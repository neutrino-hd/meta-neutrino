inherit ccache

pkg_postinst_ontarget_${PN}() {
		for lib in $(find /usr/lib/qml/QtGraphicalEffects/* -name '*.so*'); do 
			patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so libGLESv2.so $lib
		done
}
