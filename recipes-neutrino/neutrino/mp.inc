
SRC_URI_append += "git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https \
"


DEFAULT_THEME ?= "Neutrino-3.0"

EXTRA_OECONF_append += "--with-boxtype=armbox \
                        --with-boxmodel=hd51 \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
			--with-default-theme=${DEFAULT_THEME} \
"

do_install_append += "mv -f ${D}${sysconfdir}/neutrino/config/bad_package_pattern.list.sample ${D}${sysconfdir}/neutrino/config/bad_package_pattern.list"
