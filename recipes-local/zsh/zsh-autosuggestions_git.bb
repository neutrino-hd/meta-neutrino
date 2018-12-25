SUMMARY = "Autosuggestions for zsh"
DESCRIPTION = "Add autosuggestion support into zsh"
HOMEPAGE = "https://github.com/zsh-users/zsh-autosuggestions.git"
SECTION = "base/shell"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5a3b07e106276bb028ef5248eb6a2eb1"

RDEPENDS_${PN} = "zsh"

SRC_URI = "git://github.com/zsh-users/zsh-autosuggestions.git;protocol=https \
"

SRCREV = "70f36c007db30a5fe1edf2b63664088b502a729c"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-autosuggestions
	cp -rf ${S}/* ${D}/home/root/.oh-my-zsh/custom/plugins/zsh-autosuggestions
}

FILES_${PN} = "/home \
"
