FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "file://bluetooth-poweron.service"

RDEPENDS_${PN} = "firmware-rtl8761b"

PACKAGECONFIG_append += "sixaxis"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants
	install -m644 ${WORKDIR}/bluetooth-poweron.service ${D}${systemd_unitdir}/system
	ln -sf ${systemd_unitdir}/system/bluetooth-poweron.service ${D}${systemd_unitdir}/system/multi-user.target.wants
}

