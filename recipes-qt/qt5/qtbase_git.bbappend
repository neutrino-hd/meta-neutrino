
RDEPENDS_${PN} += "patchelf virtual/libgles2"

PACKAGECONFIG_remove = "${PACKAGECONFIG_GL}"

PACKAGECONFIG += "freetype udev eglfs dbus release accessibility optimize-size gles2 openssl journald libinput xkbcommon"


inherit ccache pkgconfig

INSANE_SKIP_${PN} += "file-rdeps"
INSANE_SKIP_${PN}-plugins += "file-rdeps"
