FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "file://0001-avoid-race-between-systemd-udevd-and-systemd-modules.patch"

do_patch[postfuncs] = ""
