do_install_append() {
	echo "QTWEBENGINE_DISABLE_SANDBOX=1" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_PLATFORM=eglfs" >> ${D}${sysconfdir}/environment
	echo "QT_SCALE_FACTOR=1" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_EGLFS_WIDTH=1280" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_EGLFS_HEIGHT=720" >> ${D}${sysconfdir}/environment
	echo "XKB_DEFAULT_LAYOUT=de" >> ${D}${sysconfdir}/environment
	echo "TERM=linux" >> ${D}${sysconfdir}/environment
}
