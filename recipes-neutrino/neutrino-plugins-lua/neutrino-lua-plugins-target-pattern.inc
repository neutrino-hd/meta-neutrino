include neutrino-lua-plugins.inc

# PN = mostly not the same like target name, defined in sub recipes
SRC_NAME = "${PN}"

RDEPENDS_${PN} += "neutrino-lua-plugins-shared-files"

SRC_SUBPATH = "plugins/${SRC_NAME}"

do_install () {
	cp -rf ${S}/${SRC_SUBPATH}/* ${D}${N_PLUGIN_DIR}
}
