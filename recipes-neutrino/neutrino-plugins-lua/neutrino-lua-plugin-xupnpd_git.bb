
include neutrino-lua-plugins-target-pattern.inc

SRC_NAME = "xupnpd"
SRC_SUBPATH = "${SRC_NAME}"
SUMMARY = "Content for xupnpd"


do_install () {
	# clean up, not required for content
	rm -rf ${D}/usr/share/tuxbox

	# install content
	install -d ${D}/usr/share/xupnpd/plugins
	install -m 644 ${S}/${SRC_NAME}/* ${D}/usr/share/xupnpd/plugins/
}
