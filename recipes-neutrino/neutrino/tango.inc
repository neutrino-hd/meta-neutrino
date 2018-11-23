
SRC_URI_append += "git://github.com/TangoCash/neutrino-mp-cst-next.git;protocol=https \
"


EXTRA_OECONF_append += "--enable-reschange \
			--disable-tangos \				
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"
