
include neutrino-lua-plugins-target-pattern.inc

SUMMARY = "Image-management for flash, backup, restore or move images"
SRC_NAME = "stb_plugins"

RDEPENDS_${PN} += " \
			stb-backup \
			stb-flash \
			stb-flash-local \
			stb-move \
			stb-restore \
"
