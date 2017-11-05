
SRC_URI_append += "git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https \
		   file://0007-imageinfo.cpp-change-version-output.patch \
"

EXTRA_OECONF_append += "--with-boxtype=armbox \
                        --with-boxmodel=hd51 \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
"
