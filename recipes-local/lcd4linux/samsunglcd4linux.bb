SUMMARY = "Samsung Configuration for LCD4Linux"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"


include lcd4linux.inc ${FLAVOUR}.inc

RDEPENDS_${PN} = "lcd4linux"

SRC_URI = "git://github.com/horsti58/SamsungLCD4Linux;protocol=https \
"
PATCHTOOL = "git"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"


do_configure() {
	sed -i "s|\/var\/lcd|\/usr\/share\/lcd|" ${S}/etc/lcd4linux.conf
}

do_install() {
	install -d ${D}/usr
	cp -rf ${S}/etc ${D}
	cp -rf ${S}/share ${D}/usr
	[ -d ${S}/var/lcd ] && cp -rf ${S}/var/lcd/* ${D}/usr/share/lcd/
	chmod 600 ${D}/${sysconfdir}/lcd4linux.conf
}

FILES_${PN} += "/usr \
		/etc \
" 
