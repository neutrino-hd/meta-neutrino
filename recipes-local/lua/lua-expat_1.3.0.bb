DESCRIPTION = "lua-expat"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://doc/us/license.html;beginline=61;endline=105;md5=233d45b83cb017f713f5293cabd9f391"
HOMEPAGE = "https://www.github.com/keplerproject"
DEPENDS += "expat lua"
RDEPENDS_${PN} += "lua"

S = "${WORKDIR}/luaexpat-${PV}"


SRC_URI = "https://matthewwild.co.uk/projects/luaexpat/luaexpat-${PV}.tar.gz \
           file://expat_${PV}-make.patch \
           "

SRC_URI[md5sum] = "3c20b5795e7107f847f8da844fbfe2da"
SRC_URI[sha256sum] = "d060397960d87b2c89cf490f330508b7def1a0677bdc120531c571609fc57dc3"

LDFLAGS = "-llua"

LUA_LIB_DIR = "${libdir}/lua/5.2"
LUA_SHARE_DIR = "${datadir}/lua/5.2"


FILES_${PN} = "${LUA_LIB_DIR}/*.so \
	       ${LUA_SHARE_DIR}/lxp/*.lua "

FILES_${PN}-dbg += "${LUA_LIB_DIR}/.debug/*.so"

EXTRA_OEMAKE = "LUA_V=5.2"

do_install() {
		oe_runmake install DESTDIR=${D}
}

BBCLASSEXTEND = "native nativesdk"

