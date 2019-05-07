FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN}_append += "glibc-gconv-ibm850"

SRC_URI_append += "file://smb.service \
		   file://samba \
"

do_install_append() {
	install -d ${D}${sysconfdir}/pam.d
	install -m 0644 ${WORKDIR}/samba ${D}${sysconfdir}/pam.d
	install -m 644 ${WORKDIR}/smb.service ${D}${systemd_unitdir}/system
}

pkg_postinst_ontarget_${PN}() {
#!/bin/sh

pdbedit -L | grep root >> /dev/null || smbpasswd -n -a root
}
