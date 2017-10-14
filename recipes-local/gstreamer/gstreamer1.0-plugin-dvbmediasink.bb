DESCRIPTION = "gstreamer dvbmediasink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

# Most machine recipes still (r)depend on gst-plugin-dvbmediasink
RPROVIDES_${PN} = "gst-plugin-dvbmediasink"
RREPLACES_${PN} = "gst-plugin-dvbmediasink"
RCONFLICTS_${PN} = "gst-plugin-dvbmediasink"

DEPENDS = "glib-2.0-native gstreamer1.0 gstreamer1.0-plugins-base libdca"

GSTVERSION = "1.0"

SRC_URI = "git://github.com/OpenPLi/gst-plugin-dvbmediasink.git;branch=gst-1.0"

S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"

inherit gitpkgv

PV = "${GSTVERSION}+git${SRCPV}"
PKGV = "${GSTVERSION}+git${GITPKGV}"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-${GSTVERSION}/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-${GSTVERSION}/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-${GSTVERSION}/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-${GSTVERSION}/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DVBMEDIASINK_CONFIG = "--enable-silent-rules \
					   --with-spark \
					   --with-h265 \
					   --with-vb8 \
					   --with-vb9 \
					   --with-dts \
					   --with-wma \
					   --with-wmv \
					   --with-pcm \
					   --with-eac3 \
					   --with-dts \
"


EXTRA_OECONF = "${DVBMEDIASINK_CONFIG} --with-gstversion=${GSTVERSION}"
