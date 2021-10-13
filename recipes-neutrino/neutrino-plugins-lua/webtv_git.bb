include neutrino-lua-plugins-target-pattern.inc

SUMMARY = "Content for ${SRC_NAME}, required by Neutrino bouquets. Contributed by tuxbox, ni."
MAINTAINER = "community"
SECTION = "optional"

DEPENDS = "plutotv-update"

PR = "r2"
PV = "0.${SRCPV}"

SRCREV_prov0 = "${AUTOREV}"
SRCREV_prov1 = "${AUTOREV}"
# SRCREV_FORMAT = "${MAINTAINER}_prov0_prov1"
SRCREV_FORMAT = "${MAINTAINER}"

S = "${WORKDIR}/src"
SRC_RAW= "src-raw"

## We store temporarily the origin git contents into a separate "${WORKDIR}/${SRC_RAW}" directory.
SRC_URI = " \
	git://github.com/tuxbox-neutrino/plugin-scripts-lua.git;name=prov0;protocol=https;subpath=plugins/${SRC_NAME};destsuffix=${SRC_RAW}/${SRC_NAME}-prov0 \
	git://github.com/neutrino-images/ni-neutrino-plugins.git;name=prov1;protocol=https;subpath=scripts-lua/plugins/${SRC_NAME};destsuffix=${SRC_RAW}/${SRC_NAME}-prov1 \
	file://0001-yt_live.xml-update-live-channels.patch \
"

## Before install, we misuse the do_patch routine to prepare content.
## Because we need files from several sources, these have been already fetched into the ${SRC_RAW} directory.
## We only select the required data from ${SRC_RAW} directory, and we move it into the required source code directory ${S}.
## Finally we will patch some adjustments.
do_patch () {
	WEBTVPROVLIST="prov0 prov1"

	for p in $WEBTVPROVLIST; do
		cp  ${SRC_RAW}/${SRC_NAME}-$p/*.lua ${S}
		cp  ${SRC_RAW}/${SRC_NAME}-$p/*.xml ${S}
	done

	git -C ${S} apply ${WORKDIR}/0001-yt_live.xml-update-live-channels.patch
}

do_install () {
	# Clean up.
	rm -rf ${D}${N_PLUGIN_DIR}

	# Install content from source directory into target.
	install -d ${D}${N_WEBTV_DIR}
	install -m 644 ${S}/* ${D}${N_WEBTV_DIR}
}
