DESCRIPTION = "Go supplementary libraries"
SECTION = "net"
HOMEPAGE = "https://godoc.org/golang.org/x"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

GO_IMPORT = "golang.org/x/sys"

PROVIDES += "golang-x-sys"
RDEPENDS_${PN}-dev += "bash"

inherit go

SRC_URI = "\
	git://github.com/golang/sys.git;protocol=https;name=sys;destsuffix=${BPN}-${PV}/src/golang.org/x/sys \
	"

SRCREV_sys = "ce4227a45e2eb77e5c847278dcc6a626742e2945"
