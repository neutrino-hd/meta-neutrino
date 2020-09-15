DESCRIPTION = "luafilesystem"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/keplerproject/luafilesystem.git"
DEPENDS += "virtual/lua"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

SRC_URI = "git://github.com/keplerproject/luafilesystem.git;protocol=https \
	   file://0001-adjust-config.patch \
"

SRCREV = "1dfb8c41e8a7e689959baeaf2961437db9615f74"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

TARGET_CC_ARCH += "${LDFLAGS}" 

CFLAGS_append += "-I${STAGING_INCDIR}"

do_install () {
	install -d ${D}${libdir}/lua/${LUA_VER}
	install -m 755 ${S}/src/lfs.so ${D}${libdir}/lua/${LUA_VER}
}

FILES_${PN} += "${libdir}/lua/${LUA_VER}/lfs.so"
FILES-dbg_${PN} += "${libdir}/lua/${LUA_VER}/.debug/lfs.so"

BBCLASSEXTEND = "native nativesdk"

SRC_URI[md5sum] = "1055a248d5e613012dd5f713dacd7cc3"
SRC_URI[sha256sum] = "5d2ab983f76400e8d7ca5bb206aa17c00d0167e6e665cf260d7176a88e19e1f1"

