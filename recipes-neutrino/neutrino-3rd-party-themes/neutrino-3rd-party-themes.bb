
inherit allarch pkgconfig

SECTION = "themes"
DESCRIPTION = "Neutrino-3rd Party Theme Sets"
HOMEPAGE = ""
MAINTAINER = "3rd Party Providers"
SUMMARY = "${DESCRIPTION} by ${MAINTAINER}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
"
PR = "r0"
PV = "1.0"

DEPENDS += " themes-ni themes-tango  "




