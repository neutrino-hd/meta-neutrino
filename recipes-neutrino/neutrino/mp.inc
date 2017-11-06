
SRC_URI_append += "git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https \
"

EXTRA_OECONF_append += "--with-boxtype=armbox \
                        --with-boxmodel=hd51 \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"
