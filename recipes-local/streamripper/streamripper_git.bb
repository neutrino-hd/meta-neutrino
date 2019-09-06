SUMMARY = "download online streams into audio files"
DESCRIPTION = "This command-line tool can be used to record MPEG III \
and OGG online radio-streams into track-separated audio files."
HOMEPAGE = "http://streamripper.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
DEPENDS = "glib-2.0 tremor libogg"

SRC_URI = "\
    git://github.com/neutrino-images/ni-streamripper.git;protocol=https \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += "--with-included-argv=yes --with-included-libmad=no"
EXTRA_OECONF += "\
    --with-ogg-includes=${STAGING_INCDIR} \
    --with-ogg-libraries=${STAGING_LIBDIR} \
    --with-vorbis-includes=${STAGING_INCDIR} \
    --with-vorbis-libraries=${STAGING_LIBDIR} \
"

# the included argv library needs this
CPPFLAGS_append = " -DANSI_PROTOTYPES"
