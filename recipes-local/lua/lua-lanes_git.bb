DESCRIPTION = "LuaLanes"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/LuaLanes/lanes.git"
DEPENDS += "lua"
RDEPENDS_${PN} += "lua"


SRC_URI = "git://github.com/LuaLanes/lanes.git;protocol=https \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit cmake

do_install_append() {
	install -d ${D}/5.2/lanes ${D}/usr/lib/lua ${D}/usr/share/lua/5.2
	rm -r ${D}/usr/share/lanes
        mv ${D}/usr/share/lua/cmod/core.so ${D}/5.2/lanes
	mv ${D}/5.2 ${D}/usr/lib/lua
        mv ${D}/usr/share/lua/lmod/lanes.lua ${D}/usr/share/lua/5.2/ 
}

FILES_${PN} = "/usr"

BBCLASSEXTEND = "native nativesdk"
