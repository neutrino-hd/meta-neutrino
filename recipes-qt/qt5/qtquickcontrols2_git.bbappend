inherit ccache

pkg_postinst_ontarget_${PN}() {
		patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so /usr/lib/libQt*
		patchelf --replace-needed ${STAGING_LIBDIR}/libEGL.so libEGL.so /usr/lib/libQt*
		for lib in $(find /usr/lib/qml/QtQuick/*.2 -name '*.so*'); do 
			patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so $lib
		done
		for lib in $(find /usr/lib/qml/Qt/* -name '*.so*'); do 
			patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so $lib
		done
}