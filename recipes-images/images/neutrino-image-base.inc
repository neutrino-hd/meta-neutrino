# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

# add default password for root

ROOTFS_POSTPROCESS_COMMAND += "set_root_passwd;"
set_root_passwd() {
   ROOTPW_ENCRYPTED=""
   if [ -n "${ROOTPW}" ]; then
   ROOTPW_ENCRYPTED=`openssl passwd -crypt -quiet ${ROOTPW}`
   else
   ROOTPW_ENCRYPTED="wYNffsf6sozwE"
   fi
   sed "s%^root:[^:]*:%root:${ROOTPW_ENCRYPTED}:%" \
       < ${IMAGE_ROOTFS}/etc/shadow \
       > ${IMAGE_ROOTFS}/etc/shadow.new;
   mv ${IMAGE_ROOTFS}/etc/shadow.new ${IMAGE_ROOTFS}/etc/shadow ;
}

DEPENDS_append += " \
	crossplatform-iptvplayer \
	dropbear \
	dvb-apps \
	dvb-usb-drivers-meta \
	dvbsnoop \
	ethtool \
	fzf \
	ifuse \
	lcd4linux \
	libxslt \
	mc \
	ncdu \
	setserial \
	settings-annie-19.2e \
	settings-annie-19.2e-13.0e \
	settings-annie-19.2e-13.0e-23.5e \
	settings-annie-19.2e-13.0e-23.5e-28.2e \
	settings-annie-19.2e-13.0e-23.5e-28.2e-26.0e-0.8w-5.0w \
	settings-matze-astra \
	settings-matze-astra+hb \
	settings-pathauf \
	smbnetfs \
	sshfs-fuse \
	tmux \
	v4l-utils \
	weston \
	package-index \
"

KERNEL_MODULES_INSTALLED += "kernel-module-ftdi-sio \
			     kernel-module-usbserial \
"
	
IMAGE_INSTALL += " \
	autofs \
	bash \
	bzip2 \
	ca-certificates \
	cifs-utils \
	curl \
	dosfstools \
	dvb-femon \
	e2fsprogs \
	etckeeper \
	exfat-utils \
	fbset \
	fuse-exfat \
	glibc-binary-localedata-en-us \
	glibc-binary-localedata-cs-cz \
	glibc-binary-localedata-de-de \
	glibc-binary-localedata-es-es \
	glibc-binary-localedata-fr-fr \
	glibc-binary-localedata-pl-pl \
	glibc-binary-localedata-ru-ru \
	glibc-charmap-utf-8 \
	gptfdisk \
	gzip \
	hdparm \
	htop \
	ifupdown \
	image-config \
	imagemagick \
	libswscale \
	libusb1 \
	links \
	localedef \
	minidlna \
	nano \
	nfs-utils \
	nfs-utils-client \
	ntfs-3g \
	ntfsprogs \
	ntpdate \
	ofgwrite \
	oh-my-zsh \
	openssh \
	openssl \
	opkg \
	oscam \
	parted \
	procps \
	proftpd \
	pv \
	rpcbind \
	rsync \
	samba \
	sed \
	tzdata \
	tzdata-europe \
	udev-extraconf \
	udpxy \
	usbutils \
	util-linux \
	util-linux-blkid \
	util-linux-fstrim \
	util-linux-mount \
	util-linux-swaponoff \
	util-linux-umount \
	virtual/dvb-driver \
	virtual/libstb-hal \
	virtual/screengrabber \
	webmin \
	wpa-supplicant \
	xupnpd \
	zsh \
	${EXTRA_KERNEL_WIFI_DRIVERS} \
	${KERNEL_MODULES_INSTALLED} \
	${KERNEL_WIFI_DRIVERS} \
"

EXTRAOPKGCONFIG = "neutrino-feed-config"
