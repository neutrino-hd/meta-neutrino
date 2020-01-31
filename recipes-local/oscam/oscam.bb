SUMMARY = "OSCam: Open Source Conditional Access Module"
HOMEPAGE = "http://www.streamboard.tv/oscam/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libusb1 openssl pcsc-lite"

DEPENDS_APPEND_libc-uclibc += "virtual/libstb-hal"

SRC_URI = "git://github.com/Schimmelreiter/oscam-smod.git;protocol=https \
           file://oscam.service \
           file://oscam.conf \
"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

S = "${WORKDIR}/git"

INHIBIT_PACKAGE_STRIP = "1"

inherit cmake systemd

SYSTEMD_SERVICE_${PN} = "oscam.service"

EXTRA_OECMAKE = " \
	-DOSCAM_SYSTEM_NAME=tuxbox \
	-DCS_SVN_VERSION="${SRCPV}" \
	-DDEFAULT_CS_CONFDIR="/etc/neutrino/config" \
	-DWEBIF=1 \
	-DLCDSUPPORT=1 \
	-DUSE_LIBCRYPTO=1 \
	-DUSE_LIBUSB=1 \
	-DUSE_STAPI=0 \
	-DREADER_IRDETO=1 \
	-DREADER_NAGRA=1 \
	-DREADER_SECA=1 \
	-DREADER_CRYPTOWORKS=1 \
	-DREADER_CONAX=1 \
	-DREADER_VIACCESS=1 \
	-DREADER_VIDEOGUARD=1 \
	-DMODULE_CAMD35=1 \
	-DMODULE_GBOX=1 \
	-DMODULE_CCCAM=1 \
	-DMODULE_CCCSHARE=1 \
	-DCARDREADER_SC8IN1=1 \
	-DCARDREADER_SMARGO=1 \
	-DWITH_SSL=1 \
	-DWITH_NEUTRINO=1 \                
	-DHAVE_PCSC=1 \
	-DWITH_EMU=0 \
"

do_install () {
        install -d ${D}/usr/bin ${D}${systemd_system_unitdir} ${D}${sysconfdir}/neutrino/config ${D}/${sysconfdir}/keys
        install -m 755 ${WORKDIR}/build/oscam ${D}/usr/bin/oscam.internal
        install -m 644 ${WORKDIR}/oscam.service ${D}${systemd_system_unitdir}
        install -m 644 ${WORKDIR}/oscam.conf ${D}${sysconfdir}/neutrino/config/oscam.conf.sample
}

pkg_postinst_ontarget_${PN} () {
        ln -sf /usr/bin/oscam.internal /usr/bin/oscam
}

INSANE_SKIP_${PN} = "already-stripped"

