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
	git://github.com/golang/net.git;protocol=https;name=net;destsuffix=${PN}-${PV}/src/golang.org/x/net \
	git://github.com/golang/crypto.git;protocol=https;name=crypto;destsuffix=${PN}-${PV}/src/golang.org/x/crypto \
	git://github.com/golang/text.git;protocol=https;name=text;destsuffix=${PN}-${PV}/src/golang.org/x/text \
	"

SRCREV_net = "07b51741c1d6423d4a6abab1c49940ec09cb1aaf"
SRCREV_crypto = "a548aac93ed489257b9d959b40fe1e8c1e20778c"
SRCREV_text = "d69c40b4be55797923cec7457fac7a244d91a9b6"
