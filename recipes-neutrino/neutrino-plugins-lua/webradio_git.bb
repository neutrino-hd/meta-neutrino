include neutrino-lua-plugins-target-pattern.inc

SUMMARY = "Content for ${SRC_NAME}"

do_install () {
	# clean up, not required for content
	rm -rf ${D}${N_PLUGIN_DIR}

	# install content
	install -d ${D}${N_WEBRADIO_DIR}
# 	install -m 644 ${S}/${SRC_NAME}/* ${D}${N_WEBRADIO_DIR}
}

do_fetch () {
	# no content available
	:
}

do_unpack () {
	# no content available
	:
}
