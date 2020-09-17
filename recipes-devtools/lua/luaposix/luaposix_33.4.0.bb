DESCRIPTION = "luaposix is a POSIX binding for Lua."
LICENSE = "MIT"
HOMEPAGE = "https://github.com/luaposix/luaposix"
LIC_FILES_CHKSUM = "file://COPYING;md5=7dd2aad04bb7ca212e69127ba8d58f9f"

DEPENDS += "lua-native virtual/lua"

include ../lua.inc

SRC_URI = "git://github.com/luaposix/luaposix.git;branch=release \
           file://0001-fix-avoid-race-condition-between-test-and-mkdir.patch \
	   file://0001-luaposix-change-to-luajit-bit-for-bitwise-operations.patch \
"
SRCREV = "8e4902ed81c922ed8f76a7ed85be1eaa3fd7e66d"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

CFLAGS += "-I${STAGING_INCDIR}/luajit-2.1"

do_install() {
    oe_runmake 'DESTDIR=${D}' 'luadir=${datadir}/lua/${LUA_VER}' 'luaexecdir=${libdir}/lua/${LUA_VER}' install
}

FILES_${PN} = "${datadir}/lua/${LUA_VER} ${libdir}/lua/${LUA_VER}"

