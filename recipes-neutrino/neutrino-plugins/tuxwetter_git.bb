include neutrino-plugins-ni-env.inc

DESCRIPTION = "Plugin for weather data and forecast known as Tuxwetter."

# Workaround: Internal install hook of shellexec seems broken,
# "bindir" will never installed
do_install_prepend () {
	install -d ${D}${bindir}
}
