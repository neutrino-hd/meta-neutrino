SUMMARY = "PC/SC Lite smart card framework and applications"
HOMEPAGE = "http://pcsclite.alioth.debian.org/"
LICENSE = "BSD & GPLv3+"
LICENSE_${PN} = "BSD"
LICENSE_${PN}-lib = "BSD"
LICENSE_${PN}-doc = "BSD"
LICENSE_${PN}-dev = "BSD"
LICENSE_${PN}-dbg = "BSD & GPLv3+"
LICENSE_${PN}-spy = "GPLv3+"
LICENSE_${PN}-spy-dev = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=628c01ba985ecfa21677f5ee2d5202f6"
DEPENDS = "udev"

SRC_URI = "https://pcsclite.apdu.fr/files/pcsc-lite-${PV}.tar.bz2"

SRC_URI[sha256sum] = "0148d403137124552c5d0f10f8cdab2cbb8dfc7c6ce75e018faf667be34f2ef9"

inherit autotools systemd pkgconfig

EXTRA_OECONF = " \
    --disable-libusb \
    --enable-libudev \
    --enable-usbdropdir=${libdir}/pcsc/drivers \
"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/${PN}-${PV}"

do_install_append() {
	# pcsc-spy needs python2 .... remove it
	rm -rf ${D}${bindir}
	rm -rf ${D}${libdir}/libpcscspy.so
}

RRECOMMENDS_${PN} = "ccid"

PACKAGES =+ "${PN}-lib"

FILES_${PN} = "${sbindir}"
FILES_${PN}-lib = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-dev = "${includedir} \
                   ${libdir}/pkgconfig \
                   ${libdir}/libpcsclite.la \
                   ${libdir}/libpcsclite.so"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "pcscd.socket"


