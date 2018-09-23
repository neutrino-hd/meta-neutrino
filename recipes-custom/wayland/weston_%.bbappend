REQUIRED_DISTRO_FEATURES = ""

EXTRA_OECONF = "--enable-setuid-install \
                --disable-rdp-compositor \
		WESTON_NATIVE_BACKEND=fbdev-backend.so \
		"

PACKAGECONFIG ??= "fbdev clients launch"
