DESCRIPTION = "libass is a portable subtitle renderer for the ASS/SSA (Advanced Substation Alpha/Substation Alpha) subtitle format. It is mostly compatible with VSFilter."
HOMEPAGE = "http://code.google.com/p/libass/"
SECTION = "libs/multimedia"

LICENSE = "BSD-3-Clause"

DEPENDS = "enca fontconfig freetype libpng fribidi"

SRC_URI = "git://github.com/libass/libass.git;protocol=https"

SRCREV = "98727c3b78f44cb3bbc955fcf5d977ebd911d5ca"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = " \
	--enable-fontconfig \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=a42532a0684420bdb15556c3cdd49a75"

