HOMEPAGE = "http://pupnp.sourceforge.net/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

PR = "r1"

LEAD_SONAME = "libupnp"
SRC_URI = "${SOURCEFORGE_MIRROR}/pupnp/libupnp-${PV}.tar.bz2 \
           file://sepbuildfix.patch \
           file://0001-ithread-Use-pthread_mutexattr_gettype-pthread_mutexa.patch \
"

inherit autotools pkgconfig

S = "${WORKDIR}/libupnp-${PV}"

SRC_URI[sha256sum] = "c5a300b86775435c076d58a79cc0d5a977d76027d2a7d721590729b7f369fa43"

