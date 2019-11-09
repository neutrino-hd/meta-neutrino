SUMMARY = "QtWebflix"
DESCRIPTION = "A viewer for netflix, amazon prime and similar"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=84dcc94da3adb52b53ae4fa38fe49e5d"
DEPENDS = "qtwebengine"

SRCREV_qtwebflix = "${AUTOREV}"
SRCREV_qtdbusextended = "34971431233dc408553245001148d34a09836df1"
SRCREV_qtmpris = "7251898353f1f5804c9480172ad7df88c4fe7eb6"
SRCREV_FORMAT = "qtwebflix"


SRC_URI = "git://github.com/gort818/qtwebflix.git;protocol=https;name=qtwebflix \
           git://github.com/nemomobile/qtdbusextended.git;destsuffix=git/lib/qtdbusextended;branch=master;name=qtdbusextended;protocol=https \
           git://git.merproject.org/mer-core/qtmpris.git;destsuffix=git/lib/qtmpris;branch=master;name=qtmpris;protocol=https \
           file://qtwebflix.service \
"


S = "${WORKDIR}/git"

inherit qmake5

do_install() {
	install -d ${D}/usr/bin -d ${D}${systemd_unitdir}/system/multi-user.target.wants
	install -m755 ${B}/qtwebflix ${D}/usr/bin
	install -m644 ${WORKDIR}/qtwebflix.service ${D}${systemd_unitdir}/system/qtwebflix.service
}


FILES_${PN} = "/usr/bin/qtwebflix \
				/lib/systemd/system \
"

RDEPENDS_${PN} += "qtwebengine"

pkg_postinst_ontarget_${PN}() {
patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so ${libdir}/libGLESv2.so /usr/bin/qtwebflix
patchelf --replace-needed ${STAGING_LIBDIR}/libGLESv2.so ${libdir}/libGLESv2.so /usr/bin/qtwebflix
}


PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"
