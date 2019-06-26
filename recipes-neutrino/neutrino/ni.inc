
SRC_URI_append += "git://bitbucket.org/neutrino-images/ni-neutrino-hd;protocol=https \
"

EXTRA_OECONF_append += " \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"
