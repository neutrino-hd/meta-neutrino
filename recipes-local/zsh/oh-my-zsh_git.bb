SUMMARY = "Extentions for zsh"
DESCRIPTION = "Zsh is a shell designed for interactive use, although it is also a \
               powerful scripting language. Many of the useful features of bash, \
               ksh, and tcsh were incorporated into zsh; many original features were added."
HOMEPAGE = "https://github.com/robbyrussell/oh-my-zsh.git"
SECTION = "base/shell"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=bfe3cd795831f8ea1dd7fc53382a1f19"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/robbyrussell/oh-my-zsh.git;protocol=https \
	   file://zshrc \
"

SRCREV = "4fec0a46e73479cf3b9e7f953750af5ed5df87ac"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh ${D}${sysconfdir}/zsh
	cp -rf ${S}/* ${D}/home/root/.oh-my-zsh
	cp -rf ${WORKDIR}/zshrc ${D}${sysconfdir}/zsh/zshrc
}

FILES_${PN} = "/home \
	       /etc \
"
