DESCRIPTION = "Blindscan dvb-s(2) satellites using stv090x devices"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README.md;md5=f084bf390249474bef1b8817e83757fa"

SRC_URI = "git://bitbucket.org/majortom/blindscan-s2.git;protocol=http file://support-enigma2.patch"

PV = "1+git${SRCPV}"

S = "${WORKDIR}/git/"

SRCREV = "1c359fac35e429d34226b28ebd8237c2f3dbb0e2"

do_configure_prepend() {
	sed -i "s|CFLG=-O -g -W|CFLG=-O -g -W -Wl,--hash-style=gnu|" ${S}/Makefile 
}

do_install () {
	install -d ${D}/${bindir}
	install -m 755 ${S}/blindscan-s2 ${D}/${bindir}
}
