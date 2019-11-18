do_install_append() {
	echo "QTWEBENGINE_DISABLE_SANDBOX=1" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_PLATFORM=eglfs" >> ${D}${sysconfdir}/environment
	echo "QTWEBENGINE_CHROMIUM_FLAGS=--lang=de-DE"
	echo "QT_QPA_EGLFS_WIDTH=1920" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_EGLFS_HEIGHT=1080" >> ${D}${sysconfdir}/environment
}
