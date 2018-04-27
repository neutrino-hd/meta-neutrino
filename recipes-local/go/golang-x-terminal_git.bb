DESCRIPTION = "Go supplementary libraries"
SECTION = "net"
HOMEPAGE = "https://godoc.org/golang.org/x"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

GO_IMPORT = "golang.org/x/crypto/ssh/terminal"

PROVIDES += "golang.org-x-terminal"

inherit go

SRC_URI = "\
	git://github.com/golang/crypto.git;protocol=https;name=crypto;destsuffix=${PN}-${PV}/src/golang.org/x/crypto \
	"

SRCREV_crypto = "a548aac93ed489257b9d959b40fe1e8c1e20778c"
