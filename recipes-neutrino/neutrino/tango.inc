
SRC_URI_append += "git://github.com/TangoCash/neutrino-mp-cst-next.git;protocol=https \
					 file://0001-fix-event-device-for-armbox.patch \
					 file://0012-import-proper-working-format-device-function.patch \
"

EXTRA_OECONF_append += "--with-boxtype=hd51 \
						--with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
						--enable-reschange \
						--disable-tangos \
"