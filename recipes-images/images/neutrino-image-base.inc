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
   rm -rf ${IMAGE_ROOTFS}/tmp/*
}

KERNEL_MODULES_INSTALLED += ""


BIG_IMAGE_FILES = " \
	bash \
	bluez5 \
	bzip2 \
	cifs-utils \
	dvb-femon \
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
	grep \
	gzip \
	htop \
	image-config \
	imagemagick \
	links \
	localedef \
	ntfs-3g \
	ntfsprogs \
	oh-my-zsh \
	parted \
	procps \
	proftpd \
	samba \
	sed \
	usbutils \
	util-linux \
	util-linux-fstrim \
	webmin \
	wpa-supplicant \
	zsh \
	${EXTRA_KERNEL_MODULES} \
"

KERNEL_WIFI_DRIVERS = " \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-zd1211 \
	\
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-r8712u \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-zd1211rw \
	"

EXTRA_KERNEL_WIFI_DRIVERS = " \
	firmware-rtl8192cu \
	\
	kernel-module-r8188eu \
	kernel-module-rtl8192cu \
	"

EXTERNAL_WIFI_DRIVERS = " \
	rtl8192cu \
	rt8188eu \
	rtl8192eu \
	"

EXTRA_KERNEL_MODULES = " \
	kernel-module-ftdi-sio \
	kernel-module-usbserial \
	"

IMAGE_INSTALL += " \
	autofs \
	ca-certificates \
	curl \
	dosfstools \
	e2fsprogs \
	e2fsprogs-resize2fs \
	findutils \
	gptfdisk \
	hdparm \
	ifupdown \
	less \
	libswscale \
	libusb1 \
	minidlna \
	nano \
	nfs-utils \
	nfs-utils-client \
	ntpdate \
	ofgwrite \
	openssh \
	openssl \
	opkg \
	oscam \
	pv \
	rpcbind \
	rsync \
	samsunglcd4linux \
	tar \
	tzdata \
	tzdata-europe \
	udev-extraconf \
	udpxy \
	util-linux-blkid \
	util-linux-mount \
	util-linux-swaponoff \
	util-linux-umount \
	virtual/screengrabber \
	virtual/neutrino \
	neutrino-plugins \
	neutrino-lua-plugins \
	neutrino-plugin-mediathek \
	xupnpd \
	zip \
"

EXTRAOPKGCONFIG = "neutrino-feed-config"
