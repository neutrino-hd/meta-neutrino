include neutrino-lua-plugins-source.inc

DESCRIPTION = "Lua plugins for Neutrino"
LICENSE = "GPL-2.0"
MAINTAINER = "tuxbox-neutrino"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
HOMEPAGE = "https://github.com/tuxbox-neutrino/plugin-scripts-lua.git"

RDEPENDS_${PN} = " \
			neutrino-lua-plugins-shared-files \
"

PR = "r1.0"

RDEPENDS_${PN} += " \
			heizoelpreise \
			mtv \
			rss \
			webtv \
			ard-mediathek \
			local-tv \
			netzkino \
			browser \
			logoupdater \
			settingsupdater \
			favorites-to-bin \
			mediathek \
			rcu-switcher \
			to-web-tv-xml \
			\
			stb-log \
			stb-plugins \
			stb-shell \
			stb-startup \
"

# # maybe plugin is broken and obsolete
# RDEPENDS_${PN} += " \
# 			plugin-myspass \
# "

do_install () {
	mkdir -p ${D}/usr/share/tuxbox/neutrino/plugins
}

FILES_${PN} += " \
	/usr \
"
