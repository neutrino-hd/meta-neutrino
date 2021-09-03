include neutrino-plugins.inc

MAINTAINER = "tuxbox"
HOMEPAGE = "https://tuxbox-neutrino.org"

SRC_URI += " \
	git://github.com/tuxbox-neutrino/plugin-${PLUGIN_NAME}.git;protocol=https;destsuffix=git/${PLUGIN_NAME} \
"
