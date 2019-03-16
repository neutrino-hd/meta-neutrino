SUMMARY = "Authentic Theme for Webmin"
HOMEPAGE = "https://github.com/authentic-theme/authentic-theme/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09aea93dd1e6793fa3d0cd94f846d459"

SRC_URI = "git://github.com/authentic-theme/authentic-theme.git;protocol=https \
" 
SRCREV = "1a342382c9af6ea490f8e51a374ea5151db44b7a"

S = "${WORKDIR}/git"

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

do_install() {
	install -d  ${D}/usr/libexec/webmin/authentic-theme ${D}${sysconfdir}/webmin/authentic-theme
	cp -rf ${S}/* ${D}/usr/libexec/webmin/authentic-theme/
}

FILES_${PN} = " \
	/usr \
	/etc \	
"

