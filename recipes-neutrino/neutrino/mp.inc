
SRC_URI_append += "git://github.com/neutrino-hd/gui-neutrino.git;branch=pu/mp;protocol=https"

EXTRA_OECONF_append += "--with-boxtype=axtech \
                        --with-boxmodel=ax \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"
