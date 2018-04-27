SUMMARY = "fzf"
DESCRIPTION = "A command-line fuzzy finder"
HOMEPAGE = "https://github.com/junegunn/fzf.git"
SECTION = "base/shell"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "go-shellwords go-runewidth go-isatty golang-x-terminal"

GO_IMPORT = "github.com/junegunn/fzf"

SRC_URI = "git://${GO_IMPORT} \
"

SRCREV = "6eac4af7db54b2b736a2ede928b14456c0496711"

inherit go

GO_INSTALL = "${GO_IMPORT}"

