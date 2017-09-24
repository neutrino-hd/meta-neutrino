DESCRIPTION = "Neutrino Lua RSS Reader"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
HOMEPAGE = ""
MAINTAINER = "Jacek Jendrzej"
DEPENDS = "lua expat"
RDEPENDS_${PN} = "lua-expat lua-feedparser lua-curl"

PV = "0.09c"
PR = "1"


SRC_URI = "file://rss.cfg \
	   file://rss.lua \
	   file://rssreader.conf \
	   file://rss_icon.png \
"

S = "${WORKDIR}/"

do_install () {
	install -d ${D}/home/builder/.config/neutrino/plugins ${D}/home/builder/.config/neutrino/config
	install -m 644 ${S}/rss.lua ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/rss.cfg ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/rss_icon.png ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/rssreader.conf ${D}/home/builder/.config/neutrino/config
}

FILES_${PN} += "/home \
"


