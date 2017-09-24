DESCRIPTION = "MyTVPro Lua Plugin V1.6"
LICENSE = "GPL-3.0"
MAINTAINER = "celeburdi"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
HOMEPAGE = "http://www.dbox2world.net/board293-cst-coolstream/board313-cst-coolstream-downloads/board319-coolstream-plugins/12583-mytvpro-lua-plugin/"

S = "${WORKDIR}"

PR = "r2"

SRC_URI = "file://mytvpro.lua \
	   file://mytvpro.cfg \
	   file://mytvpro_hint.png \
"

do_install () {
	install -d ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/mytvpro.lua ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/mytvpro.cfg ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/mytvpro_hint.png ${D}/home/builder/.config/neutrino/plugins
}

do_install_append () {
echo "integration=2" >> ${D}/home/builder/.config/neutrino/plugins/mytvpro.cfg
}

FILES_${PN} += "/home \
"
