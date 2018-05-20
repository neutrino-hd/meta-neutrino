DESCRIPTION = "Settings for Neutrino made by Matze - Astra + Hotbird"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
HOMEPAGE = "http://matzesetting.brinkster.net"

S = "${WORKDIR}"

RREPLACES_${PN} = "settings-pathauf settings-annie settings-matze-astra"
RCONFLICTS_${PN} = "settings-pathauf settings-annie settings-matze-astra"

SRC_URI = "http://matzesetting.brinkster.net/db/wizard/neutrino/astra_hotbird/cs-astra+hb.tar"

do_install () {
	install -d ${D}/etc/neutrino/config/zapit  
        install -m 644 ${WORKDIR}/*.xml ${D}/etc/neutrino/config/zapit
	rm ${D}/etc/neutrino/config/zapit/satellites.xml
}

FILES_${PN} = "\
    /etc/neutrino/config/zapit \
"

SRC_URI[md5sum] = "d89eaa1439d9ef222a0195f5c1efa246"
SRC_URI[sha256sum] = "01a9936d671876595cdb04a0db224c4adf84ecbd8e1d7c2e1395fff0a4cba0af"

