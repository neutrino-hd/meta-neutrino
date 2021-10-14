SUMMARY = "XML Parser library "
HOMEPAGE = "https://github.com/zeux/pugixml"
LICENSE = "MIT"
PRIORITY = "optional"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Package Revision (update whenever recipe is changed)
PR = "r0"

TARGET_CC_ARCH += "${LDFLAGS}"

SRC_URI = "http://github.com/zeux/pugixml/releases/download/v${PV}/pugixml-${PV}.tar.gz \
"

SRC_URI[sha256sum] = "26913d3e63b9c07431401cf826df17ed832a20d19333d043991e611d23beaa2c"

S = "${WORKDIR}/${PN}-${PV}"

inherit cmake pkgconfig gettext

do_configure_prepend () {
        sed -i "s|\/\/ #define PUGIXML_HAS_LONG_LONG|#define PUGIXML_HAS_LONG_LONG|" ${S}/src/pugiconfig.hpp
}
