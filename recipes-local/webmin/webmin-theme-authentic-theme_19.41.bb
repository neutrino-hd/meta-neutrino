SUMMARY = "Authentic Theme for Webmin"
HOMEPAGE = "https://github.com/authentic-theme/authentic-theme/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09aea93dd1e6793fa3d0cd94f846d459"

SRC_URI = "https://github.com/authentic-theme/authentic-theme/archive/${PV}.tar.gz \
"

SRC_URI[md5sum] = "1d3473127ff021b0533781fb4f93db84"
SRC_URI[sha256sum] = "a28fede7cd8be47281cc71d0a58111f0d14e24b7e24495b6c542e8255e2cd073"

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

INSANE_SKIP_${PN} += "src-uri-bad"
