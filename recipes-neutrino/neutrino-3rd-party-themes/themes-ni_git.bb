
include themes.inc

DESCRIPTION = "${PRE_DESCRIPTION}"
HOMEPAGE = "https://github.com/neutrino-images/ni-neutrino"
MAINTAINER = "NI-Team"
SUMMARY = "${DESCRIPTION} by ${MAINTAINER}"

SRC_URI = "git://github.com/neutrino-images/ni-neutrino.git;protocol=https;subpath=${SUBPATH}"

PR = "r4"
SRCREV = "${AUTOREV}"
PV = "3.60"


do_install () {
	install -d ${D}/var/tuxbox/themes
	install -m 644 ${S}/data/themes/Material*.theme ${D}/var/tuxbox/themes
	install -m 644 ${S}/data/themes/Adult*.theme ${D}/var/tuxbox/themes
	install -m 644 ${S}/data/themes/Dark*.theme ${D}/var/tuxbox/themes

	THEME_LIST=`ls ${D}/var/tuxbox/themes`

	install -d ${D}/var/tuxbox/themes/${PN}-icons/icons

	ICONS=`find ${S}/data/icons -name *.png`
	for i in  ${ICONS} ; do
		install -m 644 ${i} ${D}/var/tuxbox/themes/${PN}-icons/icons
	done

	rm -f ${D}/var/tuxbox/themes/${PN}-icons/icons/hint_imagelogo.png
	rm -f ${D}/var/tuxbox/themes/${PN}-icons/icons/mainmenue.png

	for t in  ${THEME_LIST} ; do
		LINKNAME=${D}/var/tuxbox/themes/`echo ${t%.*}`
		ln -sf /var/tuxbox/themes/${PN}-icons ${LINKNAME}
	done
}
