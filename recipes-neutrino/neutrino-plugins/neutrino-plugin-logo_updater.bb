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
	install -d ${D}/var/tuxbox/plugins
	install -m 755 ${S}/logo-addon/* ${D}/var/tuxbox/plugins
        sed -i 's|command="WGET"|command="CURL"|' ${D}/var/tuxbox/plugins/logo-addon.sh
}

FILES_${PN} = "/var"
