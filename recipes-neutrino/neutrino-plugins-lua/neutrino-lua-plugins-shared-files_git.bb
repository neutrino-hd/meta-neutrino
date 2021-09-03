
include neutrino-lua-plugins.inc
## PN (package name = filename)
#PN = "neutrino-lua-plugins-shared-files"
SUMMARY = "Shared files and scripts for neutrino-lua-plugins"

PV_LUA = "5.2"

SRC_SUBPATH = "share/lua/${PV_LUA}"

do_install () {
	mkdir -p ${D}/usr/share/lua/${LUA_VER}
	cp -r ${S}/${SRC_SUBPATH}/n_*.lua ${D}/usr/share/lua/${LUA_VER}/
##	json.lua not required, already provided by json package itself
# 	cp -r ${S}/${PV_LUA}/json.lua ${D}/usr/share/lua/${LUA_VER}/json.lua
	rm -r ${D}/usr/share/tuxbox
}
