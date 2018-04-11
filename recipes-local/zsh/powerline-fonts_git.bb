SUMMARY = "Powerline fonts"
DESCRIPTION = "Powerline Consolefonts needed for agnoster zsh theme"

HOMEPAGE = "https://github.com/powerline/fonts.git"
SECTION = "base/fonts"

LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=1a4c4cda3e8096d2fd483ff2f4514fec"

SRC_URI = "git://github.com/powerline/fonts.git;protocol=https"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${datadir}/consolefonts
	cp ${S}/Terminus/PSF/* ${D}${datadir}/consolefonts
}

FILES_${PN} = "/usr/share/consolefonts"
