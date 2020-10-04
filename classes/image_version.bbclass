
# set release type, configured in local.conf
image_version_get_release_type() {
	if [ ${RELEASE_STATE} == 0 ]; then
		ret="release"
	elif [ ${RELEASE_STATE} == 1 ]; then
		ret="beta"
	elif [ ${RELEASE_STATE} == 2 ]; then
		ret="nightly"
	else
		ret="personal"
	fi
	echo "$ret"
}

image_version_get_flavour_tag() {
	ret=""
	if [ ${FLAVOUR} != ${DISTRO} ]; then
		ret="${FLAVOUR}-flavour_"
	fi
	echo "$ret"
}

image_version_get_poky_version() {
	POKY_TAG=`git -C ${COREBASE} describe --abbrev=0`
	ret=`git -C ${COREBASE} rev-list $POKY_TAG..HEAD --count`
	echo "$ret"
}

image_version_get_meta_tuxbox() {
	ret="meta-neutrino"
	# In case of a changed repository name this should keeping compatibilty.
	# Why: meta-neutrino contains mostly recipes to create an image and
	# neutrino is only a part of image like all the other recipes.
	if [ -e ${COREBASE}/meta-tuxbox ]; then
		ret="meta-tuxbox"
	fi
	echo "$ret"
}

image_version_get_tuxbox_tag() {
	META_TUXBOX=`get_meta_tuxbox`
	ret=`git -C ${COREBASE}/$META_TUXBOX describe --abbrev=0`
	echo "$ret"
}

image_version_get_tuxbox_version() {
	META_TUXBOX=`get_meta_tuxbox`
	TUXBOX_TAG=`get_tuxbox_tag`
	ret=`git -C ${COREBASE}/$META_TUXBOX rev-list $TUXBOX_TAG..HEAD --count`
	echo "$ret"
}

image_version_get_meta_version() {
	# We extract the image version from 'git describe' content which are  primary provided by poky meta layers and
	# secondary from our image layer and the git tags are synchronized with our image layer.
	# The last git tag and the counted describe content will be added for our image version.
	META_TUXBOX_TAG=`get_tuxbox_tag`
	META_POKY_VERSION=`get_poky_version`
	META_TUXBOX_VERSION=`get_tuxbox_version`
	ret="$META_TUXBOX_TAG-$META_POKY_VERSION-$META_TUXBOX_VERSION"

	# If we found a user defined version it will be preferred
	if [ ${DISTRO_CUSTOM_VERSION} != "" ]; then
		ret=${DISTRO_CUSTOM_VERSION}
	fi

	# If no meta version or any user version was found then ret contains the
	# default distro version number which is defined in tuxbox.conf.
	if [ -z $ret ]; then
		ret=${DISTRO_VERSION_NUMBER}
	fi
	echo "$ret"
}

image_version_get_flavour_suffix() {
	ret=${IMAGE_FLAVOUR_TAG}v`get_meta_version`_`get_release_type`
	echo "$ret"
}

image_version_get_filename_prefix() {
	ret=${IMAGE_NAME}
	if [ ${INHIBIT_EXTENDED_IMAGE_VERSION} == "0" ]; then
		ret=${IMAGE_NAME}_`get_flavour_suffix`
	fi
	echo "$ret"
}

image_version_get_filename_latest_prefix() {
	ret=${IMAGE_BASENAME}_${MACHINE}
	if [ ${INHIBIT_EXTENDED_IMAGE_VERSION} == "0" ]; then
		ret=${IMAGE_BASENAME}_${MACHINE}_latest_`get_flavour_suffix`
	fi
	echo "$ret"
}

EXPORT_FUNCTIONS get_release_type  get_flavour_tag  get_poky_version  get_meta_tuxbox get_tuxbox_tag  get_tuxbox_version  get_meta_version  get_flavour_suffix  get_filename_prefix  get_filename_latest_prefix 

export IMAGE_RELEASE_TYPE="`get_release_type`"
export IMAGE_FLAVOUR_TAG="`get_flavour_tag`"
export IMAGE_POKY_VERSION="`get_poky_version`"
export IMAGE_META_TUXBOX="`get_meta_tuxbox`"
export IMAGE_TUXBOX_TAG="`get_tuxbox_tag`"
export IMAGE_TUXBOX_VERSION="`get_tuxbox_version`"
export IMAGE_META_VERSION="`get_meta_version`"
export IMAGE_FLAVOUR_SUFFIX="`get_flavour_suffix`"
export IMAGE_FILE_NAME_PREFIX="`get_filename_prefix`"
export IMAGE_FILE_NAME_LATEST_PREFIX="`get_filename_latest_prefix`"
