SRC_URI_append = "\
    git://anongit.freedesktop.org/gstreamer/common;branch=${GST_BRANCH};destsuffix=git/common;name=common \
"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

SRCREV_FORMAT = "base"

PV = "1.12.3+git${SRCPV}"

GST_BRANCH ?= "1.12"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

