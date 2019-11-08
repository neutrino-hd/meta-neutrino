
RDEPENDS_${PN} += "patchelf virtual/libgles2"

PACKAGECONFIG_remove = "${PACKAGECONFIG_GL}"

PACKAGECONFIG += "freetype udev eglfs dbus release accessibility optimize-size gles2 openssl journald libinput xkbcommon"


inherit ccache pkgconfig


pkg_postinst_ontarget_${PN}() {
		patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so ${libdir}/libGLESv2.so /usr/lib/libQt*
		patchelf --replace-needed ${STAGING_LIBDIR}/libEGL.so ${libdir}/libEGL.so /usr/lib/libQt*
}
