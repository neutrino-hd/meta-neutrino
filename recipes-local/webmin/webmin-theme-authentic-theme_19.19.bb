SUMMARY = "Authentic Theme for Webmin"
HOMEPAGE = "https://github.com/authentic-theme/authentic-theme/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09aea93dd1e6793fa3d0cd94f846d459"

SRC_URI = "https://github.com/authentic-theme/authentic-theme/archive/${PV}.tar.gz \
"

SRC_URI[md5sum] = "1c3725c010e3d1777a135640e032bf49"
SRC_URI[sha256sum] = "94910238dd8919169f3354d30d186d8adfbcd3b598182266d07b5d51705d796d"

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

