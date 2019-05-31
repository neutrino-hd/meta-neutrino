DESCRIPTION = "ng Logoupdater"
LICENSE = "proprietary"
MAINTAINER = "Fred Feuerstein"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license;md5=17a6b3d5436a55985b200c725761907a"
HOMEPAGE = "http://www.ng-return.com/wbb2/index.php?page=Thread&postID=334856#post334856"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"

PR = "r1"

RDEPENDS_${PN} = "curl"

SRC_URI = "git://bitbucket.org/neutrino-images/ni-logo-stuff;protocol=https \
	   file://license \
"

do_install () {
	install -d ${D}/usr/share/tuxbox/neutrino/plugins
	install -m 755 ${S}/logo-addon/* ${D}/usr/share/tuxbox/neutrino/plugins
        sed -i 's|command="WGET"|command="CURL"|' ${D}/usr/share/tuxbox/neutrino/plugins/logo-addon.sh
}

FILES_${PN} = "/usr"
