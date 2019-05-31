DESCRIPTION = "crossplatform-iptvplayer"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "python"
RDEPENDS_${PN} += "python python-json python-twisted python-io python-logging python-netclient rtmpdump"
SRC_URI = "git://github.com/TangoCash/crossplatform_iptvplayer.git;protocol=https \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit pythonnative

do_configure() {
        install -d ${B}/usr/share/E2emulator ${B}/usr/share/E2emulator/Plugins/Extensions/IPTVPlayer ${B}/usr/share/tuxbox/neutrino/plugins ${B}/usr/bin
	cp -rf ${S}/E2emulator ${B}/usr/share
        cp -rf ${S}/IPTVplayer/* ${B}/usr/share/E2emulator/Plugins/Extensions/IPTVPlayer
        cp -rf ${S}/IPTVdaemon/* ${B}/usr/share/E2emulator/Plugins/Extensions/IPTVPlayer
        cp -rf ${S}/addon4cmdline/* ${B}/usr/share/E2emulator/Plugins/Extensions/IPTVPlayer
        chmod 755 ${B}/usr/share/E2emulator/Plugins/Extensions/IPTVPlayer/cmdlineIPTV.sh
        cp -rf ${S}/addon4neutrino/neutrinoIPTV/* ${B}/usr/share/tuxbox/neutrino/plugins/
        ln -sf /usr/share/E2emulator/Plugins/Extensions/IPTVPlayer/cmdlineIPTV.sh ${B}/usr/bin
}

do_compile() {
        ${STAGING_BINDIR_NATIVE}/python-native/python  -Wi -t -O ${STAGING_LIBDIR}/python2.7/compileall.py ${B}/usr/share/E2emulator
}

do_install () {
	cp -rf ${B}/* ${D}
}

FILES_${PN} += "/usr"

INSANE_SKIP_${PN} = "file-rdeps"
