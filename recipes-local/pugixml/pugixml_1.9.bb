SUMMARY = "XML Parser library "
HOMEPAGE = "https://github.com/zeux/pugixml"
LICENSE = "MIT"
PRIORITY = "optional"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Package Revision (update whenever recipe is changed)
PR = "r0"

TARGET_CC_ARCH += "${LDFLAGS}"

SRC_URI = "http://github.com/zeux/pugixml/releases/download/v${PV}/pugixml-${PV}.tar.gz \
           file://001_Makefile.patch \
"

SRC_URI[md5sum] = "7286ee2ed11376b6b780ced19fae0b64"
SRC_URI[sha256sum] = "d156d35b83f680e40fd6412c4455fdd03544339779134617b9b28d19e11fdba6"

S = "${WORKDIR}/${PN}-${PV}"

inherit cmake pkgconfig gettext

do_configure_prepend () {
        sed -i "s|\/\/ #define PUGIXML_HAS_LONG_LONG|#define PUGIXML_HAS_LONG_LONG|" ${S}/src/pugiconfig.hpp
}

