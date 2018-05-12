SUMMARY = "go-encoding-adds"
DESCRIPTION = "Various character map encodings missing from golang.org/x/net/encoding"
HOMEPAGE = "https://github.com/gdamore/encoding.git"
SECTION = "go"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "golang-x-net"

GO_IMPORT = "github.com/gdamore/encoding"

SRC_URI = "git://${GO_IMPORT} \
"

SRCREV = "b23993cbb6353f0e6aa98d0ee318a34728f628b9"

inherit go

GO_INSTALL = "${GO_IMPORT}"
