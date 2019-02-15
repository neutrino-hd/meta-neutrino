DESCRIPTION = "Neutrino Lua Setting Updater"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
HOMEPAGE = ""
MAINTAINER = "Horsti58"
DEPENDS = "lua"


SRC_URI = "git://github.com/horsti58/lua-data.git;protocol=https"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/lua/settingsupdater.lua ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/lua/settingsupdater.cfg ${D}/usr/share/tuxbox/plugins
}

FILES_${PN} =  "/usr \
"
