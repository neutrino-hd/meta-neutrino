FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebengine:"

SRC_URI_append = "file://0001-qtwebengine-set-user-agent.patch"

SRC_URI_remove = "file://chromium/0021-chromium-Fix-build-on-32bit-arches-with-64bit-time_t.patch;patchdir=src/3rdparty"

inherit ccache

PACKAGECONFIG_append += "libwebp libvpx opus ffmpeg proprietary-codecs pepper-plugins webrtc"

DEPENDS_append = "libnss-mdns"
 
