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
	webmin \
"
	
IMAGE_INSTALL += " \
	autofs \
	bzip2 \
	ca-certificates \
	cifs-utils \
	curl \
	dvb-usb-drivers-meta \
	dosfstools \
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	e2fsprogs-badblocks \
	etckeeper \
	glibc-charmap-utf-8 \
	glibc-localedata-en-us \
	gptfdisk \
	gzip \
	hdparm \
	image-config \
	ifupdown \
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
	procps \
	pv \
	rpcbind \
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
	wpa-supplicant \
	xupnpd \
	zsh \
	${KERNEL_WIFI_DRIVERS} \
	${EXTRA_KERNEL_WIFI_DRIVERS} \
	${EXTERNAL_WIFI_DRIVERS} \
"

EXTRAOPKGCONFIG = "neutrino-feed-config"
