
SRC_URI_append += "git://github.com/neutrino-images/ni-neutrino;protocol=https \
"

EXTRA_OECONF_append += " \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"
