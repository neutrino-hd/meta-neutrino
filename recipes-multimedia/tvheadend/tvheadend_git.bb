SUMMARY = "Tvheadend TV streaming server"
HOMEPAGE = "https://www.lonelycoder.com/redmine/projects/tvheadend"

DEPENDS = "avahi cmake-native dvb-apps libdvbcsa libopus libpcre2 openssl uriparser zlib ffmpeg nasm"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=9cae5acac2e9ee2fc3aec01ac88ce5db"

SRC_URI = "git://github.com/tvheadend/tvheadend.git \
	   file://tvheadend.service \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit gettext autotools-brokensep pkgconfig

EXTRA_OECONF += "--arch=${TARGET_ARCH} \
		--enable-nvenc \
		--enable-cardclient \
		--enable-mmal \
		--enable-ffmpeg \
		--disable-ffmpeg_static \
		--enable-inotify \
		--enable-pcre2 \
		--enable-uriparser \
		--enable-tvhcsa \
		--enable-bundle \
		--enable-dvbcsa \
		--enable-kqueue \
		--enable-libvpx \
		--enable-libopus \
		--enable-ddci \
		--python=python3 \
                 "


do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/tvheadend.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${systemd_unitdir}"

CLEANBROKEN = "1"

