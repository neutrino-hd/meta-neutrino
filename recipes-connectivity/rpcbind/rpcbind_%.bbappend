do_install_append_append () {
	ln -sf ${systemd_unitdir}/system/rpcbind.socket ${D}${systemd_unitdir}/systemd/system/sockets.target.wants/rpcbind.socket
	ln -sf ${systemd_unitdir}/system/rpcbind.service ${D}${systemd_unitdir}/systemd/system/multi-user.target.wants/rpcbind.service
}
