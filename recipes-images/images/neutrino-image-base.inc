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
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	e2fsprogs-badblocks \
	etckeeper \
	gptfdisk \
	gstreamer1.0 \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-bad \
	gstreamer1.0-plugins-ugly \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugin-dvbmediasink \
	gstreamer1.0-plugin-subsink \
	gzip \
	hdparm \
	image-config \
	ifupdown \
	libswscale \
	libusb1 \
	minidlna \
	nano \
	neutrino-plugin-logo \
	ni-logos \
	nfs-utils \
	nfs-utils-client \
	ntfs-3g \
	ntfsprogs \
	ntpdate \
	ofgwrite \
	openssh \
	openssl \
	opkg \
	oscam \
	procps \
	rpcbind \
	samba \
	tzdata \
	tzdata-europe \
	udev-extraconf \
	udpxy \
	usbutils \
	util-linux \
	util-linux-blkid \
	util-linux-mount \
	util-linux-umount \
	util-linux-fstrim \
	virtual/libstb-hal \
	virtual/dvb-driver \
	virtual/screengrabber \
	vsftpd \
	wpa-supplicant \
	xupnpd \
	${KERNEL_WIFI_DRIVERS} \
	${EXTRA_KERNEL_WIFI_DRIVERS} \
	${EXTERNAL_WIFI_DRIVERS} \
"

EXTRAOPKGCONFIG = "neutrino-feed-config"
