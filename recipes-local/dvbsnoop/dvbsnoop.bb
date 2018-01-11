DESCRIPTION = "DVB / MPEG stream analyzer"
SUMMARY = "DVB / MPEG stream analyzer"
MAINTAINER = "Persian Professionals"
AUTHOR = "Rainer Scherg <rasc@users.sourceforge.net>"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit gitpkgv

PV = "1.4.53"
PKGV = "1.4.53+git${GITPKGV}"

SRC_URI = "git://github.com/Duckbox-Developers/dvbsnoop.git;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"

inherit autotools
