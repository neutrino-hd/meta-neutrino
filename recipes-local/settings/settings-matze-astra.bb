DESCRIPTION = "Settings for Neutrino made by Matze - Astra only"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
HOMEPAGE = "http://matzesetting.brinkster.net/"

S = "${WORKDIR}"

RREPLACES_${PN} = "settings-pathauf settings-annie settings-matze-astra-hb"
RCONFLICTS_${PN} = "settings-pathauf settings-annie settings-matze-astra+hb"

SRC_URI = "http://matzesetting.brinkster.net/db/wizard/neutrino/astra/cs-astra.tar"

do_install () {
	install -d ${D}/etc/neutrino/config/zapit  
        install -m 644 ${WORKDIR}/*.xml ${D}/etc/neutrino/config/zapit
	rm ${D}/etc/neutrino/config/zapit/satellites.xml
}

FILES_${PN} = "\
    /etc/neutrino/config/zapit \
"

SRC_URI[md5sum] = "03756dd9fe0da51e7795e5041ab753c0"
SRC_URI[sha256sum] = "a4ea40ce3bb2f759116596e272119beb67921e0686e7dbb93b2ab2c1c5574173"
