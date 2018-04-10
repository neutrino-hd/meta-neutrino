DESCRIPTION = "NCurses Disk Usage"
DEPENDS = "ncurses"
PR = "r1"

SRC_URI = "http://dev.yorhel.nl/download/ncdu-${PV}.tar.gz"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit autotools pkgconfig

SRC_URI[md5sum] = "67239592ac41f42290f52ab89ff198be"
SRC_URI[sha256sum] = "f4d9285c38292c2de05e444d0ba271cbfe1a705eee37c2b23ea7c448ab37255a"
