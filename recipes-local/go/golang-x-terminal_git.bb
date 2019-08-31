DESCRIPTION = "Go supplementary libraries"
SECTION = "net"
HOMEPAGE = "https://godoc.org/golang.org/x"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "golang-x-sys"
GO_IMPORT = "golang.org/x/crypto/ssh/terminal"

PROVIDES += "golang-x-terminal"

inherit go

SRC_URI = "\
	git://github.com/golang/crypto.git;protocol=https;name=crypto;destsuffix=${BPN}-${PV}/src/golang.org/x/crypto \
	"

SRCREV_crypto = "${AUTOREV}"
