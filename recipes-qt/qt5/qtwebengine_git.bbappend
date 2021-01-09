FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebengine:"

SRC_URI_append = "file://0001-qtwebengine-set-user-agent.patch \
		  file://chromium/0004-enable-openmax.patch;patchdir=src/3rdparty \
		  file://chromium/0005-mediahost.patch;patchdir=src/3rdparty \
"

SRCREV_qtwebengine = "f5268555099a1a96f8730035a40f04a473dd396e"
SRCREV_chromium = "7b2f027ea83c372c33d5b50deb65a2d98244aa04"

SRC_URI_remove = "file://chromium/0021-chromium-Fix-build-on-32bit-arches-with-64bit-time_t.patch;patchdir=src/3rdparty"

inherit ccache

PACKAGECONFIG_append += "libwebp libvpx opus ffmpeg proprietary-codecs pepper-plugins webrtc"

DEPENDS_append = "libnss-mdns"
 
 
