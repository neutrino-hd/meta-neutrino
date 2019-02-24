DESCRIPTION = "Syntax Highlighting für GNU nano"
HOMEPAGE = "http://www.nano-editor.org/"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
SECTION = "console/utils"
RDEPENDS_${PN} = "nano"

SRC_URI = "git://github.com/scopatz/nanorc.git;protocol=https"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

S = "${WORKDIR}/git"

do_install(){
	install -d ${D}/${datadir}/nano
	install -m 644 ${S}/*.nanorc ${D}${datadir}/nano
}

FILES_${PN} += "/usr/share/nano"
