
include themes.inc

DESCRIPTION = "${PRE_DESCRIPTION}"
HOMEPAGE = "https://github.com/TangoCash/neutrino-tangos"
MAINTAINER = "Tango"
SUMMARY = "${DESCRIPTION} by ${MAINTAINER}"

SRC_URI = "git://github.com/TangoCash/neutrino-tangos.git;protocol=https;subpath=${SUBPATH}"

PR = "r4"
PV = "git-${SRCPV}"

do_install () {
	install -d ${D}/var/tuxbox/themes
	THEME_LIST="GlassHD Lechuk Sportster TangoCash adtheme bgfade colors dbox-all megasat4000 nesh"
	for t in  ${THEME_LIST} ; do
		install -m 644 ${S}/data/themes/${t}.theme ${D}/var/tuxbox/themes
	done

	install -d ${D}/var/tuxbox/themes/${PN}-icons/icons
	ICONS=`find ${S}/data/icons -name *.png`
	for i in  ${ICONS} ; do
		install -m 644 ${i} ${D}/var/tuxbox/themes/${PN}-icons/icons
	done

	for t in  ${THEME_LIST} ; do
		LINKNAME=${D}/var/tuxbox/themes/${t}
		ln -sf /var/tuxbox/themes/${PN}-icons ${LINKNAME}
	done
}
