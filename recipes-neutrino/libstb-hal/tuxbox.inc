HOMEPAGE = "http://www.tuxbox.org"
MAINTAINER = "Tuxbox-Developers"

SRC_URI += "git://github.com/tuxbox-neutrino/library-stb-hal.git;branch=mpx;protocol=https \
"

PV = "1.5.0+${SRCPV}"

EXTRA_OECONF_append += " \
			--with-boxtype=${TARGET_ARCH}box \
			--with-boxmodel=${MACHINE} \
			--with-targetroot= \
			--with-targetprefix= /usr \
"
