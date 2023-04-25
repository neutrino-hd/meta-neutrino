HOMEPAGE = "https://www.neutrino-images.de"
MAINTAINER = "Sven Hoefer"

SRC_URI += "git://github.com/neutrino-images/ni-libstb-hal;protocol=https \
"

PV = "${FLAVOUR}-git${SRCPV}"

EXTRA_OECONF_append += " \
			--with-boxtype=${TARGET_ARCH}box \
			--with-boxmodel=${MACHINE} \
			--with-targetroot= \
			--with-targetprefix= /usr \
"
