SRC_URI_append += "file://00-create-volatile.conf"

do_install_append() {
	install -m 644 ${WORKDIR}/00-create-volatile.conf ${D}${sysconfdir}/tmpfiles.d/00-create-volatile.conf
}

pkg_postinst_udev-hwdb () {
		udevadm hwdb --update
}
