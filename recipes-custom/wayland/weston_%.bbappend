REQUIRED_DISTRO_FEATURES = ""

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'kms fbdev wayland egl', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'xwayland', '', d)} \
                   ${@bb.utils.filter('DISTRO_FEATURES', 'pam systemd x11', d)} \
                   ${@bb.utils.contains_any('DISTRO_FEATURES', 'wayland x11', '', 'headless', d)} \
                   launch"

EXTRA_OECONF = " \
                --disable-rdp-compositor \
		WESTON_NATIVE_BACKEND=fbdev-backend.so \
		"

RDEPENDS_${PN} += "weston-init"
