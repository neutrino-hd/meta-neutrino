SUMMARY = "libde265 - x65 decoder"
HOMEPAGE = "github.com/strukturag/libde265.git"
SECTION = "libs"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=852f345c1c52c9160f9a7c36bb997546"

SRC_URI = "git://github.com/strukturag/libde265.git;protocol=https"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

CXXFLAGS_append += "-fPIC"

