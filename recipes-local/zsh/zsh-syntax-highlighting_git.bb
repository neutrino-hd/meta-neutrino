SUMMARY = "Syntax highlighting for zsh"
DESCRIPTION = "Add syntax highlighting support into zsh"
HOMEPAGE = "https://github.com/zsh-users/zsh-syntax-highlighting.git"
SECTION = "base/shell"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=3bf38c5f1bf4c65eeb1029f986724465"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/zsh-users/zsh-syntax-highlighting.git;protocol=https \
"

SRCREV = "02a37dd919dc48e0821186e5f20e78bd0215f86a"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting
	cp -rf ${S}/zsh-syntax-highlighting.plugin.zsh ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting
        cp -rf ${S}/zsh-syntax-highlighting.zsh ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting
        cp -rf ${S}/.revision-hash  ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting
        cp -rf ${S}/.version ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting
	cp -rf ${S}/highlighters ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting
}

FILES_${PN} = "/home \
"
