SUMMARY = "Tvheadend TV streaming server"
HOMEPAGE = "https://www.lonelycoder.com/redmine/projects/tvheadend"

DEPENDS = "avahi cmake-native dvb-apps libdvbcsa libpcre2 openssl uriparser zlib nasm"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=9cae5acac2e9ee2fc3aec01ac88ce5db"

SRC_URI = "git://github.com/tvheadend/tvheadend.git \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit gettext autotools-brokensep pkgconfig

EXTRA_OECONF += "--arch=${TARGET_ARCH} \
                 --disable-hdhomerun_static \
                 --disable-ffmpeg_static \
                 --disable-libav \
                 --python=python3 \
                 "


CLEANBROKEN = "1"

