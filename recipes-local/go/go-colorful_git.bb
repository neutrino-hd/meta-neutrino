SUMMARY = "go-colorful"
DESCRIPTION = "A library for playing with colors in go (golang)."
HOMEPAGE = "https://github.com/lucasb-eyer/go-colorful.git"
SECTION = "go"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

GO_IMPORT = "github.com/lucasb-eyer/go-colorful"

SRC_URI = "git://${GO_IMPORT} \
"

SRCREV = "${AUTOREV}"

inherit go

GO_INSTALL = "${GO_IMPORT}"

