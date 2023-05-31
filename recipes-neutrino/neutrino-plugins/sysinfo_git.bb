include neutrino-plugins-tuxbox-env.inc

DESCRIPTION = "Shows graphically processed system informations"

SRC_URI += " \
	file://0001-sysinfo-fix-install-of-sysinfo-in-Makefile.am.patch \
"

do_patch () {
	git -C ${S}/${PLUGIN_NAME} apply ${S}/0001-sysinfo-fix-install-of-sysinfo-in-Makefile.am.patch
}
