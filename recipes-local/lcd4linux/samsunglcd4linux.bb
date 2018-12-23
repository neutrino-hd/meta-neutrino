SUMMARY = "Samsung Configuration for LCD4Linux"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PN} = "lcd4linux"

PV = "${SRCPV}"

SRC_URI = "git://github.com/horsti58/SamsungLCD4Linux;protocol=https \
"

SRCREV = "${AUTOREV}"
S =  "${WORKDIR}/git/${FLAVOUR}"

do_install() {
	install -d ${D}/usr
	cp -rf ${S}/etc ${D}
	cp -rf ${S}/share ${D}/usr
	chmod 600 ${D}/${sysconfdir}/lcd4linux.conf
}

FILES_${PN} += "/usr \
		/etc \
" 
