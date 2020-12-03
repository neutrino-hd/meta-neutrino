HOMEPAGE = "http://pupnp.sourceforge.net/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

PR = "r1"

LEAD_SONAME = "libupnp"
SRC_URI = "${SOURCEFORGE_MIRROR}/pupnp/${P}.tar.bz2"

inherit autotools pkgconfig

SRC_URI[sha256sum] = "ecb23d4291968c8a7bdd4eb16fc2250dbacc16b354345a13342d67f571d35ceb"

