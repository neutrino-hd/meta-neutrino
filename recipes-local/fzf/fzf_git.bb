SUMMARY = "fzf"
DESCRIPTION = "A command-line fuzzy finder"
HOMEPAGE = "https://github.com/junegunn/fzf.git"
SECTION = "base/shell"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=04a8c5d840f2f1a07d28ce3e19951303"

DEPENDS = "go-native"
SRC_URI = "git://github.com/junegunn/fzf.git;protocol=https \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit go
