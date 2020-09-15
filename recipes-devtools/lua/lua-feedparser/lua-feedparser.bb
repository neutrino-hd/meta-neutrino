DESCRIPTION = "lua-feedparser"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b8e3c7d92765c11f200410b0e53c8bdb"
HOMEPAGE = "https://github.com/slact/lua-feedparser"

include ../lua.inc

RDEPENDS_${PN} += "virtual/lua"
SRCREV = "9b284bc014ea6adbbd847b16ba64dadd40724fac"
PR = "r1"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/slact/lua-feedparser.git;protocol=git;branch=master \
	   file://0001-lua-feedparser-adjust-Makefile-for-OE.patch \
"

SRC_URI[md5sum] = "9da3eb618aaf1547a1a75504c8185cee"
SRC_URI[sha256sum] = "efe74a0ff7375ee5fe459aefff723c0efd5ebba7d05de34f7ebc334147c0731b"

EXTRA_OEMAKE += "LUA_VER=${LUA_VER} DESTDIR=${D}"

inherit autotools-brokensep

FILES_${PN} += "/usr/share"

BBCLASSEXTEND = "native nativesdk"

