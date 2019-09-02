

DESCRIPTION = "luaposix is a POSIX binding, including curses, for Lua 5.1 and 5.2."
LICENSE = "MIT"
HOMEPAGE = "https://github.com/luaposix/luaposix"
LIC_FILES_CHKSUM = "file://COPYING;md5=0de24acdcec9cb21ff095356966cbef2"

SRC_URI = "https://github.com/luaposix/luaposix/archive/release-v${PV}.tar.gz"
SRC_URI[md5sum] = "a25ff76d54bbbebf7a1f3b20c9806ee3"
SRC_URI[sha256sum] = "c5ed2f6c16b9f31d3ca0db05f9b660db69c966baab244878480ab6658abbbe24"

DEPENDS += "lua-native lua ncurses"

S = "${WORKDIR}/luaposix-release-v${PV}"

inherit autotools pkgconfig

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS}'"
EXTRA_OECONF += "--datadir=${datadir}/lua/5.2 --libdir=${libdir}/lua/5.2"

FILES_${PN}-dbg += "${libdir}/lua/5.2/.debug"
FILES_${PN}-dev += "${libdir}/lua/5.2/*.la"
FILES_${PN} += "${libdir}/lua/5.2/*.so"
FILES_${PN} += "${datadir}/lua/5.2/*.lua"

BBCLASSEXTEND = "native"

INSANE_SKIP += "src-uri-bad"