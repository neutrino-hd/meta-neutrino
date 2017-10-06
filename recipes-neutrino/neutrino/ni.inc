
SRC_URI_append += "git://bitbucket.org/neutrino-images/ni-neutrino-hd;branch=ni/mp/tuxbox;protocol=git;protocol=https"

EXTRA_OECONF_append += "--with-boxtype=axtech \
						--with-boxmodel=ax \
						--with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"