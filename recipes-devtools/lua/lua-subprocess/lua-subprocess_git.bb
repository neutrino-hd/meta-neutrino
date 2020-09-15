DESCRIPTION = "lua-subprocess"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/xlq/lua-subprocess.git"
DEPENDS += "virtual/lua"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

SRC_URI = "git://github.com/xlq/lua-subprocess.git;protocol=https \
	   file://0001-subprocess.c-include-signal.h.patch \
	   file://Makefile \
"		

SRCREV = "b5f84612acb625a0b564aa50f18614180a2a404e"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit autotools-brokensep

TARGET_CC_ARCH += "${LDFLAGS}" 

CFLAGS_append += "-I${STAGING_INCDIR}"

do_configure_prepend() {
	cp -rf ${WORKDIR}/Makefile ${S}/Makefile
}

do_install () {
	install -d ${D}${libdir}/lua/${LUA_VER}
	install -m 755 ${S}/subprocess.so ${D}${libdir}/lua/${LUA_VER}
}

FILES_${PN} = "/usr/lib"


BBCLASSEXTEND = "native nativesdk"

