
SRC_URI_append += "git://github.com/TangoCash/neutrino-mp-cst-next.git;protocol=https \
		   file://0001-do-jpgs-for-osd-video-grabs-to-improve-yweb-performa.patch \
                   file://workaround_hdd_format.patch \
"


EXTRA_OECONF_append += "--with-boxtype=hd51 \
						--with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
						--enable-reschange \
						--disable-tangos \				
"
