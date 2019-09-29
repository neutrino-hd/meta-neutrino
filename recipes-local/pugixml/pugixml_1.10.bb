SUMMARY = "XML Parser library "
HOMEPAGE = "https://github.com/zeux/pugixml"
LICENSE = "MIT"
PRIORITY = "optional"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Package Revision (update whenever recipe is changed)
PR = "r0"

TARGET_CC_ARCH += "${LDFLAGS}"

SRC_URI = "http://github.com/zeux/pugixml/releases/download/v${PV}/pugixml-${PV}.tar.gz \
"

SRC_URI[md5sum] = "f97237e9908201c6d8536210747b66af"
SRC_URI[sha256sum] = "55f399fbb470942410d348584dc953bcaec926415d3462f471ef350f29b5870a"

S = "${WORKDIR}/${PN}-${PV}"

inherit cmake pkgconfig gettext

do_configure_prepend () {
        sed -i "s|\/\/ #define PUGIXML_HAS_LONG_LONG|#define PUGIXML_HAS_LONG_LONG|" ${S}/src/pugiconfig.hpp
}
