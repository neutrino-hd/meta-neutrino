DESCRIPTION = "GNU nano (Nano's ANOther editor, or \
Not ANOther editor) is an enhanced clone of the \
Pico text editor."
HOMEPAGE = "http://www.nano-editor.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"
SECTION = "console/utils"
DEPENDS = "ncurses"
RDEPENDS_${PN} = "ncurses-terminfo nano-syntax-highlighting"

SRC_URI = "http://www.nano-editor.org/dist/v4/nano-${PV}.tar.gz"

inherit autotools gettext

SRC_URI += "file://nanorc"

inherit pkgconfig

SRC_URI[md5sum] = "afb611a531d321dc3775c5d9c3a5ff8c"
SRC_URI[sha256sum] = "5b3f67d7d187e9feb980e1482ba38c1bc424bace5282c6bbe85b4bb98371ef1e"

do_install(){
	install -d ${D}/${sysconfdir} ${D}/${bindir}
	install -m 644 ${WORKDIR}/nanorc ${D}${sysconfdir}/
	install -m 755 ${WORKDIR}/build/src/nano ${D}${bindir}/
}

do_install_append() {
	install -d ${D}/home/root/.local/share
}

FILES_${PN} += "/home/root"
