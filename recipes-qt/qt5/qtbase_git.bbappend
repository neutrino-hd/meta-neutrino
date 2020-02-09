
RDEPENDS_${PN} += "patchelf virtual/libgles2"

PACKAGECONFIG_remove = "${PACKAGECONFIG_GL}"

PACKAGECONFIG += "freetype udev eglfs dbus release accessibility optimize-size gles2 openssl journald libinput xkbcommon"

pkg_postinst_ontarget_${PN}() {
		patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so /usr/lib/libQt*
		patchelf --replace-needed ${STAGING_LIBDIR}/libEGL.so libEGL.so /usr/lib/libQt*
}

inherit ccache pkgconfig

INSANE_SKIP_${PN} +="file-rdeps"
INSANE_SKIP_${PN}-plugins +="file-rdeps"
