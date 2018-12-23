
SRC_URI_append += "git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https \
		   file://0001-neutrino-mp-deactivate-unneeded-update-menu-entries.patch;apply=no \
		   file://0001-hdd_menu.cpp-allow-fuse-filesystems-also-avoid-hardc.patch;apply=yes \
		   file://0001-add-lcd4linux-support.patch \
"

DEFAULT_THEME ?= "Neutrino-3.0"

EXTRA_OECONF_append += "--with-default-theme=${DEFAULT_THEME} \
                        --with-stb-hal-includes=${STAGING_INCDIR}/libstb-hal \
			--enable-lcd4linux \
"

do_install_append += "mv -f ${D}${sysconfdir}/neutrino/config/bad_package_pattern.list.sample ${D}${sysconfdir}/neutrino/config/bad_package_pattern.list"
