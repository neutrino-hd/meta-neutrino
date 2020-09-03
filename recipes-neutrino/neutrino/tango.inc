HOMEPAGE = "https://github.com/TangoCash"
MAINTAINER = "TangoCash"

SRC_URI_append += "git://github.com/TangoCash/neutrino-mp-tangos.git;protocol=https \
"

PV = "${FLAVOUR}-git${GITPKGV}"

EXTRA_OECONF_append += "--enable-reschange \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
			--enable-lcd4linux \
"
