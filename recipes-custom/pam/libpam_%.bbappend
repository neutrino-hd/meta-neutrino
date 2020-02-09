do_install_append() {
	echo "QTWEBENGINE_DISABLE_SANDBOX=1" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_PLATFORM=eglfs" >> ${D}${sysconfdir}/environment
	echo "QT_SCALE_FACTOR=1" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_EGLFS_WIDTH=1280" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_EGLFS_HEIGHT=720" >> ${D}${sysconfdir}/environment
	echo "XKB_DEFAULT_LAYOUT=de" >> ${D}${sysconfdir}/environment
	echo "TERM=linux" >> ${D}${sysconfdir}/environment
	echo "XDG_RUNTIME_DIR=/tmp" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_FONTDIR=/usr/share/fonts" >> ${D}${sysconfdir}/environment
	echo "QT_LOGGING_RULES=qt.qpa.*=true" >> ${D}${sysconfdir}/environment
	echo "#QT_QPA_GENERIC_PLUGINS=evdevmouse" >> ${D}${sysconfdir}/environment
	echo "#QT_QPA_EVDEV_MOUSE_PARAMETERS=/dev/input/event1:grab" >> ${D}${sysconfdir}/environment
	echo "QTWEBENGINE_CHROMIUM_FLAGS='--enable-gpu-rasterization --num-raster-threads=4'" >> ${D}${sysconfdir}/environment
}
