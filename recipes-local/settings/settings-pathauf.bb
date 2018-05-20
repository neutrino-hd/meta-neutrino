DESCRIPTION = "Sortierte Senderliste SAT 19,2°E Sky-komplett HD mit HD+ von PathAuf"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "http://www.coolstream.to/index.php?page=Thread&threadID=9536"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"

RREPLACES_${PN} = "settings-matze-astra+hb settings-matze-astra settings-annie"
RCONFLICTS_${PN} = "settings-matze-astra+hb settings-annie settings-matze-astra"

SRC_URI = "git://github.com/neutrino-hd/settings-pathauf.git;protocol=https"

do_install () {
	install -d ${D}/etc/neutrino/config/zapit  
        install -m 644 ${S}/*.xml ${D}/etc/neutrino/config/zapit
}

FILES_${PN} = "\
    /etc/neutrino/config/zapit \
"
