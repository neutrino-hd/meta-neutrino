SUMMARY = "Autosuggestions for zsh"
DESCRIPTION = "Add autosuggestion support into zsh"
HOMEPAGE = "https://github.com/zsh-users/zsh-autosuggestions.git"
SECTION = "base/shell"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=36e39d585ac11b6fba262a822e7135d5"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/zsh-users/zsh-autosuggestions.git;protocol=https \
"

SRCREV = "ae315ded4dba10685dbbafbfa2ff3c1aefeb490d"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-autosuggestions
	cp -rf ${S}/* ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-autosuggestions
}

FILES_${PN} = "/home \
"
