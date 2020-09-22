DESCRIPTION = "Lua plugins for Neutrino"
LICENSE = "GPL-2.0"
MAINTAINER = "tuxbox-neutrino"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
HOMEPAGE = "https://github.com/tuxbox-neutrino/plugin-scripts-lua.git"

RDEPENDS_${PN} = "lua-feedparser lua-expat lua-json luaposix"

S = "${WORKDIR}/git"

PR = "r1"

SRC_URI = "git://github.com/tuxbox-neutrino/plugin-scripts-lua.git;protocol=https \
		   file://yt_live.tar.gz \
		   file://webtv.tar.gz \
"

SRCREV = "${AUTOREV}"

include ../../recipes-devtools/lua/lua.inc

do_install () {
	install -d ${D}/usr/share/tuxbox/neutrino/plugins/webtv ${D}/usr/share/xupnpd/plugins
	install -d ${D}/usr/share/tuxbox/neutrino/plugins/webradio ${D}/usr/share/lua/${LUA_VER}
	install -m 644 ${S}/plugins/webtv/* ${D}/usr/share/tuxbox/neutrino/plugins/webtv
	install -m 644 ${S}/plugins/LocalTV/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/netzkino/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/ard_mediathek/* ${D}/usr/share/tuxbox/neutrino/plugins
	cp -rf ${S}/plugins/rss/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${WORKDIR}/webtv_ora.xml ${D}/usr/share/tuxbox/neutrino/plugins/webtv
	install -m 644 ${WORKDIR}/webtv_localtv.xml ${D}/usr/share/tuxbox/neutrino/plugins/webtv
	install -m 644 ${WORKDIR}/yt_live.cfg ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${WORKDIR}/yt_live.png ${D}/usr/share/tuxbox/neutrino/plugins/yt_live_hint.png
	install -m 644 ${S}/xupnpd/* ${D}/usr/share/xupnpd/plugins/
	install -m 644 ${S}/plugins/stb_restore/stb-restore.lua ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_shell/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_flash/stb-flash.lua ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_backup/stb-backup.lua ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_move/stb-move.lua ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_log/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_local-flash/stb-local-flash.lua ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_plugins/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_startup/stb-startup.lua ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_startup/stb-startup_hint.png ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/stb_startup/stb-startup.cfg ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/logoupdater/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/mtv/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/settingsupdater/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/heizoelpreise/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/browser/* ${D}/usr/share/tuxbox/neutrino/plugins		
	install -m 644 ${S}/plugins/rcu_switcher/* ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 644 ${S}/plugins/2webTVxml/* ${D}/usr/share/tuxbox/neutrino/plugins
	cp -rf ${S}/plugins/neutrino-mediathek/* ${D}/usr/share/tuxbox/neutrino/plugins
	cp -r ${S}/share/lua/5.2/n_gui.lua ${D}/usr/share/lua/${LUA_VER}/n_gui.lua
	cp -r ${S}/share/lua/5.2/n_helpers.lua ${D}/usr/share/lua/${LUA_VER}/n_helpers.lua
}

FILES_${PN} += " \
	/usr \
"

