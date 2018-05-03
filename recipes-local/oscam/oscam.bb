SUMMARY = "OSCam: Open Source Conditional Access Module"
HOMEPAGE = "http://www.streamboard.tv/oscam/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libusb1 openssl pcsc-lite"

DEPENDS_APPEND_libc-uclibc += "virtual/libstb-hal"

SRC_URI = "svn://www.streamboard.tv/svn/oscam-addons;protocol=http;module=modern;scmdata=keep \
	   file://oscam.service \
"

SRCREV = "1528"
PV = "svn${SRCREV}"

S = "${WORKDIR}/modern"
B = "${S}"	

INHIBIT_PACKAGE_STRIP = "1"

inherit cmake systemd

SYSTEMD_SERVICE_${PN} = "oscam.service"

do_configure_append_coolstream-hd2 () {
	if [ ${BOXTYPE} = "kronos" ];then
		sed -i "s|^#define MAX_COOL_DMX.*|#define MAX_COOL_DMX 3|" ${S}/module-dvbapi-coolapi.c
	fi
}


EXTRA_OECMAKE = " \
		 -DCS_SVN_VERSION="${SRCPV}" \
		 -DDEFAULT_CS_CONFDIR="/etc/neutrino/config" \
		 -DWEBIF=1 \
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
		 -DHAVE_PCSC=1 \
"

EXTRA_OECMAKE_append_coolstream-hd1 += "-DOSCAM_SYSTEM_NAME=Coolstream \
"

EXTRA_OECMAKE_append_coolstream-hd2 += "-DOSCAM_SYSTEM_NAME=CST2 \
"

EXTRA_OECMAKE_append_hd51 += "-DOSCAM_SYSTEM_NAME=tuxbox \
"

do_install () {
	install -d ${D}/usr/bin ${D}${systemd_system_unitdir}
	install -m 755 ${WORKDIR}/build/oscam ${D}/usr/bin/oscam.internal
        install -m 644 ${WORKDIR}/oscam.service ${D}${systemd_system_unitdir}
}

pkg_postinst_ontarget_${PN} () {
	ln -sf /usr/bin/oscam.internal /usr/bin/oscam
}

INSANE_SKIP_${PN} = "already-stripped"
