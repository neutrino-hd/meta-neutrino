SUMMARY = "kodi inputstream addon for rtmp"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit kodi-addon

DEPENDS += "expat"

SRCREV = "${AUTOREV}"

PV = "2.0.9+gitr${SRCPV}"

KODIADDONBRANCH = "Leia"

SRC_URI = "git://github.com/xbmc/inputstream.rtmp.git;protocol=https;branch=${KODIADDONBRANCH} \
         "

S = "${WORKDIR}/git"

KODIADDONNAME = "inputstream.rtmp"
