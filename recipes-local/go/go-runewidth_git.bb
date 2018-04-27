SUMMARY = "go-runewidth"
DESCRIPTION = "go runewidth"
HOMEPAGE = "https://github.com/mattn/go-runewidth"
SECTION = "go"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

GO_IMPORT = "github.com/mattn/go-runewidth"

SRC_URI = "git://${GO_IMPORT} \
"

SRCREV = "ce7b0b5c7b45a81508558cd1dba6bb1e4ddb51bb"

inherit go

GO_INSTALL = "${GO_IMPORT}"

