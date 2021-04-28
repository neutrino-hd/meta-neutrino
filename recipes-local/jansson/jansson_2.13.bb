SUMMARY = "Jansson is a C library for encoding, decoding and manipulating JSON data"
HOMEPAGE = "http://www.digip.org/jansson/"
BUGTRACKER = "https://github.com/akheron/jansson/issues"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=afd92c4cfc08f4896003251b878cc0bf"

SRC_URI = "http://www.digip.org/jansson/releases/${BPN}-${PV}.tar.gz"

SRC_URI[md5sum] = "ae993e7eda4fec5ce23ce1a191fcef32"
SRC_URI[sha256sum] = "02c31bc16e702b30feb06d18bbfe086c0d8c938e906950980af7adcdb324541b"

inherit autotools pkgconfig

