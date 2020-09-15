DESCRIPTION = "lua-linotify"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/hoelzro/linotify.git"
DEPENDS += "virtual/lua"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

SRC_URI = "git://github.com/hoelzro/linotify.git;protocol=https \
"

SRCREV = "a56913e9c0922befb65227a00cf69c2e8052de1a"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

TARGET_CC_ARCH += "${LDFLAGS}" 

do_install () {
	install -d ${D}${libdir}/lua/${LUA_VER}
	install -m 755 ${S}/inotify.so ${D}${libdir}/lua/${LUA_VER}
}

FILES_${PN} += "${libdir}/lua/${LUA_VER}/inotify.so"
FILES-dbg_${PN} += "${libdir}/lua/${LUA_VER}/.debug/inotify.so"

BBCLASSEXTEND = "native nativesdk"

SRC_URI[md5sum] = "1055a248d5e613012dd5f713dacd7cc3"
SRC_URI[sha256sum] = "5d2ab983f76400e8d7ca5bb206aa17c00d0167e6e665cf260d7176a88e19e1f1"

