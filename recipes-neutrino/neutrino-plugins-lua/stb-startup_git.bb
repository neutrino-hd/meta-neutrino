include neutrino-lua-plugins-target-pattern.inc

SRC_NAME = "stb_startup"

do_install_append () {
	rm -f ${D}${N_PLUGIN_DIR}/stb-startup_old.lua
}
