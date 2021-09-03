include neutrino-plugins-ni-env.inc

DESCRIPTION = "Terminal controlled menu manager known as Flexmenu."

# We need an extra template for configure.ac
SRC_URI += " \
	file://configure.ac.${PLUGIN_NAME} \
"

## Hack: shellexec's make install is doing strange things, try to fix it here
do_install () {
	install -d ${D}${bindir}
	install -d ${D}${N_PLUGIN_DIR}
	oe_runmake install DESTDIR=${D}
}
