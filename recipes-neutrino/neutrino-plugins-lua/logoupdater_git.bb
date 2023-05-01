
include neutrino-lua-plugins-target-pattern.inc

MAINTAINER = "flk, content by fred_feuerstein"
SRC_NAME = "logoupdater"

SRC_URI_append = " \
	https://raw.githubusercontent.com/neutrino-images/ni-logo-stuff/master/logo-intro/lua-version/${SRC_NAME}_1.png \
"
# only for logo checksum, keep empty
SRC_URI[sha256sum] = ""

do_install_append () {
	install -d ${D}${N_LUAPLUGIN_DIR}
	install -m 644 ${WORKDIR}/${SRC_NAME}_1.png ${D}${N_LUAPLUGIN_DIR}/${SRC_NAME}.png
}
