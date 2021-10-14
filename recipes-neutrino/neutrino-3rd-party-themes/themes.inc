
inherit allarch pkgconfig

SECTION = "themes"
PRE_DESCRIPTION = "Theme set"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
"
SUBPATH = "data"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}"


FILES_${PN} = "\
    /var/tuxbox/themes \
"
