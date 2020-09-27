DESCRIPTION = "graphlcd"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

DEPENDS = "freetype fontconfig imagemagick"

SRC_URI = "git://github.com/MaxWiesel/graphlcd-base.git;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"

EXTRA_OEMAKE += '"INCLUDES=-I${STAGING_INCDIR} -I${STAGING_INCDIR}/ImageMagick-7 -I${STAGING_INCDIR}/freetype2 -I${S}" "UDEVRULESDIR=${D}/etc/udev/rules.d"'


inherit autotools-brokensep pkgconfig

do_configure_prepend() {
	#hack: we dont have io.h for arm 32bit
	touch ${STAGING_INCDIR}/sys/io.h
}

do_install_prepend() {
	install -d ${D}/etc/udev/rules.d
}


do_install_append() {
	install -d ${D}/usr
	mv ${D}/bin ${D}/usr/bin
	mv ${D}/lib ${D}/usr/lib
	mv ${D}/include ${D}/usr/include
}

FILES_${PN} = "/usr/bin /usr/lib /etc"
FILES_${PN}-dev = "/usr/include"

INSANE_SKIP_${PN} = "dev-so"

