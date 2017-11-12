
PROVIDES = "virtual/neutrino"
RPROVIDES_${PN} = "virtual/neutrino"

inherit autotools pkgconfig systemd gitpkgv

DEPENDS += " \
	curl \
	ffmpeg \
	flac \
	freetype \
	freetype-native \
	gettext \
	giflib \
	libbluray \
	libdvbsi++ \
	libid3tag \
	libjpeg-turbo \
	pugixml \
	libmad \
	libpng \
	libsigc++-2.0\
	lua \
	luaposix \
	openssl \
	openthreads \
	virtual/libstb-hal \
	virtual/libiconv \
	libvorbis \
	fontconfig \
	libpng \
"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "neutrino.service"

EXTRA_OECONF = " \
	--enable-maintainer-mode \
	--enable-silent-rules \
	--enable-mdev \
	--enable-upnp \
	--enable-pugixml \
	--enable-giflib \
	--enable-flac \
	--with-target=native \
	--prefix=/usr \
	--exec_prefix=/usr/bin \
	--includedir=${prefix}/include \
	--with-configdir=/etc/neutrino/config \
	--with-plugindir=/etc/neutrino/plugins \
	--with-webtvdir_var=/etc/neutrino/plugins/webtv \
	--with-luaplugindir=/etc/neutrino/plugins \
	--with-plugindir_var=/etc/neutrino/plugins \
	--with-gamesdir=/etc/neutrino/plugins \
	--with-webtvdir_var=/etc/neutrino/plugins/webtv \
	--with-plugindir_var=/etc/neutrino/plugins \
"

N_CFLAGS = "-Wall -Wextra -Wshadow -g -O2 -fno-strict-aliasing -rdynamic -DENABLE_FREESATEPG -DNEW_LIBCURL -I${S}/lib/libcoolstream2 -I${STAGING_INCDIR}/freetype2 -I${STAGING_INCDIR}/sigc++-2.0"

N_CXXFLAGS = "${N_CFLAGS}"

N_LDFLAGS += "-Wl,--hash-style=gnu -Wl,-rpath-link, ${STAGING_LIBDIR} -lcurl -ldl"
N_CPPFLAGS = "-DDYNAMIC_LUAPOSIX -D__KERNEL_STRICT_NAMES -D__STDC_CONSTANT_MACROS"

CPPFLAGS = "${N_CPPFLAGS}"

do_configure_prepend() {
	ln -sf ${B}/src/gui/version.h ${S}/src/gui/
	if [ ${YT_DEV_KEY} ];then
		sed -i "s|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|${YT_DEV_KEY}|" ${S}/src/neutrino.cpp
	fi
	if [ ${TMDB_DEV_KEY} ];then
		sed -i "s|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|${TMDB_DEV_KEY}|" ${S}/src/neutrino.cpp
	fi
	if [ ${SHOUTCAST_DEV_KEY} ];then
		sed -i "s|XXXXXXXXXXXXXXXX|${SHOUTCAST_DEV_KEY}|" ${S}/src/neutrino.cpp
	fi
}

do_compile () {
	# unset CFLAGS CXXFLAGS LDFLAGS
	oe_runmake CFLAGS="${N_CFLAGS}" CXXFLAGS="${N_CXXFLAGS}" LDFLAGS="${N_LDFLAGS}"
}


do_install_prepend () {
	install -d ${D}${sysconfdir}/network
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/neutrino/config
	install -d ${D}${sysconfdir}/neutrino/plugins	
    install -m 0644 ${WORKDIR}/neutrino.service ${D}${systemd_unitdir}/system/
    install -m 0755 ${WORKDIR}/neutrino.sh ${D}${bindir}/   
	install -m 755 ${WORKDIR}/pre-wlan0.sh ${D}${sysconfdir}/network/
	install -m 755 ${WORKDIR}/post-wlan0.sh ${D}${sysconfdir}/network/
	install -m 644 ${WORKDIR}/timezone.xml ${D}${sysconfdir}/timezone.xml
	install -d ${D}${localstatedir}/cache
	install -d ${D}/lib/mdev/fs
	install -m 755 ${WORKDIR}/mount.mdev ${D}/lib/mdev/fs/mount
	echo "creator=${CREATOR}"             >> ${D}/.version 
	echo "imagename=Neutrino-MP"             >> ${D}/.version 
	echo "homepage=${HOMEPAGE}"              >> ${D}/.version
        echo "version=${DISTRO_VERSION_NUMBER}"    > ${D}/.version
	if [ ! -z ${RELEASE_TEXT_LOCATION_HOST} ];then
		HASH=$(cd ${S} && echo `git rev-parse --abbrev-ref HEAD` `git describe --always --tags --dirty`)
		echo "${IMAGE_LOCATION} ${RELEASE_STATE}${DISTRO_VERSION_NUMBER_MAJOR}${DISTRO_VERSION_NUMBER_MINOR}"0"`date +%Y%m%d%H%M` MD5 ${HASH} ${DISTRO_VERSION}" > ${RELEASE_TEXT_LOCATION_HOST}
	fi 
}

do_install_append() {
	install -d ${D}/share ${D}${sysconfdir}/neutrino/bin ${D}${sysconfdir}/neutrino/plugins/luaplugins ${D}${sysconfdir}/neutrino/plugins/webtv
	ln -s ${datadir}/tuxbox ${D}/share/
	ln -s ${datadir}/fonts  ${D}/share/
	if [ ! -z ${RELEASE_TEXT_LOCATION} ];then
		echo "${RELEASE_TEXT_LOCATION}" > ${D}/etc/update.urls
	fi
	cp -rf ${WORKDIR}/etc ${D}
	install -m 755 ${WORKDIR}/deepstandby.on ${D}/etc/neutrino/config
}

FILES_${PN} += "\
	/.version \
	/etc \
	/share \
	/usr/share \
	/lib \
"

systemd_prerm() {
}

systemd_postinst() {
OPTS=""

if [ -n "$D" ]; then
    OPTS="--root=$D"
fi

if type systemctl >/dev/null 2>/dev/null; then
	if [ -z "$D" ]; then
		systemctl daemon-reload
	fi

	systemctl $OPTS ${SYSTEMD_AUTO_ENABLE} ${SYSTEMD_SERVICE}
fi
}


pkg_postinst_${PN} () {
	update-alternatives --install /bin/backup.sh backup.sh /usr/bin/backup.sh 100
	update-alternatives --install /bin/install.sh install.sh /usr/bin/install.sh 100
	update-alternatives --install /bin/restore.sh restore.sh /usr/bin/restore.sh 100
	# pic2m2v is only available on platforms that use "real" libstb-hal
	if which pic2m2v >/dev/null 2>&1; then
		# neutrino icon path
		I=/usr/share/tuxbox/neutrino/icons
		pic2m2v $I/mp3.jpg $I/radiomode.jpg $I/scan.jpg $I/shutdown.jpg $I/start.jpg
	fi
}

