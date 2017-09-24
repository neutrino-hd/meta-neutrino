DESCRIPTION = "Myspass Lua Plugin"
LICENSE = "MIT"
MAINTAINER = "Marc Szymkowiak"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/Ezak91/CST-MYSPASS-Plugin"

PR = "r1"

SRC_URI = "git://github.com/Ezak91/CST-MYSPASS-Plugin"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/var/plugins"

do_install () {
	install -d ${D}/home/builder/.config/neutrino/plugins
	install -m 755 ${S}/myspass.lua ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/myspass.cfg ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/myspass.png ${D}/home/builder/.config/neutrino/plugins
}

do_install_append () {
echo "integration=2" >> ${D}/home/builder/.config/neutrino/plugins/myspass.cfg
}

FILES_${PN} += "/home \
"
