SUMMARY = "AdvanceMame Arcade emulator"
HOMEPAGE = "https://www.advancemame.it/"
SECTION = "emulators"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "git://github.com/amadvance/${BPN}.git;protocol=https"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

DEPENDS = "virtual/libsdl2 alsa-lib ncurses freetype zlib expat"
RDEPENDS_${PN} = "steam-devices"

inherit autotools-brokensep pkgconfig

do_configure_prepend() {
    # Upstream doesn't ship this and autoreconf won't install it as automake isn't used.
    cp -f $(automake --print-libdir)/install-sh ${S}/
}

FILES_${PN} += "${datadir}/advance/*"
FILES_${PN}-doc += "${prefix}/doc/* ${prefix}/man/*"
