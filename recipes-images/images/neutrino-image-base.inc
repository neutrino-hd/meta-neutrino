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


IMAGE_INSTALL += " \
	autofs \
	bash \
	bzip2 \
	cifs-utils \
	coreutils \
	dosfstools \
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	etckeeper \
	exfat-utils \
	gawk \
	glibc-charmaps \
	glibc-binary-localedata-de-de \
	glibc-gconvs \
	glibc-localedatas \
	gstreamer1.0-plugins-good-meta \
	gstreamer1.0-plugins-bad-meta \
	gstreamer1.0-plugins-ugly-meta \
	gstreamer1.0-plugins-base-meta \
	gstreamer1.0 \
	gstreamer1.0-plugin-dvbmediasink \
	gstreamer1.0-libav \
	gzip \
	hdparm \
	image-config \
	inotify-tools \
	ifupdown \
	kernel-modules \
	libevent \
	libusb1 \
	localedef \
	mc \
	mc-fish \
	mc-helpers \
	mc-helpers-perl \
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
	parted \
	procps \
	python \
	python-modules \
	rpcbind \
	samba \
	sed \
	tzdata \
	tzdata-europe \
	udev-extraconf \
	udpxy \
	usbutils \
	util-linux \
	util-linux-agetty \
	util-linux-blkid \
	virtual/libstb-hal \
	virtual/dvb-driver \
	virtual/screengrabber \
	vsftpd \
	wpa-supplicant \
	xupnpd \
"
    
RDEPENDS_${PN}_append_libc_glibc += "kbd-locale-de"

EXTRAOPKGCONFIG = "neutrino-feed-config"




