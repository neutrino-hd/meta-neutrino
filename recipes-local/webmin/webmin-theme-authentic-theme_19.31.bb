SUMMARY = "Authentic Theme for Webmin"
HOMEPAGE = "https://github.com/authentic-theme/authentic-theme/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09aea93dd1e6793fa3d0cd94f846d459"

SRC_URI = "https://github.com/authentic-theme/authentic-theme/archive/${PV}.tar.gz \
"

SRC_URI[md5sum] = "03fdcb4f0ca3026da68db3f0885c6608"
SRC_URI[sha256sum] = "7f9fc3c6bbb4dc096b7b0d4b7e5d2bc4684a36b1ba3cdc112e9bcca1eca625b9"

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
