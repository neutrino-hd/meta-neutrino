DESCRIPTION = "lua-expat"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/us/license.html;beginline=61;endline=105;md5=e883421fc699b0af5d55b94af3d817cf"
HOMEPAGE = "https://www.github.com/tomasguisasola"
DEPENDS += "expat virtual/lua"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

SRC_URI = "git://github.com/tomasguisasola/luaexpat.git;protocol=https \
           "

SRCREV = "9878960feecd9f3c30d76652dfc5a721a7117aac"

S = "${WORKDIR}/git"
PV = "1.3.3"

inherit autotools-brokensep
CFLAGS += "-I${STAGING_INCDIR}/luajit-2.1"

do_configure_prepend () {
        sed -i "s|CFLAGS =.*|CFLAGS = ${CFLAGS} -fPIC|" ${S}/makefile
        sed -i "s|CC =.*|CC = ${CC}|" ${S}/makefile
        sed -i "s|-lexpat|${LDFLAGS}|" ${S}/makefile
}

FILES_${PN} = "/usr/share \
	       /usr/lib\
"

FILES_${PN}-dbg += "/usr/lib/lua/${LUA_VER}/.debug/*.so"

EXTRA_OEMAKE = " \
		LUA_DIR=${D}/usr/share/lua/${LUA_VER} \
		LUA_LIBDIR=${D}/usr/lib/lua/${LUA_VER} \
"

INSANE_SKIP_${PN} = "dev-so"

BBCLASSEXTEND = "native nativesdk"
