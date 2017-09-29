SUMMARY = "Neutrino MP"
DESCRIPTION = "Neutrino-MP for AX HD51 Platform."
HOMEPAGE = "http://www.tuxbox.org"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PKGV = "${GITPKGVTAG}"

PATCHTOOL = "git"

SRC_URI = "git://github.com/TangoCash/neutrino-mp-cst-next.git;protocol=http \
	file://neutrino.ttf \
	file://neutrino.service \
	file://neutrino.sh \
	file://timezone.xml \
	file://custom-poweroff.init \
	file://COPYING.GPL \
	file://pre-wlan0.sh \
	file://post-wlan0.sh \
	file://mount.mdev \
	file://icons.tar.gz \
	file://var.tar.gz \
"

include neutrino.inc

EXTRA_OECONF_append += "--with-boxtype=armbox \
						--with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"

do_configure_prepend() {
	cp ${WORKDIR}/neutrino.ttf ${S}/data/fonts
}
