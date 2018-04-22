SUMMARY = "Autosuggestions for zsh"
DESCRIPTION = "Add autosuggestion support into zsh"
HOMEPAGE = "https://github.com/zsh-users/zsh-autosuggestions.git"
SECTION = "base/shell"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f55002133c6640aa78f9b7826a266013"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/zsh-users/zsh-autosuggestions.git;protocol=https \
"

SRCREV = "67a364bc1766fb775010cd00dda1967210013410"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-autosuggestions
	cp -rf ${S}/* ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-autosuggestions
}

FILES_${PN} = "/home \
"
