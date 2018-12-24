SUMMARY = "Samsung Configuration for LCD4Linux"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PN} = "lcd4linux"

SRC_URI = "git://github.com/horsti58/SamsungLCD4Linux;protocol=https \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

include ${FLAVOUR}.inc

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
