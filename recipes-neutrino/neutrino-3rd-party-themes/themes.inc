
inherit allarch

SECTION = "themes"
SUMMARY = "${DESCRIPTION}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
"
SUBPATH = "data/themes"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}"
