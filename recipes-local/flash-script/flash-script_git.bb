
DESCRIPTION = "Flash script for ${MACHINE_BRAND}-${MACHINE}"
HOMEPAGE = "https://github.com/tuxbox-neutrino"
MAINTAINER = "Tuxbox-Developers"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=285b6276c3a2d7b9bb2783a4ef5af8d4"

PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_FEATURES += " ${PN} "

SRC_URI = "git://github.com/tuxbox-neutrino/flash-script.git;branch=${MACHINE};protocol=https"

PR = "r0"
PV = "0.1+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${bindir}
        install -m 755 ${S}/flash ${D}${bindir}
}

# FILES_${PN} += " \
# 	/usr/bin \
# "
