include neutrino-lua-plugins-target-pattern.inc

SUMMARY = "Content for ${SRC_NAME}"
MAINTAINER = "community"

SRCREV_${SRC_NAME} = "${AUTOREV}"
SRCREV_FORMAT = "${MAINTAINER}-${SRC_NAME}"

SRC_URI_append = " \
	git://github.com/bazi-98/plugin-scripts-lua.git;name=${SRC_NAME};protocol=https;subpath=plugins/${SRC_NAME};destsuffix=git/${SRC_NAME} \
	file://webtv.tar.gz \
"

do_install () {
	# clean up, not required for content
	rm -rf ${D}${N_PLUGIN_DIR}

	# install content
	install -d ${D}${N_WEBTV_DIR}
	install -m 644 ${S}/${SRC_SUBPATH}/* ${D}${N_WEBTV_DIR}
	install -m 644 ${WORKDIR}/webtv_*.xml ${D}${N_WEBTV_DIR}
	install -m 644 ${S}/${SRC_NAME}/plutotv.* ${D}${N_WEBTV_DIR}
}
