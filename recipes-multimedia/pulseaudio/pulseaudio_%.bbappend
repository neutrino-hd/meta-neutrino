FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "file://client.conf \
		   file://default.pa \
		   file://pulseaudio.service \
		   file://pulseaudio.socket \
		   file://pulseaudio-bluetooth.conf \
		   file://pulseaudio-system.conf \
		   file://system.pa \
"

PACKAGECONFIG_append = "systemd autospawn-for-root"

RRECOMMENDS_${PN} += " \
		alsa-utils \
		alsa-plugins-pulseaudio-conf \
		pulseaudio-server \
		pulseaudio-misc \
		pulseaudio-pa-info \
		pulseaudio-module-native-protocol-tcp \
		pulseaudio-module-bluetooth-policy \
		pulseaudio-module-bluez5-discover \
		pulseaudio-module-bluez5-device \
		pulseaudio-module-filter-apply \
		pulseaudio-module-filter-heuristics \
		pulseaudio-module-udev-detect \
		pulseaudio-module-null-sink \
		pulseaudio-module-device-restore \
		pulseaudio-module-stream-restore \
		pulseaudio-module-card-restore \
		pulseaudio-module-augment-properties \
		pulseaudio-module-detect \
		pulseaudio-module-alsa-sink \
		pulseaudio-module-alsa-source \
		pulseaudio-module-alsa-card \
		pulseaudio-module-native-protocol-unix \
		pulseaudio-module-default-device-restore \
		pulseaudio-module-intended-roles \
		pulseaudio-module-always-sink \
		pulseaudio-module-suspend-on-idle \
		pulseaudio-module-position-event-sounds \
		pulseaudio-module-role-cork \
		pulseaudio-module-switch-on-port-available \
		pulseaudio-module-switch-on-connect \
		pulseaudio-module-systemd-login \
		"
		
do_install_append() {
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants
	install -m644 ${WORKDIR}/pulseaudio-system.conf ${D}${sysconfdir}/dbus-1/system.d
	install -m644 ${WORKDIR}/pulseaudio-bluetooth.conf ${D}${sysconfdir}/dbus-1/system.d
	install -m644 ${WORKDIR}/system.pa ${D}${sysconfdir}/pulse
	install -m644 ${WORKDIR}/default.pa ${D}${sysconfdir}/pulse
	install -m644 ${WORKDIR}/client.conf ${D}${sysconfdir}/pulse
	install -m644 ${WORKDIR}/pulseaudio.service ${D}${systemd_unitdir}/system
	install -m644 ${WORKDIR}/pulseaudio.socket ${D}${systemd_unitdir}/system
	ln -sf ${systemd_unitdir}/system/pulseaudio.service ${D}${systemd_unitdir}/system/multi-user.target.wants
	ln -sf ${systemd_unitdir}/system/pulseaudio.socket ${D}${systemd_unitdir}/system/multi-user.target.wants	
}

FILES_${PN} += "${systemd_unitdir}"
