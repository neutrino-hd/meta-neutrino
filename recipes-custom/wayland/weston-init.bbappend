FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "file://weston.service"

SYSTEMD_AUTO_ENABLE = "disable"

REQUIRED_DISTRO_FEATURES = ""
