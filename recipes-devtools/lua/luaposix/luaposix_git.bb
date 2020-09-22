DESCRIPTION = "luaposix is a POSIX binding for Lua."
LICENSE = "MIT"
HOMEPAGE = "https://github.com/luaposix/luaposix"
LIC_FILES_CHKSUM = "file://LICENSE;md5=71690c320e7bd75799e67e43234bbf4f"

DEPENDS += "lua-native virtual/lua libxcrypt"

include ../lua.inc

SRC_URI = "git://github.com/luaposix/luaposix.git;branch=master \
	   file://0001-require-bit-for-luajit.patch \
"
SRCREV = "1ff80ab330dad662bec01c377726d8c369e38d18"

S = "${WORKDIR}/git"
PV = "35.0.0"

inherit siteinfo

do_compile() {
        ${STAGING_BINDIR_NATIVE}/lua ${S}/build-aux/luke \
        CFLAGS="-I${STAGING_INCDIR}/luajit-2.1"
}



do_install() {
	install -d ${D}${libdir}/lua/${LUA_VER} ${D}${datadir}/lua/${LUA_VER}
 	cp -rf ${S}/linux/posix ${D}${libdir}/lua/${LUA_VER}
	cp -rf ${S}/lib/posix ${D}${datadir}/lua/${LUA_VER}
}

FILES_${PN} = "${datadir}/lua/${LUA_VER} ${libdir}/lua/${LUA_VER}"

BBCLASSEXTEND = "native"

INSANE_SKIP_${PN} = "ldflags"

