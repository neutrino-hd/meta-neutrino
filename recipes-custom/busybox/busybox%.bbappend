FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SYSTEMD_SERVICE_${PN} = "telnet.service"

SRC_URI_append += "file://telnet.service \
				   file://telnetd.cfg \
"



do_install_append() {
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/
	install -m 0644 ${WORKDIR}/telnet.service ${D}${systemd_unitdir}/system/
	ln -sf ${systemd_unitdir}/system/telnet.service ${D}${systemd_unitdir}/system/multi-user.target.wants/telnet.service
}

FILES_${PN} += "lib/systemd"