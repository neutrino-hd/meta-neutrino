DESCRIPTION = "etckeeper Lua plugin"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
HOMEPAGE = "https://github.com/neutrino-hd"
DEPENDS = "lua"
RDEPENDS_${PN} = "luaposix"
PV = "0.1"

SRC_URI = "file://etckeeper.lua \
	   file://etckeeper.cfg \
	   file://etckeeper.png \
"

S = "${WORKDIR}/"

do_install () {
	install -d ${D}/etc/neutrino/plugins
	install -m 644 ${S}/etckeeper.* ${D}/etc/neutrino/plugins
}


