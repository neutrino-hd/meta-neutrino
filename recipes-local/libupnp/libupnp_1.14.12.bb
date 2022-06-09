HOMEPAGE = "http://pupnp.sourceforge.net/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

PR = "r1"

LEAD_SONAME = "libupnp"
SRC_URI = "${SOURCEFORGE_MIRROR}/pupnp/${P}.tar.bz2"

inherit autotools pkgconfig

S = "${WORKDIR}/libupnp-${PV}"

SRC_URI[sha256sum] = "091c80aada1e939c2294245c122be2f5e337cc932af7f7d40504751680b5b5ac"

