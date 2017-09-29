FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "file://smb.conf \
				   file://smb.service \
				   file://add_smbusr.sh \
"

do_install_append() {
	install -m 0755 ${WORKDIR}/add_smbusr.sh ${D}${sysconfdir}/samba
	install -m 644 ${WORKDIR}/smb.service ${D}${systemd_unitdir}/system
	install -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba
	sed -i "s|COOLSTREAM|${MACHINE}|" ${D}${sysconfdir}/samba/smb.conf
}
