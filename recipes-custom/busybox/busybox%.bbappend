FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SYSTEMD_SERVICE_${PN} = "telnet.service"

SRC_URI_append += "file://telnet.service \
				   file://telnetd.cfg \
				   file://dos2unix.cfg \
				   file://ether-wake.cfg \
				   file://remove.cfg \
                                   file://simple.script \
				   file://resolv.conf \
"



do_install_append() {
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/
	install -m 0644 ${WORKDIR}/telnet.service ${D}${systemd_unitdir}/system/
	ln -sf ${systemd_unitdir}/system/telnet.service ${D}${systemd_unitdir}/system/multi-user.target.wants/telnet.service
	install -m 0644 ${WORKDIR}/resolv.conf ${D}/etc/resolv.conf
}

FILES_${PN} += "lib/systemd"
