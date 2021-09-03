include neutrino-lua-plugins-target-pattern.inc

SUMMARY = "Content for ${SRC_NAME}"

SRC_URI_append = " \
		   file://webtv.tar.gz \
"

do_install () {
	# clean up, not required for content
	rm -rf ${D}${N_PLUGIN_DIR}

	# install content
	install -d ${D}${N_WEBTV_DIR}
	install -m 644 ${S}/${SRC_SUBPATH}/* ${D}${N_WEBTV_DIR}
	install -m 644 ${WORKDIR}/webtv_*.xml ${D}${N_WEBTV_DIR}
}
