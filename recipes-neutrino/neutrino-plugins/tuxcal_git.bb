include neutrino-plugins-ni-env.inc

DESCRIPTION = "Graphical calendar known as Tuxcal."

# We need an extra template for configure.ac
SRC_URI += " \
	file://configure.ac.${PLUGIN_NAME} \
"
