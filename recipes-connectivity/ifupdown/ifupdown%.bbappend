FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "file://interfaces \
		   file://networking.service \
		   file://networking \
		   file://ifupdown-pre.service \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/ ${D}${sysconfdir}/network ${D}${sysconfdir}/default
	install -m0644 ${WORKDIR}/interfaces ${D}${sysconfdir}/network/interfaces
	install -m0644 ${WORKDIR}/networking.service ${D}${systemd_unitdir}/system/networking.service
        install -m0644 ${WORKDIR}/ifupdown-pre.service ${D}${systemd_unitdir}/system/ifupdown-pre.service
        install -m0644 ${WORKDIR}/networking ${D}${sysconfdir}/default/networking
	ln -sf ${systemd_unitdir}/system/networking.service ${D}${systemd_unitdir}/system/multi-user.target.wants/networking.service 
}

FILES_${PN}_append += "lib/systemd"
