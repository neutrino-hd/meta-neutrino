SUMMARY = "Authentic Theme for Webmin"
HOMEPAGE = "https://github.com/authentic-theme/authentic-theme/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09aea93dd1e6793fa3d0cd94f846d459"

SRC_URI = "https://github.com/authentic-theme/authentic-theme/archive/${PV}.tar.gz \
"

SRC_URI[md5sum] = "1a28b9f704fcba6ea53d99fc8d85955c"
SRC_URI[sha256sum] = "929bc8d366812bd81b7fd7228b5984f89785d48623e8e034a6c89c27bf51b174"

RDEPENDS_${PN} = " \
	perl \
	perl-module-lib \
	perl-module-utf8 \
	perl-module-unicore \
	perl-module-overload \
	perl-module-bytes \
	perl-module-encode \
	perl-module-encode-unicode \
	perl-module-file-find \
	webmin \
"

S = "${WORKDIR}/authentic-theme-${PV}"

do_install() {
	install -d  ${D}/usr/libexec/webmin/authentic-theme ${D}${sysconfdir}/webmin/authentic-theme
	cp -rf ${S}/* ${D}/usr/libexec/webmin/authentic-theme/
}

FILES_${PN} = " \
	/usr \
	/etc \	
"
