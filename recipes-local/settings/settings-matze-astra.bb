
DESCRIPTION = "Settings for Neutrino made by Matze - Astra only"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"
HOMEPAGE = "http://matzesetting.brinkster.net/"

S = "${WORKDIR}"

RREPLACES_${PN} = "settings-annie-19.2e-13.0e-23.5e-28.2e settings-annie-19.2e-13.0e-23.5e settings-annie-19.2e-13.0e settings-annie-19.2e settings-pathauf settings-matze-astra+hb settings-annie-19.2e-13.0e-23.5e-28.2e-26.0e-0.8w-5.0w"
RCONFLICTS_${PN} = "settings-annie-19.2e-13.0e-23.5e-28.2e settings-annie-19.2e-13.0e-23.5e settings-annie-19.2e-13.0e settings-annie-19.2e settings-pathauf settings-matze-astra+hb settings-annie-19.2e-13.0e-23.5e-28.2e-26.0e-0.8w-5.0w"

SRC_URI = "http://matzesetting.brinkster.net/db/wizard/neutrino-hd/astra/cs-astra.tar"

do_install () {
	install -d ${D}/etc/neutrino/config/zapit  
        install -m 644 ${WORKDIR}/*.xml ${D}/etc/neutrino/config/zapit
	rm ${D}/etc/neutrino/config/zapit/satellites.xml
}

FILES_${PN} = "\
    /etc/neutrino/config/zapit \
"

BB_STRICT_CHECKSUM = "0"
