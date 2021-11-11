include neutrino-plugins.inc

MAINTAINER = "tuxbox"
HOMEPAGE = "https://tuxbox-neutrino.org"

SRCREV_${PLUGIN_NAME} = "${AUTOREV}"
SRCREV_FORMAT = "${MAINTAINER}-${PLUGIN_NAME}"

SUB_PATH ?= ""

SRC_URI += " \
	git://github.com/tuxbox-neutrino/plugin-${PLUGIN_NAME}.git;name=${PLUGIN_NAME};subpath=${SUB_PATH};protocol=https;destsuffix=src/${PLUGIN_NAME} \
"
