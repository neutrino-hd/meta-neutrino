SUMMARY = "go-isatty"
DESCRIPTION = "A command-line fuzzy finder"
HOMEPAGE = "https://github.com/mattn/go-shellwords"
SECTION = "go"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

GO_IMPORT = "github.com/mattn/go-shellwords"

SRC_URI = "git://${GO_IMPORT} \
"

SRCREV = "${AUTOREV}"

inherit go

GO_INSTALL = "${GO_IMPORT}"

