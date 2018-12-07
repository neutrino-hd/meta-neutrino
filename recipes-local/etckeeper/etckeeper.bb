LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PN} += "git findutils util-linux-mountpoint perl-module-file-glob glibc-utils"

SRC_URI = "git://github.com/neutrino-hd/etckeeper.git;protocol=https \
           file://etckeeper.conf \
"


SRC_URI[md5sum] = "439d65fc487910a30b686788b7c6fc99"
SRC_URI[sha256sum] = "76fd0349ff138b98a4dde831a23a13d3fc6608147ef4fef35ce58ebf48f18f23"

SRCREV = "15ad5bd450b81a65ba50ce99c97615b183cc032d"
PV = "${SRCPV}"
PR = "1"

S = "${WORKDIR}/git"

inherit autotools-brokensep systemd

do_install_append () {
	install -d ${D}${systemd_unitdir}/system/timers.target.wants
	install -m 644 ${WORKDIR}/etckeeper.conf ${D}/etc/etckeeper
	ln -s /lib/systemd/system/etckeeper.timer ${D}${systemd_unitdir}/system/timers.target.wants/etckeeper.timer
}

FILES_${PN}_append += "/lib/systemd \
		       /usr/share/bash-completion \
		       /usr/lib/ \
"

pkg_postinst_ontarget_${PN} () {
	/usr/bin/etckeeper init
}
