do_install_append() {
	echo "QTWEBENGINE_DISABLE_SANDBOX=1" >> ${D}${sysconfdir}/environment
	echo "QT_QPA_PLATFORM=eglfs" >> ${D}${sysconfdir}/environment
}
