FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN}_append += "glibc-gconv-ibm850"

SRC_URI_append += "file://smb.conf \
		   file://smb.service \
		   file://samba \
"

do_install_append() {
	install -d ${D}${sysconfdir}/pam.d
	install -m 0644 ${WORKDIR}/samba ${D}${sysconfdir}/pam.d
	install -m 644 ${WORKDIR}/smb.service ${D}${systemd_unitdir}/system
	install -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba
	sed -i "s|COOLSTREAM|${MACHINE}|" ${D}${sysconfdir}/samba/smb.conf
}

pkg_postinst_ontarget_${PN}() {
#!/bin/sh

if pdbedit -L | grep root >> /dev/null;then
        exit
else
    	smbpasswd -n -a root
fi
}
