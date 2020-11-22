FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://weston.service \
	    file://start_weston.cfg \
	    file://start_weston_hint.png \
	    file://start_weston.lua \
"

REQUIRED_DISTRO_FEATURES = ""

SYSTEMD_SERVICE_${PN} = "weston.service"		
SYSTEMD_AUTO_ENABLE = "disable"

do_install_append() {
	rm -f ${D}${systemd_unitdir}/system/weston@.service
	rm -f ${D}${systemd_unitdir}/system/weston@.socket
	install -d ${D}${datadir}/tuxbox/neutrino/plugins
	install -m 644 ${WORKDIR}/weston.service ${D}${systemd_unitdir}/system
	install -m 644 ${WORKDIR}/start_weston* ${D}${datadir}/tuxbox/neutrino/plugins
}

FILES_${PN} += "${datadir}"

