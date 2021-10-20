# for some common variables, e.g. plugin paths
include ../neutrino/neutrino-common-vars.inc

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

## PLUGIN_NAME is defined by recipe name
PLUGIN_NAME = "${PN}"

## These variables should be defined in plugin recipe and will be override if no description was defined inside plugin recipe
SECTION = "neutrino-plugin"
DESCRIPTION ?= "${PLUGIN_NAME}, plugin for neutrino"
MAINTAINER ?= "${FLAVOR}-developers"
HOMEPAGE ?= "https://github.com/tuxbox-neutrino"

## summary contains the plugin description
SUMMARY = "${DESCRIPTION}"

DEPENDS = "freetype ffmpeg zlib libxml2 virtual/libiconv openssl libpng curl giflib libjpeg-turbo"

PROVIDES = "virtual/neutrino-plugin-${PLUGIN_NAME}"
RPROVIDES_${PN} = "virtual/neutrino-plugin-${PLUGIN_NAME}"

PV = "git+${SRCPV}"

SRCREV = "${AUTOREV}"

# we need an independent file stack for the build, some templates for configure.ac and Makefile.am
# The plugin source and other required sources itself will be defined inside the file: neutrino-plugins-<provider>-env.inc
SRC_URI = " \
	file://acinclude.m4 \
	file://autogen.sh \
	file://COPYING \
	\
	file://configure.ac.template \
	file://Makefile.am.template \
"

S = "${WORKDIR}/git"

ALLOW_EMPTY_neutrino-plugins = "1"

inherit autotools pkgconfig gettext

EXTRA_OECONF += " \
	--enable-maintainer-mode \
	--with-target=native \
	--with-boxtype=armbox \
	--with-boxmodel=${MACHINE} \
	\
	--with-plugindir=${N_PLUGIN_DIR} \
	--with-plugindir_var=${N_PLUGIN_DIR_VAR} \
	--with-webtvdir=${N_WEBTV_DIR} \
	--with-webtvdir_var=${N_WEBTV_DIR_VAR} \
	--with-webradiodir=${N_WEBRADIO_DIR} \
	--with-webradiodir_var=${N_WEBRADIO_DIR_VAR} \
	--with-luaplugindir=${N_LUAPLUGIN_DIR} \
	--with-gamesdir=${N_GAMES_DIR}  \
	--with-iconsdir=${N_ICONS_DIR}  \
	--with-iconsdir_var=${N_ICONS_DIR}  \
	--with-configdir=${N_CONFIG_DIR} \
	--with-zapitdir=${N_ZAPITDIR} \
"

N_CFLAGS = "-Wall -W -Wshadow -g -O2 -funsigned-char -I${STAGING_INCDIR}/freetype2 -fcommon"
N_CXXFLAGS = "${N_CFLAGS}"
N_LDFLAGS += "-Wl,--hash-style=gnu -Wl,-rpath-link,${STAGING_DIR_HOST}${libdir},-lfreetype -lcrypto -lssl -lpng -lcurl -lz -lpthread"

do_patch () {
	# Here we misuse the patch routine to assemble and modify some required files in the source directory.
	# First we move a general fileset e.g. autogen.sh, acinclude.m4, COPYING etc. into source directory...
	cp -f acinclude.m4  autogen.sh ${S}

	# ... then we move the templates for configure.ac and Makefile.am into source directory.
	# Btw: if we found an extra configure.ac or template file, it will be used instead default template file, because
	# in some cases it could be easier to have a completed file.
	if test -f "${WORKDIR}/configure.ac.${PLUGIN_NAME}"; then
		cp -f "${WORKDIR}/configure.ac.${PLUGIN_NAME}" "${S}"/configure.ac
	else
		cp -f "${WORKDIR}/configure.ac.template" "${S}"/configure.ac
	fi

	if test -f "${WORKDIR}/Makefile.am.${PLUGIN_NAME}"; then
		cp -f "${WORKDIR}/Makefile.am.${PLUGIN_NAME}" "${S}"/Makefile.am
	else
		cp -f "${WORKDIR}/Makefile.am.template" "${S}"/Makefile.am
	fi

	# copy available Makefiles from "${WORKDIR}"/${PLUGIN_NAME} in to plugin source directory
	if test -f "${WORKDIR}/${PLUGIN_NAME}/Makefile.am"; then
		cp -f "${WORKDIR}/${PLUGIN_NAME}/Makefile.am" "${S}/${PLUGIN_NAME}"
	fi

	# Finally we modify configure.ac and Makefile.am, means: we replace the placeholders
	# with required data e.g. plugin name
	sed -i -e 's/@P@/${PLUGIN_NAME}/' ${S}/configure.ac
	sed -i -e 's/@PN@/${PN}/' ${S}/configure.ac
	sed -i -e 's|@HP@|${HOMEPAGE}|' ${S}/configure.ac

	sed -i -e 's/@P@/${PLUGIN_NAME}/' ${S}/Makefile.am
}

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CFLAGS="${N_CFLAGS}" CXXFLAGS="${N_CXXFLAGS}" LDFLAGS="${N_LDFLAGS}"
}

do_install_append() {
	rm -rf ${D}/usr/share/tuxbox/neutrino/plugins/*.la
	rm -rf ${D}/usr/share/tuxbox/neutrino/luaplugins
}

FILES_${PN} = "/usr \
	       /etc \
"

FILES_${PN}-dbg += "/usr/share/tuxbox/neutrino/plugins/.debug"
