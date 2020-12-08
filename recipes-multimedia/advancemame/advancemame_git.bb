SUMMARY = "AdvanceMame Arcade emulator"
HOMEPAGE = "https://www.advancemame.it/"
SECTION = "emulators"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "git://github.com/amadvance/${BPN}.git;protocol=https \
	   file://advmame.rc \
	   file://advmame@.service \
	   file://advmame.cfg \
	   file://advmame.lua \
	   file://advmame_hint.png \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

DEPENDS = "virtual/libsdl2 alsa-lib ncurses freetype zlib expat"

inherit autotools-brokensep pkgconfig gettext

do_configure_prepend() {
    # Upstream doesn't ship this and autoreconf won't install it as automake isn't used.
    cp -f $(automake --print-libdir)/install-sh ${S}/
}

do_install_append() {
	install -d ${D}${sysconfdir} -d ${D}${systemd_unitdir}/system -d ${D}${datadir}/tuxbox/neutrino/plugins
	install -m644 ${WORKDIR}/advmame.rc ${D}${sysconfdir}
	install -m644 ${WORKDIR}/advmame@.service ${D}${systemd_unitdir}/system
	install -m644 ${WORKDIR}/advmame.cfg ${D}${datadir}/tuxbox/neutrino/plugins
	install -m644 ${WORKDIR}/advmame.lua ${D}${datadir}/tuxbox/neutrino/plugins
	install -m644 ${WORKDIR}/advmame_hint.png ${D}${datadir}/tuxbox/neutrino/plugins
}

FILES_${PN} += "${datadir} \
		${sysconfdir} \
		${base_libdir} \
"

FILES_${PN}-doc += "${prefix}/doc/* ${prefix}/man/*"

