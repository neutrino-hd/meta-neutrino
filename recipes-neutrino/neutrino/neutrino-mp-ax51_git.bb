SUMMARY = "Neutrino MP"
DESCRIPTION = "Neutrino-MP for AX HD51 Platform."
HOMEPAGE = "http://www.tuxbox.org"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

SRC_URI = "git://github.com/TangoCash/neutrino-mp-cst-next.git;branch=master;protocol=http \
	file://0001-remove_workaround_for_gcc6.x.patch \
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

include neutrino-mp.inc

EXTRA_OECONF_append += "--with-boxtype=armbox \
						--with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \"

