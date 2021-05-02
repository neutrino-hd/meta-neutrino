FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

SRC_URI += " \
		file://backup@.service \
		file://banner.template \
		file://cccam.service \
		file://firstboot.service \
		file://flash@.service \
		file://fstab \
		file://fstrim.service \
		file://fstrim.timer \
		file://gbox.service \
		file://getty-toggle \
		file://inputrc \
		file://local_cam.sh \
		file://locale.conf \
		file://local.service \
		file://mount@.service \
		file://net-umount.service \
		file://net-umount.sh \
		file://nsswitch.conf \
		file://restore@.service \
		file://shells \
		file://vconsole.conf \
"

RDEPENDS_${PN}_append += "coreutils"

BASEFILESISSUEINSTALL = "do_custom_baseissueinstall"

do_custom_baseissueinstall() {
	do_install_basefilesissue
	install -m 644 ${WORKDIR}/issue*  ${D}${sysconfdir}
	cat ${WORKDIR}/banner.template								>> ${D}${sysconfdir}/welcome
	printf "\n\nWelcome at ${IMAGE_BASENAME} image on ${MACHINE_BRAND} ${MACHINE_NAME}\n"	>> ${D}${sysconfdir}/welcome
	cat ${D}${sysconfdir}/welcome 								> ${D}${sysconfdir}/issue
	cat ${D}${sysconfdir}/welcome 								> ${D}${sysconfdir}/issue.net
}

do_install_append () {
	install -d ${D}${localstatedir}/update ${D}${systemd_unitdir}/system/multi-user.target.wants ${D}${bindir} ${D}${sysconfdir}/systemd/system/multi-user.target.wants
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}${systemd_unitdir}/system ${D}${sysconfdir}/modules-load.d
		install -m 0755 ${WORKDIR}/local_cam.sh ${D}${bindir}
		install -m 0644 ${WORKDIR}/locale.conf  ${D}${sysconfdir}
		install -m 0644 ${WORKDIR}/firstboot.service  ${D}${sysconfdir}/systemd/system
		ln -sf /etc/systemd/system/firstboot.service  ${D}${sysconfdir}/systemd/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/fstrim.service  ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/fstrim.timer  ${D}${systemd_unitdir}/system
		ln -sf /lib/systemd/system/fstrim.timer  ${D}${systemd_unitdir}/system/multi-user.target.wants
		install -m 0755 ${WORKDIR}/net-umount.sh  ${D}${bindir}
		install -m 0644 ${WORKDIR}/net-umount.service  ${D}${systemd_unitdir}/system
		ln -sf /lib/systemd/system/net-umount.service  ${D}${sysconfdir}/systemd/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/mount@.service  ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/flash@.service  ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/restore@.service  ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/backup@.service  ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/local.service  ${D}${systemd_unitdir}/system
		ln -sf /lib/systemd/system/local.service  ${D}${systemd_unitdir}/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/vconsole.conf  ${D}${sysconfdir}
		install -m 0755 ${WORKDIR}/getty-toggle  ${D}${bindir}
		install -m 0644 ${WORKDIR}/gbox.service ${D}${systemd_unitdir}/system
		install -m 0644 ${WORKDIR}/cccam.service ${D}${systemd_unitdir}/system
	fi
	rm -rf ${D}${sysconfdir}/profile

# 	Hack to remove dirty files from base-files package.
# 	Allthough it's possible to remove some files from SRC_URI with help of local.conf with such line:
#	SRC_URI_remove_pn-base-files = " file1 file2 ... "
#
# 	... despite that without any check for existing files the do_install_append task will execute all defined install commands
# 	so the install task will fail and the build aborts.
# 	Problem:
#	do_install_append() can not be override completly with appropriate adjustments inside local.conf entries and
#	in the best case commands will be just append.
# 	If you want to remove some files, add file paths to ${WORKDIR}/.remove
#	and the follow command will delete defined files.
#	Here for example add these lines to your local.conf:
# 	do_install_prepend_pn-base-files  () {
# 		echo "${D}${systemd_unitdir}/system/<file-to-remove>" > ${WORKDIR}/.remove
# 	}
	if test -f ${WORKDIR}/.remove; then
		for f in  `cat ${WORKDIR}/.remove` ; do
			rm -f ${f}
		done
		rm -f ${WORKDIR}/.remove
	fi
}
