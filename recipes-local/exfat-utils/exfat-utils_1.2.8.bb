SUMMARY = "utilities to create, check, label and dump exFAT filesystem"
DESCRIPTION = "Utilities to manage extended file allocation table filesystem. \
This package provides tools to create, check and label the filesystem. It \
contains \
 - dumpexfat to dump properties of the filesystem \
 - exfatfsck / fsck.exfat to report errors found on a exFAT filesystem \
 - exfatlabel to label a exFAT filesystem \
 - mkexfatfs / mkfs.exfat to create a exFAT filesystem. \
"
HOMEPAGE = "http://code.google.com/p/exfat/"
SECTION = "universe/otherosfs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://github.com/relan/exfat/releases/download/v${PV}/${BP}.tar.gz"

DEPENDS = "virtual/libc"

inherit pkgconfig autotools

SRC_URI[md5sum] = "00953eb7f704a875e26d7d01cc738fd3"
SRC_URI[sha256sum] = "5c1643d23d24663b4e483292a643a791d2af7269870cac2f781c5dfe6a20ce27"

do_install_append() {
	install -d ${D}/sbin
	ln -s /usr/sbin/mkfs.exfat ${D}/sbin/mkfs.exfat
        ln -s /usr/sbin/fsck.exfat ${D}/sbin/fsck.exfat
}
