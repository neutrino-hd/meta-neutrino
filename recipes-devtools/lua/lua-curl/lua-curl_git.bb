DESCRIPTION = "lua-cURL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/Lua-cURL/Lua-cURLv3"
DEPENDS += "curl virtual/lua"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

S = "${WORKDIR}/git"
SRCREV = "c38f3d4c298a1ce941158e0b8bc9ea5ca40fe655"
PV = "0.3.11"

SRC_URI = "git://github.com/Lua-cURL/Lua-cURLv3.git;protocol=https \
	   file://0001-Makefile-adjust-for-OE.patch \
"

EXTRA_OEMAKE += "LUA_VER=${LUA_VER}"

inherit autotools-brokensep

FILES_${PN} =  "/usr/share \
		/usr/lib \
"

BBCLASSEXTEND = "native nativesdk"

