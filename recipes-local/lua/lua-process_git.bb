DESCRIPTION = "lua process module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = ""
DEPENDS += "lua lua-native"
RDEPENDS_${PN} += "lua"

SRC_URI = "git://github.com/mah0x211/lua-process.git;protocol=https \
"
S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

inherit autotools-brokensep

TARGET_CC_ARCH += "${LDFLAGS}" 

CFLAGS_append += "-I${STAGING_INCDIR}"

do_configure_prepend() {
	export LIBDIR=${LIBDIR}
}

SRC_URI[md5sum] = "df9d4dbb84038c9bdfd9c8b0bfa292de"
SRC_URI[sha256sum] = "daa2504f872ea28018e43ca23528a615493c4ee4d7d136c4e2f8489ad405c47b"

BBCLASSEXTEND = "native nativesdk"

