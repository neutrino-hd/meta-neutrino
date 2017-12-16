FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

SRC_URI += "file://profile \
	    file://inputrc \
	    file://nsswitch.conf \
	    file://fstab \
	    file://oscam.service \
	    file://gbox.service \
	    file://ciplushelper.service \
	    file://lcd.sh \
	    file://lcd.service \
	    file://firstboot.service \
	    file://firstboot.sh \
	    file://-.mount \
	    file://boot.mount \
	    file://mnt-partition_1.mount \
	    file://mnt-partition_2.mount \
            file://mnt-partition_3.mount \
            file://mnt-partition_4.mount \
            file://mnt-partition_1.automount \
            file://mnt-partition_2.automount \
            file://mnt-partition_3.automount \
            file://mnt-partition_4.automount \
	    file://dev-mmcblk0p10.swap \
	    file://fstrim.service \
	    file://fstrim.timer \
"

inherit systemd

SYSTEMD_SERVICE_${PN} = "oscam.service"
BASEFILESISSUEINSTALL = "do_custom_baseissueinstall"

do_custom_baseissueinstall() {
	do_install_basefilesissue
	install -m 644 ${WORKDIR}/issue*  ${D}${sysconfdir}
	printf " __  __         __         ___       __"				>> ${D}${sysconfdir}/issue
	printf " __  __         __         ___       __"				>> ${D}${sysconfdir}/issue.net
	printf "\n%s" ' \\ \\/ /__  ____/ /____    / _ \\___  / /____ __'		>> ${D}${sysconfdir}/issue
	printf "\n%s" ' \\ \\/ /__  ____/ /____    / _ \\___  / /____ __' 		>> ${D}${sysconfdir}/issue.net
	printf "\n%s" '  \\  / _ \\/ __/ __/ _ \\  / ___/ _ \\/  ´_/ // /' 		>> ${D}${sysconfdir}/issue
	printf "\n%s" '  \\  / _ \\/ __/ __/ _ \\  / ___/ _ \\/  ´_/ // /' 		>> ${D}${sysconfdir}/issue.net
	printf "\n%s" '  /_/\\___/\\__/\\__/\\___/ /_/   \\___/_/\\_\\\\_, /' 		>> ${D}${sysconfdir}/issue
	printf "\n%s" '  /_/\\___/\\__/\\__/\\___/ /_/   \\___/_/\\_\\\\_, /' 		>> ${D}${sysconfdir}/issue.net
	printf "\n%s" '                                       /___/'			>> ${D}${sysconfdir}/issue
	printf "\n%s" '                                       /___/'			>> ${D}${sysconfdir}/issue.net
	printf "\n\nNeutrino-HD image (based on Yocto ${DISTRO} ${DISTRO_VERSION})" 	>> ${D}${sysconfdir}/issue
	printf "\n\nNeutrino-HD image (based on Yocto ${DISTRO} ${DISTRO_VERSION}) " 	>> ${D}${sysconfdir}/issue.net
	echo "\n%s %m %r" 								>> ${D}${sysconfdir}/issue
	echo "\n%s %m %r" 								>> ${D}${sysconfdir}/issue.net
	echo "%d, %t" 									>> ${D}${sysconfdir}/issue
	echo "%d, %t" 									>> ${D}${sysconfdir}/issue.net
	printf "\\\n \\\l\n"								>> ${D}${sysconfdir}/issue
	echo >> ${D}${sysconfdir}/issue
	echo >> ${D}${sysconfdir}/issue.net
}

do_install_append () {
	install -d ${D}${localstatedir}/update ${D}${systemd_unitdir}/system/multi-user.target.wants ${D}${bindir} ${D}${sysconfdir}/systemd/system/multi-user.target.wants
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
  		install -d ${D}${systemd_unitdir}/system
  		install -m 0644 ${WORKDIR}/oscam.service ${D}${systemd_unitdir}/system/oscam.service
  		install -m 0644 ${WORKDIR}/gbox.service ${D}${systemd_unitdir}/system/gbox.service
  		install -m 0644 ${WORKDIR}/ciplushelper.service ${D}${systemd_unitdir}/system/ciplushelper.service
                ln -sf /lib/systemd/system/ciplushelper.service  ${D}${sysconfdir}/systemd/system/multi-user.target.wants
  		install -m 0644 ${WORKDIR}/lcd.service ${D}${systemd_unitdir}/system/lcd.service
		ln -sf /lib/systemd/system/lcd.service ${D}${systemd_unitdir}/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/-.mount ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/-.mount  ${D}${systemd_unitdir}/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/boot.mount ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/boot.mount  ${D}${systemd_unitdir}/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/mnt-partition_1.mount ${D}${systemd_unitdir}/system
                install -m 0644 ${WORKDIR}/mnt-partition_2.mount ${D}${systemd_unitdir}/system
                install -m 0644 ${WORKDIR}/mnt-partition_3.mount ${D}${systemd_unitdir}/system
                install -m 0644 ${WORKDIR}/mnt-partition_4.mount ${D}${systemd_unitdir}/system
                install -m 0644 ${WORKDIR}/mnt-partition_1.automount ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/mnt-partition_1.automount  ${D}${systemd_unitdir}/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/mnt-partition_2.automount  ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/mnt-partition_2.automount  ${D}${systemd_unitdir}/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/mnt-partition_3.automount  ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/mnt-partition_3.automount  ${D}${systemd_unitdir}/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/mnt-partition_4.automount  ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/mnt-partition_4.automount  ${D}${systemd_unitdir}/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/dev-mmcblk0p10.swap  ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/dev-mmcblk0p10.swap  ${D}${systemd_unitdir}/system/multi-user.target.wants
         	install -m 0755 ${WORKDIR}/firstboot.sh  ${D}${sbindir}
		install -m 0644 ${WORKDIR}/firstboot.service  ${D}${sysconfdir}/systemd/system
                ln -sf /etc/systemd/system/firstboot.service  ${D}${sysconfdir}/systemd/system/multi-user.target.wants
                install -m 0644 ${WORKDIR}/fstrim.service  ${D}${systemd_unitdir}/system
                install -m 0644 ${WORKDIR}/fstrim.timer  ${D}${systemd_unitdir}/system
                ln -sf /lib/systemd/system/fstrim.timer  ${D}${systemd_unitdir}/system/multi-user.target.wants
	fi
	install -m 0755 ${WORKDIR}/lcd.sh ${D}${bindir}
}
