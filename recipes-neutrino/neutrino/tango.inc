
SRC_URI_append += "git://github.com/TangoCash/neutrino-mp-cst-next.git;protocol=https \
					 file://0008-rcsim.c-fix-eventdev-for-yocto.patch \
					 file://0009-src-nhttpd-tuxboxapi-controlapi.cpp-fix-eventdev-for.patch \
					 file://0012-import-proper-working-format-device-function.patch \
"

EXTRA_OECONF_append += "--with-boxtype=armbox \
						--with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
						--enable-reschange \
						--disable-tangos \
"