SUMMARY = "eplayer5 a simple gst videoplayer"
MAINTAINER = "Duckbox Team"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 libxml2"


PV = "1.0"
PR = "r0"

inherit pkgconfig

SRC_URI = "file://eplayer5.c file://Makefile"

S = "${WORKDIR}"

FILES_${PN} = "${bindir}/*"

do_compile() {
    make -f Makefile eplayer5
    ${STRIP} ${S}/eplayer5
}

do_install() {
    install -d ${D}/${bindir}
    install -m 755 ${S}/eplayer5 ${D}/${bindir}
}

