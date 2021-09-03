
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DESCRIPTION = "Required data files and fonts for neutrino plugins."
MAINTAINER = "${FLAVOR}-developers"
HOMEPAGE = ""

SUMMARY = "${DESCRIPTION}"

PV = "git+${SRCPV}"

SRCREV = "${AUTOREV}"

SRC_URI = " \
	git://github.com/neutrino-images/ni-neutrino-plugins.git;protocol=https;subpath=data;destsuffix=git/data \
"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${datadir}/fonts
	install -m 0644 ${S}/data/fonts/* ${D}${datadir}/fonts/
	rm -f ${D}${datadir}/fonts/Makefile*
}

FILES_${PN} = "/usr \
	       /etc \
"
