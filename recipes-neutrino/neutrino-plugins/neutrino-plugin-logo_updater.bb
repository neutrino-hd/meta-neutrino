DESCRIPTION = "ng Logoupdater"
LICENSE = "proprietary"
MAINTAINER = "Fred Feuerstein"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license;md5=17a6b3d5436a55985b200c725761907a"
HOMEPAGE = "http://www.ng-return.com/wbb2/index.php?page=Thread&postID=334856#post334856"

S = "${WORKDIR}/git/logo-addon"

SRCREV = "${AUTOREV}"

PR = "r1"

RDEPENDS_${PN} = "curl"

SRC_URI = "git://bitbucket.org/neutrino-images/ni-logo-stuff;protocol=https \
	   file://license \
"

do_install () {
	install -d ${D}/usr/share/tuxbox/plugins
	install -m 755 ${S}/logo-addon.sh ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/logo-addon.cfg ${D}/usr/share/tuxbox/plugins
	install -m 644 ${S}/logo-addon_hint.png ${D}/usr/share/tuxbox/plugins
}

FILES_${PN} = "/usr"
