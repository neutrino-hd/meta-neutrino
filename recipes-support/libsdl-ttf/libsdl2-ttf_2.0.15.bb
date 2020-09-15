SUMMARY = "Simple DirectMedia Layer truetype font library"
SECTION = "libs"
DEPENDS = "virtual/libsdl2 freetype"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=4bb27d550fdafcd8f8e4fb8cbb2775ef"

SRC_URI = " \
    http://www.libsdl.org/projects/SDL_ttf/release/SDL2_ttf-${PV}.tar.gz \
    file://use.pkg-config.for.freetype2.patch;apply=no \
"
SRC_URI[sha256sum] = "a9eceb1ad88c1f1545cd7bd28e7cbc0b2c14191d40238f531a15b01b1b22cd33"


S = "${WORKDIR}/SDL2_ttf-${PV}"

inherit autotools pkgconfig

do_configure_prepend() {
    touch ${S}/NEWS ${S}/README ${S}/AUTHORS ${S}/ChangeLog

    # Removing these files fixes a libtool version mismatch.
    MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"

    for i in ${MACROS}; do
        rm -f ${S}/acinclude/$i
    done
}
