
DESCRIPTION = "Additional upgrade script for the Open Package Manager (OPKG)"
HOMEPAGE = "https://github.com/tuxbox-neutrino"
MAINTAINER = "Tuxbox-Developers"
LICENSE = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=4ae09d45eac4aa08d013b5f2e01c67f6"

PACKAGE_ARCH = "all"

DEPENDS = "opkg"

SRC_URI = "git://github.com/dbt1/opkg-upgrade.git;protocol=https"

PR = "r0"
PV = "0.3.6-git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${bindir}
        install -m 755 ${S}/opkg-upgrade.sh ${D}${bindir}
        install -m 755 ${S}/system-update ${D}${bindir}
}

# FILES_${PN} += " \
# 	/usr/bin \
# "
