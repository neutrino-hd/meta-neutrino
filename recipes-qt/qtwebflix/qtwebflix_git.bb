SUMMARY = "QtWebflix"
DESCRIPTION = "A viewer for netflix, amazon prime and similar"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

DEPENDS = "qtwebengine qtwidevine"
RDEPENDS_${PN} = "qtwidevine"

SRCREV_qtwebflix = "${AUTOREV}"
SRCREV_qtdbusextended = "34971431233dc408553245001148d34a09836df1"
SRCREV_qtmpris = "7251898353f1f5804c9480172ad7df88c4fe7eb6"
SRCREV_FORMAT = "qtwebflix"


SRC_URI = "git://github.com/gort818/qtwebflix.git;protocol=https;name=qtwebflix \
           git://github.com/nemomobile/qtdbusextended.git;destsuffix=git/lib/qtdbusextended;branch=master;name=qtdbusextended;protocol=https \
           git://git.merproject.org/mer-core/qtmpris.git;destsuffix=git/lib/qtmpris;branch=master;name=qtmpris;protocol=https \
           file://qtwebflix.service \
           file://browser.service \
           file://ardmediathek.service \
           file://zdfmediathek.service \
           file://artemediathek.service \
           file://3satmediathek.service \
           file://youtube.service \
           file://0001-mainwindow.cpp-spoof-in-ChromeOS-useragent-to-fix-pl.patch \
           "


S = "${WORKDIR}/git"

inherit qmake5

do_install() {
	install -d ${D}/usr/bin -d ${D}${systemd_unitdir}/system
	install -m755 ${B}/qtwebflix ${D}/usr/bin
	install -m644 ${WORKDIR}/qtwebflix.service ${D}${systemd_unitdir}/system/qtwebflix.service
	install -m644 ${WORKDIR}/browser.service ${D}${systemd_unitdir}/system/browser.service
	install -m644 ${WORKDIR}/ardmediathek.service ${D}${systemd_unitdir}/system/ardmediathek.service
	install -m644 ${WORKDIR}/zdfmediathek.service ${D}${systemd_unitdir}/system/zdfmediathek.service
	install -m644 ${WORKDIR}/artemediathek.service ${D}${systemd_unitdir}/system/artemediathek.service
        install -m644 ${WORKDIR}/3satmediathek.service ${D}${systemd_unitdir}/system/3satmediathek.service
        install -m644 ${WORKDIR}/youtube.service ${D}${systemd_unitdir}/system/youtube.service
}


FILES_${PN} = "/usr/bin/qtwebflix \
	       /lib/systemd/system \
"

RDEPENDS_${PN} += "qtwebengine qtflashplayer qtwidevine libnss-mdns"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"
