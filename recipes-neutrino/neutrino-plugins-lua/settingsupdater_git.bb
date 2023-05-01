
include neutrino-lua-plugins-target-pattern.inc

MAINTAINER = "flk, content by horsti58"
SRC_NAME = "settingsupdater"


SRC_URI_append = " \
	https://raw.githubusercontent.com/horsti58/lua-data/master/lua/settingupdater_1.png \
"
# only for logo checksum, keep empty
SRC_URI[sha256sum] = ""

do_install_append () {
	install -d ${D}${N_LUAPLUGIN_DIR}
	install -m 644 ${WORKDIR}/settingupdater_1.png ${D}${N_LUAPLUGIN_DIR}/${SRC_NAME}.png
}
