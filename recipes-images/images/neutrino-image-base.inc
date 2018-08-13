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
	dropbear \
	mc \
	libxslt \
	dvbsnoop \
	ncdu \
	tmux \
	fzf \
	setserial \
	sshfs-fuse \
	smbnetfs \
	ifuse \
        settings-annie-19.2e \
	settings-annie-19.2e-13.0e \ 
	settings-annie-19.2e-13.0e-23.5e \
	settings-annie-19.2e-13.0e-23.5e-28.2e \
	settings-annie-19.2e-13.0e-23.5e-28.2e-26.0e-0.8w-5.0w \
	settings-pathauf \
	settings-matze-astra \
	settings-matze-astra+hb \
	links \
	v4l-utils \
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
	dvb-usb-drivers-meta \
	dosfstools \
	e2fsprogs \
	etckeeper \
	exfat-utils \
	fbset \
	fuse-exfat \
	gptfdisk \
	glibc-charmap-utf-8 \
	glibc-binary-localedata-en-us \
        glibc-binary-localedata-de-de \
        glibc-binary-localedata-fr-fr \
        glibc-binary-localedata-ru-ru \
        glibc-binary-localedata-cs-cz \
        glibc-binary-localedata-pl-pl \
        glibc-binary-localedata-es-es \
	gzip \
	hdparm \
	htop \
	image-config \
	imagemagick \
	ifupdown \ 
	kernel-module-ftdi-sio \
	kernel-module-usbserial \ 
	libswscale \
	libusb1 \
	localedef \
	minidlna \
	nano \
	ni-logos \
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
	util-linux-mount \
	util-linux-umount \
	util-linux-swaponoff \
	util-linux-fstrim \
	virtual/libstb-hal \
	virtual/dvb-driver \
	virtual/screengrabber \
	proftpd \
	webmin \
	wpa-supplicant \
	xupnpd \
	zsh \
	${KERNEL_WIFI_DRIVERS} \
	${EXTRA_KERNEL_WIFI_DRIVERS} \
	${EXTERNAL_WIFI_DRIVERS} \
	${KERNEL_MODULES_INSTALLED} \
"

EXTRAOPKGCONFIG = "neutrino-feed-config"
