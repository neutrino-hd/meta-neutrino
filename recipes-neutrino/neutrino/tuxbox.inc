HOMEPAGE = "http://www.tuxbox.org"
MAINTAINER = "Tuxbox-Developers"

SRC_URI_append += "git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https \
		   file://0001-hdd_menu.cpp-assume-device-mounted.patch \
"

PV = "3.23.${SRCPV}"

DEFAULT_THEME ?= "Neutrino-3.0"

EXTRA_OECONF_append += "--with-default-theme=${DEFAULT_THEME} \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"

do_install_append += "mv -f ${D}${sysconfdir}/neutrino/config/bad_package_pattern.list.sample ${D}${sysconfdir}/neutrino/config/bad_package_pattern.list"



