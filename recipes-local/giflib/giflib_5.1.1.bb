DESCRIPTION = "shared library for GIF images"
SECTION = "libs"
LICENSE = "MIT"
PR = "r3"

LIC_FILES_CHKSUM = "file://COPYING;md5=ae11c61b04b2917be39b11f78d71519a"

SRC_URI = "${SOURCEFORGE_MIRROR}/giflib/giflib-${PV}.tar.bz2"
SRC_URI[md5sum] = "cb530d737c8f2d1023797cf0587b4e05"
SRC_URI[sha256sum] = "7a07d3f7cca5c0b38ca811984ef8da536da32932d68c1a6cce33ec2462b930bf"

inherit autotools lib_package

PACKAGES += "${PN}-utils"

FILES_${PN}-utils = "${bindir}/*"
