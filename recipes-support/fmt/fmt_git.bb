SUMMARY = "open-source formatting library for C++"
DESCRIPTION = "{fmt} is an open-source formatting library for C++. It can be used as a safe and fast alternative to (s)printf and iostreams."
HOMEPAGE = "https://fmt.dev"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=af88d758f75f3c5c48a967501f24384b"

SRC_URI += "git://github.com/fmtlib/fmt"
SRCREV = "e50ced88c6f0db6c7c2e16cfb9aec4c9ab4df09c"

S = "${WORKDIR}/git"

inherit cmake
inherit ptest

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"
