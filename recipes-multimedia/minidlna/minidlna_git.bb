SUMMARY = "lightweight DLNA/UPnP-AV server targeted at embedded systems"
HOMEPAGE = "http://sourceforge.net/projects/minidlna/"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b1a795ac1a06805cf8fd74920bc46b5c"
DEPENDS = "libexif libjpeg-turbo libid3tag flac libvorbis sqlite3 ffmpeg util-linux virtual/libiconv"


VERSION = "1"
MINOR = "3"
MICRO = "0"
PV = "${VERSION}.${MINOR}.${MICRO}"
PR = "r2"

SRC_URI = " \
	git://git.code.sf.net/p/minidlna/git;protokoll=https;tag=v${VERSION}_${MINOR}_${MICRO} \
	file://minidlna.conf \
	file://minidlna.service \
	file://0001-Update-Gettext-version.patch \
	file://0002-Revert-Fix-some-build-warnings-when-building-with-mu.patch \
"

S = "${WORKDIR}/git"

inherit autotools-brokensep gettext systemd

# This remove "--exclude=autopoint" option from autoreconf argument to avoid
# configure.ac:30: error: required file './ABOUT-NLS' not found
EXTRA_AUTORECONF = ""

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "${bindir}/test*"

CONFFILES_${PN} = "${sysconfdir}/minidlna.conf"

SYSTEMD_SERVICE_${PN} = "minidlna.service"

CFLAGS += "-fcommon"

do_configure_prepend() {
	sed -i "s|Coolstream|${MACHINE}|" ${WORKDIR}/minidlna.conf
}

do_install_append() {
	install -d ${D}${sysconfdir} ${D}${systemd_unitdir}/system ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
	install -m 644 ${WORKDIR}/minidlna.conf ${D}${sysconfdir}/minidlna.conf
	install -m 644 ${WORKDIR}/minidlna.service ${D}${systemd_unitdir}/system/minidlna.service
	ln -sf ${systemd_unitdir}/system/minidlna.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/minidlna.service
}

