
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
DEPENDS += "zlib bzip2 xz xz-native lzo e2fsprogs util-linux libgcrypt"

SRC_URI = "git://github.com/fdupoux/fsarchiver.git;protocol=https \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += "--disable-lz4 \
		 --disable-zstd \
"
