SUMMARY = "userspace tool for steam controllers"
HOMEPAGE = "https://github.com/rodrigorc/steamctrl.git"
SECTION = "games"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "systemd"

SRC_URI = "git://github.com/rodrigorc/steamctrl.git;protocol=https \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

