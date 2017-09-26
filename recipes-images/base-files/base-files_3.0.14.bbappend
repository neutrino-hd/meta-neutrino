FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

SRC_URI += "file://profile \
	    file://inputrc \
	    file://nsswitch.conf \
	    file://oscam.service \
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
	install -d ${D}${localstatedir}/update ${D}${systemd_unitdir}/system/multi-user.target.wants
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
  		install -d ${D}${systemd_unitdir}/system
  		install -m 0644 ${WORKDIR}/oscam.service ${D}${systemd_unitdir}/system/oscam.service
	fi
}
