SUMMARY = "Mednafen is a portable, command line driven multi-system emulator"
HOMEPAGE = "https://mednafen.github.io/"
SECTION = "emulators"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "\
           https://mednafen.github.io/releases/files/${BPN}-${PV}.tar.xz \
           file://build-without-intl.patch \
           "

SRC_URI[sha256sum] = "842907c25c4292c9ba497c9cb9229c7d10e04e22cb4740d154ab690e6587fdf4"

S = "${WORKDIR}/mednafen"

DEPENDS = "zlib alsa-lib pkgconfig libsndfile1 virtual/libsdl2"

inherit autotools-brokensep pkgconfig gettext
