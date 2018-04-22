SUMMARY = "zsh completions for zsh"
DESCRIPTION = "Additional completion scripts for zsh"
HOMEPAGE = "https://github.com/zsh-users/zsh-completions.git"
SECTION = "base/shell"

LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26b9ce7bfd3731f0df81909b2d90129b"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/zsh-users/zsh-completions.git;protocol=https \
"

SRCREV = "1a71e0a1a36117722c02736487e6940e72455e0b"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-completions
	cp -rf ${S}/zsh-completions.plugin.zsh ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-completions
}

FILES_${PN} = "/home \
"
