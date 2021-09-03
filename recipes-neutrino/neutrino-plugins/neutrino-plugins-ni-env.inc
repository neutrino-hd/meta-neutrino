include neutrino-plugins.inc

MAINTAINER = "ni"
HOMEPAGE = "https://neutrino-images.de"

SRCREV_${PLUGIN_NAME} = "${AUTOREV}"
SRCREV_data = "${AUTOREV}"
SRCREV_include = "${AUTOREV}"

# SRCREV_FORMAT = "${PLUGIN_NAME}_data_include"
SRCREV_FORMAT = "${PLUGIN_NAME}"

SRC_URI += " \
	git://github.com/neutrino-images/ni-neutrino-plugins.git;name=${PLUGIN_NAME};protocol=https;subpath=${PLUGIN_NAME};destsuffix=git/${PLUGIN_NAME} \
	git://github.com/neutrino-images/ni-neutrino-plugins.git;name=include;protocol=https;subpath=include;destsuffix=git/include \
"

# EXTRA_OECONF += " \
# 	--program-prefix=ni- \
# "
