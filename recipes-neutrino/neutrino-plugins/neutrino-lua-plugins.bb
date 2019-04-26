DESCRIPTION = "Lua plugins for Neutrino"
LICENSE = "GPL-2.0"
MAINTAINER = "tuxbox-neutrino"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
HOMEPAGE = "https://github.com/tuxbox-neutrino/plugin-scripts-lua.git"

S = "${WORKDIR}/git"

PR = "r1"

SRC_URI = "git://github.com/tuxbox-neutrino/plugin-scripts-lua.git;protocol=https \
		   file://0001-ard-mediathek-disable-hds.patch \
		   file://yt_live.tar.gz \
		   file://webtv.tar.gz \
"

SRCREV = "${AUTOREV}"

do_install () {
	install -d ${D}/usr/share/tuxbox/plugins/webtv ${D}/usr/share/xupnpd/plugins ${D}/usr/share/tuxbox/plugins/webradio
	install -m 644 ${S}/plugins/webtv/* ${D}/usr/share/tuxbox/plugins/webtv
        install -m 644 ${S}/plugins/LocalTV/* ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/plugins/netzkino/* ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/plugins/ard_mediathek/* ${D}/usr/share/tuxbox/plugins
        install -m 644 ${WORKDIR}/webtv_ora.xml ${D}/usr/share/tuxbox/plugins/webtv
        install -m 644 ${WORKDIR}/webtv_localtv.xml ${D}/usr/share/tuxbox/plugins/webtv
        install -m 644 ${WORKDIR}/yt_live.cfg ${D}/usr/share/tuxbox/plugins
        install -m 644 ${WORKDIR}/yt_live.png ${D}/usr/share/tuxbox/plugins/yt_live_hint.png
	install -m 644 ${S}/xupnpd/* ${D}/usr/share/xupnpd/plugins/
        install -m 644 ${S}/plugins/stb_restore/stb-restore.lua ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_shell/* ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_flash/stb-flash.lua ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_backup/stb-backup.lua ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_move/stb-move.lua ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_log/* ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_local-flash/stb-local-flash.lua ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_plugins/* ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/logoupdater/* ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/mtv/* ${D}/usr/share/tuxbox/plugins
        install -m 644 ${S}/plugins/settingsupdater/* ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/plugins/heizoelpreise/* ${D}/usr/share/tuxbox/plugins
}

FILES_${PN} += " \
	/usr \
"
