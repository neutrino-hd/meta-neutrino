
include neutrino-lua-plugins-target-pattern.inc

MAINTAINER = "flk, content by fred_feuerstein"
SRC_NAME = "logoupdater"

SRC_URI_append = " \
	https://raw.githubusercontent.com/neutrino-images/ni-logo-stuff/master/logo-intro/lua-version/${SRC_NAME}_1.png \
"
# only for logo checksum
SRC_URI[sha256sum] = "db5e3aa45b449f81af6ae265d7c607c70fd96eecb66bc0d9a3f6364054c3a245"

do_install_append () {
	install -d ${D}${N_ICONS_DIR}
	install -m 644 ${WORKDIR}/${SRC_NAME}_1.png ${D}${N_ICONS_DIR}/${SRC_NAME}.png
}
