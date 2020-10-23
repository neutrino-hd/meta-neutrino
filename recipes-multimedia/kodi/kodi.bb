SUMMARY = "Systemd service for kodi startup"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PV = "1.0"

SRC_URI = "file://kodi.service \
	   file://start_kodi.lua \
	   file://start_kodi.cfg \
	   file://start_kodi_hint.png \
"

do_install() {
	install -d ${D}/lib/systemd/system ${D}${datadir}/tuxbox/neutrino/plugins
	install -m 0644 ${WORKDIR}/kodi.service ${D}/lib/systemd/system
	install -m 0644 ${WORKDIR}/start_kodi* ${D}${datadir}/tuxbox/neutrino/plugins 
}

FILES_${PN} = "/lib /usr"

RDEPENDS_${PN} += "virtual/kodi"
