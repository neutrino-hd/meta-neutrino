FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

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
		pulseaudio-module-rescue-streams \
		pulseaudio-module-always-sink \
		pulseaudio-module-suspend-on-idle \
		pulseaudio-module-position-event-sounds \
		pulseaudio-module-role-cork \
		pulseaudio-module-switch-on-port-available \
		pulseaudio-module-switch-on-connect \
		pulseaudio-module-systemd-login \
		"
