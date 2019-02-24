DESCRIPTION = "GNU nano (Nano's ANOther editor, or \
Not ANOther editor) is an enhanced clone of the \
Pico text editor."
HOMEPAGE = "http://www.nano-editor.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"
SECTION = "console/utils"
DEPENDS = "ncurses"
RDEPENDS_${PN} = "ncurses-terminfo nano-syntax-highlighting"

SRC_URI = "http://www.nano-editor.org/dist/v3/nano-${PV}.tar.gz"

inherit autotools gettext

SRC_URI += "file://nanorc"

inherit pkgconfig

SRC_URI[md5sum] = "84f973654eaf42efea84cc7a6d8871b3"
SRC_URI[sha256sum] = "ca694554628d6d5e695af70d3a78673a76b474c38732ab5bcca47d22845086bf"

do_install(){
	install -d ${D}/${sysconfdir} ${D}/${bindir}
	install -m 644 ${WORKDIR}/nanorc ${D}${sysconfdir}/
	install -m 755 ${WORKDIR}/build/src/nano ${D}${bindir}/
}

do_install_append() {
	install -d ${D}/home/root/.local/share
}

FILES_${PN} += "/home/root"
