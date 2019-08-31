DESCRIPTION = "Go supplementary libraries"
SECTION = "net"
HOMEPAGE = "https://godoc.org/golang.org/x"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

GO_IMPORT = "golang.org/x"

PROVIDES += "golang.org-x-crypto"
PROVIDES += "golang.org-x-text"

inherit go

SRC_URI = "\
	git://github.com/golang/net.git;protocol=https;name=net;destsuffix=${BPN}-${PV}/src/golang.org/x/net \
	git://github.com/golang/crypto.git;protocol=https;name=crypto;destsuffix=${BPN}-${PV}/src/golang.org/x/crypto \
	git://github.com/golang/text.git;protocol=https;name=text;destsuffix=${BPN}-${PV}/src/golang.org/x/text \
	"

SRCREV_net = "${AUTOREV}"
SRCREV_crypto = "${AUTOREV}"
SRCREV_text = "${AUTOREV}"
SRCREV_FORMAT = "git"