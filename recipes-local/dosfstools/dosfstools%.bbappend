do_install_append() {
	install -d ${D}/sbin
	ln -sf /usr/sbin/mkfs.vfat ${D}/sbin
        ln -sf /usr/sbin/fsck.vfat ${D}/sbin
}
