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
        install -d ${D}/var/tuxbox/plugins/webtv ${D}/var/xupnpd/plugins ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/webtv/* ${D}/var/tuxbox/plugins/webtv
        install -m 644 ${S}/plugins/LocalTV/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/netzkino/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/ard_mediathek/* ${D}/var/tuxbox/plugins
        install -m 644 ${WORKDIR}/webtv_ora.xml ${D}/var/tuxbox/plugins/webtv
        install -m 644 ${WORKDIR}/webtv_localtv.xml ${D}/var/tuxbox/plugins/webtv
        install -m 644 ${WORKDIR}/yt_live.cfg ${D}/var/tuxbox/plugins
        install -m 644 ${WORKDIR}/yt_live.png ${D}/var/tuxbox/plugins/yt_live_hint.png
        install -m 644 ${S}/xupnpd/* ${D}/var/xupnpd/plugins/
        install -m 644 ${S}/plugins/stb_restore/stb-restore.lua ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_shell/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_flash/stb-flash.lua ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_backup/stb-backup.lua ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_move/stb-move.lua ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_log/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_local-flash/stb-local-flash.lua ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_plugins/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_startup/stb-startup.lua ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_startup/stb-startup_hint.png ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/stb_startup/stb-startup.cfg ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/logoupdater/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/mtv/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/settingsupdater/* ${D}/var/tuxbox/plugins
        install -m 644 ${S}/plugins/heizoelpreise/* ${D}/var/tuxbox/plugins
}

FILES_${PN} += " \
        /var \
"

