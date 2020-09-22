DESCRIPTION = "lua-compat53"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/keplerproject/lua-compat-5.3.git"
DEPENDS += "virtual/lua"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/keplerproject/lua-compat-5.3.git;protocol=https \
"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"
PV = "v0.9"
SRC = "compat-5.3.c"

CFLAGS += "-I${S}/c-api -I${STAGING_INCDIR}/luajit-2.1 -DLUA_COMPAT_BITLIB"

COMPAT53_LIBS = "lstrlib lutf8lib ltablib lbitlib"

do_compile () {
	for file in ${COMPAT53_LIBS}; do
		${CC} -O2 -fPIC -fomit-frame-pointer -shared -I${STAGING_INCDIR}/luajit-2.1 -c -o $file.o $file.c
		${CC} -shared -fPIC -O2 ${CFLAGS} ${LDFLAGS} -I${STAGING_INCDIR}/luajit-2.1 -o $file.so $file.c 
	done
}

do_install () {
	install -d ${D}${libdir}/lua/${LUA_VER}
	install -m 644 ${S}/lstrlib.so ${D}${libdir}/lua/${LUA_VER}/string.so
	install -m 644 ${S}/lutf8lib.so ${D}${libdir}/lua/${LUA_VER}/utf8.so
	install -m 644 ${S}/lbitlib.so ${D}${libdir}/lua/${LUA_VER}/bit32.so	
	install -m 644 ${S}/ltablib.so ${D}${libdir}/lua/${LUA_VER}/table.so
}

FILES_${PN} =  "/usr/lib \
"

BBCLASSEXTEND = "native nativesdk"

