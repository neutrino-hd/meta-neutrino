DESCRIPTION = "LuaBitOp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://bitop.luajit.org"
DEPENDS += "virtual/lua virtual/lua-native"
RDEPENDS_${PN} += "virtual/lua"

include ../lua.inc

SRCREV = "${AUTOREV}"

SRC_URI = "https://bitop.luajit.org/download/LuaBitOp-1.0.2.tar.gz \
"
SRC_URI[sha256sum] = "1207c9293dcd52eb9dca6538d1b87352bd510f4e760938f5048433f7f272ce99"

S = "${WORKDIR}/LuaBitOp-1.0.2"

SRC = "bit.c"

CFLAGS += "-I${STAGING_INCDIR}/luajit-2.1"

do_compile () {
	${CC} -O2 -fPIC -fomit-frame-pointer -shared -I${STAGING_INCDIR}/luajit-2.1 -c -o bit.o bit.c
	${CC} -shared -fPIC -O2 ${CFLAGS} ${LDFLAGS} -I${STAGING_INCDIR}/luajit-2.1 -o bit.so bit.c 
}

do_install () {
	install -d ${D}${libdir}/lua/${LUA_VER}
	install -m644 bit.so ${D}${libdir}/lua/${LUA_VER}/bit32.so
}

FILES_${PN} =  "/usr/lib \
"

BBCLASSEXTEND = "native nativesdk"

