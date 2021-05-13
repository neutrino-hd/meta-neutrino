
include themes.inc

DESCRIPTION = "${PRE_DESCRIPTION}"
HOMEPAGE = "https://github.com/TangoCash/neutrino-tangos"
MAINTAINER = "Tango"
SUMMARY = "${DESCRIPTION} by ${MAINTAINER}"

SRC_URI = "git://github.com/TangoCash/neutrino-tangos.git;protocol=https;subpath=${SUBPATH}"

PR = "r3"
PV = "git-${SRCPV}"

do_install () {
	install -d ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/GlassHD.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/Lechuk.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/Sportster.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/TangoCash.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/adtheme.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/bgfade.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/colors.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/dbox-all.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/megasat4000.theme ${D}/var/tuxbox/themes
        install -m 644 ${S}/themes/nesh.theme ${D}/var/tuxbox/themes
}


FILES_${PN} = "\
    /var/tuxbox/themes \
"

