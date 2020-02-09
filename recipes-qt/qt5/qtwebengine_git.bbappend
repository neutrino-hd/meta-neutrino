FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebengine:"

SRC_URI_append = "file://0001-qtwebengine-set-user-agent.patch"

inherit ccache

PACKAGECONFIG_append += "ffmpeg libwebp opus libvpx proprietary-codecs pepper-plugins webrtc"

DEPENDS += "libnss-mdns"

pkg_postinst_ontarget_${PN}() {
		patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so /usr/lib/libQt*
		patchelf --replace-needed ${STAGING_LIBDIR}/libEGL.so libEGL.so /usr/lib/libQt*
}
