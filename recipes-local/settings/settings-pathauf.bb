DESCRIPTION = "Sortierte Senderliste SAT 19,2°E Sky-komplett HD mit HD+ von PathAuf"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "http://www.coolstream.to/index.php?page=Thread&threadID=9536"

S = "${WORKDIR}/git"

RREPLACES_${PN} = "settings-annie-19.2e-13.0e-23.5e-28.2e settings-annie-19.2e-13.0e-23.5e settings-annie-19.2e-13.0e settings-annie-19.2e settings-matze-astra settings-matze-astra+hb settings-annie-19.2e-13.0e-23.5e-28.2e-26.0e-0.8w-5.0w"
RCONFLICTS_${PN} = "settings-annie-19.2e-13.0e-23.5e-28.2e settings-annie-19.2e-13.0e-23.5e settings-annie-19.2e-13.0e settings-annie-19.2e settings-matze-astra settings-matze-astra+hb settings-annie-19.2e-13.0e-23.5e-28.2e-26.0e-0.8w-5.0w"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/neutrino-hd/settings-pathauf.git;protocol=https"

do_install () {
	install -d ${D}/etc/neutrino/config/zapit  
        install -m 644 ${S}/*.xml ${D}/etc/neutrino/config/zapit
}

FILES_${PN} = "\
    /etc/neutrino/config/zapit \
"
