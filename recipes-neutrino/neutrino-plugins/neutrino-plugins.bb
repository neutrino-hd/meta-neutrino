DESCRIPTION = "tuxbox plugins, ported to neutrino-hd"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "freetype ffmpeg zlib libxml2 virtual/libiconv openssl libpng curl giflib libjpeg-turbo"

SRCREV = "${AUTOREV}"
PV = "9"

SRC_URI = "git://github.com/MaxWiesel/neutrino-plugins-max.git;protocol=https"

S = "${WORKDIR}/git"

ALLOW_EMPTY_neutrino-plugins = "1"

inherit autotools pkgconfig

EXTRA_OECONF += " \
	--enable-maintainer-mode \
	--with-target=native \
	--with-plugindir=/usr/share/tuxbox/neutrino/plugins \
	--with-boxtype=armbox \
	--with-boxmodel=${MACHINE} \
	--disable-logoview \
	--with-configdir=/etc/neutrino/config \
"

N_CFLAGS = "-Wall -W -Wshadow -g -O2 -funsigned-char -I${STAGING_INCDIR}/freetype2 -fcommon"
N_CXXFLAGS = "${N_CFLAGS}"
N_LDFLAGS += "-Wl,--hash-style=gnu -Wl,-rpath-link,${STAGING_DIR_HOST}${libdir},-lfreetype -lcrypto -lssl -lpng -lcurl -lz"

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CFLAGS="${N_CFLAGS}" CXXFLAGS="${N_CXXFLAGS}" LDFLAGS="${N_LDFLAGS}" SUBDIRS="${PLUGIN_INSTALL}"
}

do_install () {
	for i in ${PLUGIN_INSTALL}; do
		oe_runmake install SUBDIRS="$i" DESTDIR=${D}
	done
}			

do_install_append() {
	rm -f ${D}/usr/share/tuxbox/neutrino/plugins/*.la
}

FILES_${PN} = "/usr \
	       /etc \
"

FILES_${PN}-dbg += "/usr/share/tuxbox/neutrino/plugins/.debug"

