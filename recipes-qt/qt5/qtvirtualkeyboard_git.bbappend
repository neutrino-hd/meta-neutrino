DEPENDS += "patchelf-native"

pkg_postinst_ontarget_${PN}() {
		patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so /usr/lib/libQt5VirtualKeyboard.so*
		for lib in $(find /usr/lib/qml/QtQuick/VirtualKeyboard/* -name '*.so*'); do 
			patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so $lib
		done
}

INSANE_SKIP_${PN} += "file-rdeps"

