DESCRIPTION = "Utilities for generating documentation from source code"
HOMEPAGE = "http://www.doxygen.org/"
SECTION = "console/utils"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
 
DEPENDS = "flex-native bison-native virtual/libiconv"
 
SRC_URI = "git://github.com/doxygen/doxygen.git;protocol=https"

SRCREV = "${AUTOREV}"

S ="${WORKDIR}/git"

inherit cmake pythonnative

EXTRA_OECONF = "--prefix ${prefix}"

BBCLASSEXTEND = "native"
