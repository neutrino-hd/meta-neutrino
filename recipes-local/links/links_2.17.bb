DESCRIPTION = "Links is graphics and text mode WWW \
browser, similar to Lynx."
HOMEPAGE = "http://links.twibright.com/"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b0c80473f97008e42e29a9f80fcc55ff"
DEPENDS = "jpeg libpng flex openssl zlib"

SRC_URI = "http://links.twibright.com/download/links-${PV}.tar.bz2 \
           file://links-2.17.patch \
           file://links-2.17-ac-prog-cxx.patch \
	   file://links-2.17-hd51-input.patch \
	   file://links.cfg \
	   file://bookmarks \
	   file://bookmarks.html \
"

S = "${WORKDIR}/links-${PV}"

PACKAGECONFIG ??= "bzip2 lzma"
PACKAGECONFIG[bzip2] = "--with-bzip2,--without-bzip2,bzip2"
PACKAGECONFIG[lzma] = "--with-lzma,--without-lzma,xz"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-graphics \
                --with-ssl=${STAGING_LIBDIR}/.. \
		--with-libjpeg \
                --without-libtiff \
		--without-svgalib \
		--with-fb \
		--enable-graphics \
                --without-directfb \
		--without-pmshell \
		--without-atheos \
		--without-gpm \
                --without-x \
"

do_install_append() {
	install -d ${D}/usr/share/tuxbox/neutrino/plugins -d ${D}/etc/neutrino/config/links
	ln -sf /usr/bin/links ${D}/usr/share/tuxbox/neutrino/plugins/links.so
	install -m 0644 ${WORKDIR}/bookmarks ${D}/etc/neutrino/config
        install -m 0644 ${WORKDIR}/links.cfg ${D}/usr/share/tuxbox/neutrino/plugins
	touch ${D}/etc/neutrino/config/links/links.his
        install -m 0644 ${WORKDIR}/bookmarks.html ${D}/etc/neutrino/config/links
}

FILES_${PN} += "/usr"

SRC_URI[md5sum] = "e0cd5582b99a678b5ad3b112e9b42f7e"
SRC_URI[sha256sum] = "d8389763784a531acf7f18f93dd0324563bba2f5fa3df203f27d22cefe7a0236"

INSANE_SKIP_${PN}+= "dev-so"
