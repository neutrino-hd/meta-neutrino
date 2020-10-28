HOMEPAGE = "https://github.com/mighty-p/t2scan"
SUMMARY = "Channel scan tool which generates DVB-T/T2 channels.conf files"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "git://github.com/mighty-p/t2scan.git;protocol=https"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools
