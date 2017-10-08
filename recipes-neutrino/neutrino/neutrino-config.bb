SUMMARY = "Neutrino MP"
DESCRIPTION = "Neutrino-MP for AX HD51 Platform."
HOMEPAGE = "http://www.tuxbox.org"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license;md5=d41d8cd98f00b204e9800998ecf8427e"


SRC_URI = "file://etc.tar.gz \
		   file://license \
"

do_install() {
	cp -rf ${WORKDIR}/etc ${D}	
}

FILES_${PN} = "/etc \
"