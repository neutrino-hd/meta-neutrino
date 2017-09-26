
PV = "3.5.1"

RDEPENDS_${PN} += "fbshot"

PROVIDES = "virtual/neutrino"
RPROVIDES_${PN} = "virtual/neutrino"

inherit autotools pkgconfig systemd

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
	--with-plugindir=/var/tuxbox/plugins \
	--with-webtvdir_var=/var/tuxbox/plugins/webtv \
	--with-luaplugindir=/var/tuxbox/plugins/luaplugins \
"

N_CFLAGS = "-Wall -Wextra -Wshadow -g -O2 -fno-strict-aliasing -rdynamic -DENABLE_FREESATEPG -DNEW_LIBCURL -I${S}/lib/libcoolstream2 -I${STAGING_INCDIR}/freetype2"

N_CXXFLAGS = "${N_CFLAGS}"

N_LDFLAGS += "-Wl,--hash-style=gnu -Wl,-rpath-link, ${STAGING_LIBDIR} -lcurl -ldl"
N_CPPFLAGS = "-DDYNAMIC_LUAPOSIX -D__KERNEL_STRICT_NAMES -D__STDC_CONSTANT_MACROS"

CPPFLAGS = "${N_CPPFLAGS}"

do_configure_prepend() {
	ln -sf ${B}/src/gui/version.h ${S}/src/gui/
	sed -i "s|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|${YT_DEV_KEY}|" ${S}/src/neutrino.cpp
	sed -i "s|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|${TMDB_DEV_KEY}|" ${S}/src/neutrino.cpp
sed -i "s|XXXXXXXXXXXXXXXX|${SHOUTCAST_DEV_KEY}|" ${S}/src/neutrino.cpp
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
    install -m 0644 ${WORKDIR}/neutrino.service ${D}${systemd_unitdir}/system/
    install -m 0755 ${WORKDIR}/neutrino.sh ${D}${bindir}/   
	install -m 755 ${WORKDIR}/pre-wlan0.sh ${D}${sysconfdir}/network/
	install -m 755 ${WORKDIR}/post-wlan0.sh ${D}${sysconfdir}/network/
	install -m 644 ${WORKDIR}/timezone.xml ${D}${sysconfdir}/timezone.xml
	install -d ${D}${localstatedir}/cache
	install -d ${D}${localstatedir}/tuxbox
	install -d ${D}/lib/mdev/fs
	install -m 755 ${WORKDIR}/mount.mdev ${D}/lib/mdev/fs/mount
	echo "version=${DISTRO_VERSION}  `date +%Y-%m-%d` `date +%H:%M`"    > ${D}/.version  
	echo "creator=${CREATOR}"             >> ${D}/.version 
	echo "imagename=Neutrino-MP"             >> ${D}/.version 
	echo "homepage=${HOMEPAGE}"              >> ${D}/.version
	if [ ! -z ${RELEASE_TEXT_LOCATION_HOST} ];then
		HASH=$(cd ${S} && echo `git rev-parse --abbrev-ref HEAD` `git describe --always --tags --dirty`)
		echo "${IMAGE_LOCATION} ${RELEASE_STATE}${DISTRO_VERSION_NUMBER_MAJOR}${DISTRO_VERSION_NUMBER_MINOR}"0"`date +%Y%m%d%H%M` MD5 ${HASH} ${DISTRO_VERSION}" > ${RELEASE_TEXT_LOCATION_HOST}
	fi 
}

do_install_append() {
	install -d ${D}/share ${D}${sysconfdir}/neutrino/bin ${D}/var/tuxbox/plugins/luaplugins \
		   ${D}/var/tuxbox/plugins/webtv
	ln -s ${datadir}/tuxbox ${D}/share/
	ln -s ${datadir}/fonts  ${D}/share/
	if [ -d ${WORKDIR}/icons ];then
		install -m 644 ${WORKDIR}/icons/* ${D}/usr/share/tuxbox/neutrino/icons/
	fi
	if [ -d ${WORKDIR}/var ];then
		install -d ${D}/var/tuxbox/plugins/webtv
		install -m 644 ${WORKDIR}/var/tuxbox/config/* ${D}/etc/neutrino/config/
		install -m 644 ${WORKDIR}/var/tuxbox/plugins/webtv/* ${D}/var/tuxbox/plugins/webtv
	fi
	if [ ! -z ${RELEASE_TEXT_LOCATION} ];then
		echo "${RELEASE_TEXT_LOCATION}" > ${D}/etc/update.urls
	fi
}

FILES_${PN} += "\
	/.version \
	/etc \
	/share \
	/usr/share \
	/var \
	/lib \
"

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
