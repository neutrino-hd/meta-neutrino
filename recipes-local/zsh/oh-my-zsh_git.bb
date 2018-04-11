SUMMARY = "Extentions for zsh"
DESCRIPTION = "Zsh is a shell designed for interactive use, although it is also a \
               powerful scripting language. Many of the useful features of bash, \
               ksh, and tcsh were incorporated into zsh; many original features were added."
HOMEPAGE = "https://github.com/robbyrussell/oh-my-zsh.git"
SECTION = "base/shell"

LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=1a4c4cda3e8096d2fd483ff2f4514fec"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/robbyrussell/oh-my-zsh.git;protocol=https \
	   file://zshrc \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh
	cp -rf ${S}/* ${D}/home/root/.oh-my-zsh
	cp -rf ${WORKDIR}/zshrc ${D}/home/root/.zshrc
}


FILES_${PN} = "/home \
	       /etc \
"
