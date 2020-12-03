SUMMARY = "lightweight DLNA/UPnP-AV server targeted at embedded systems"
HOMEPAGE = "http://sourceforge.net/projects/minidlna/"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b1a795ac1a06805cf8fd74920bc46b5c"
DEPENDS = "libexif libjpeg-turbo libid3tag flac libvorbis sqlite3 ffmpeg util-linux virtual/libiconv"

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/minidlna/minidlna/${PV}/minidlna-${PV}.tar.gz \
		file://minidlna.conf \
		file://minidlna.service \
"

SRC_URI[sha256sum] = "47d9b06b4c48801a4c1112ec23d24782728b5495e95ec2195bbe5c81bc2d3c63"

S = "${WORKDIR}/${PN}-${PV}"

inherit autotools-brokensep gettext systemd

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

