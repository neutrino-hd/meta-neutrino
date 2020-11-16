SUMMARY = "udev rules for game controllers"
HOMEPAGE = "https://github.com/ValveSoftware/steam-devices"
SECTION = "games"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9938a391463fd6513858b1d7c6835613"

SRC_URI = "git://github.com/ValveSoftware/steam-devices.git;protocol=https"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 755 ${S}/60-steam-input.rules ${D}${sysconfdir}/udev/rules.d/
	install -m 755 ${S}/60-steam-vr.rules ${D}${sysconfdir}/udev/rules.d/
}

FILES_${PN} += "${sysconfdir}"

