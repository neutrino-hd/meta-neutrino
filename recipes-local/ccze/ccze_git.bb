SUMMARY = "ccze"
DESCRIPTION = "Colors for your logs"
HOMEPAGE = "https://github.com/cornet/ccze.git"
SECTION = "base/shell"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "git://github.com/cornet/ccze.git;protocol=https \
"

DEPENDS = "ncurses pcre"
SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
