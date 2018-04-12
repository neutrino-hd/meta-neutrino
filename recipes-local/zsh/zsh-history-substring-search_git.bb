SUMMARY = "History subsearch for zsh"
DESCRIPTION = "Add history subsearch support for zsh"
HOMEPAGE = "https://github.com/zsh-users/zsh-history-substring-search.git"
SECTION = "base/shell"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/zsh-users/zsh-history-substring-search.git;protocol=https \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-history-substring-search
	cp -rf ${S}/zsh-history-substring-search.plugin.zsh ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-history-substring-search
}

FILES_${PN} = "/home \
"
