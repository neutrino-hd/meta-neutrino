
include themes.inc

DESCRIPTION = "NI Theme Set"
HOMEPAGE = "https://github.com/neutrino-images/ni-neutrino"
MAINTAINER = "NI-Team"

SRC_URI = "git://github.com/neutrino-images/ni-neutrino.git;protocol=https;subpath=${SUBPATH}"

PR = "r2"
SRCREV = "${AUTOREV}"
PV = "3.60"


do_install () {
	install -d ${D}/var/tuxbox/themes
	install -m 644 ${S}/themes/Material*.theme ${D}/var/tuxbox/themes
	install -m 644 ${S}/themes/Adult*.theme ${D}/var/tuxbox/themes
	install -m 644 ${S}/themes/Dark*.theme ${D}/var/tuxbox/themes
}



