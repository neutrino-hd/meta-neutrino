DESCRIPTION = "mount UPnP server content as a linux filesystem"
HOMEPAGE = "http://djmount.sourceforge.net/"
LICENSE = "GPLv2+"
DEPENDS = "libupnp fuse gettext-native"
RDEPENDS_${PN} = "fuse"
PR = "r8"

LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

INITSCRIPT_NAME = "djmount"
INITSCRIPT_PARAMS = "defaults"

inherit autotools update-rc.d pkgconfig gettext

EXTRA_OECONF = "--with-external-libupnp"
EXTRA_OEMAKE += "'CFLAGS= -fcommon'"


SRC_URI = "${SOURCEFORGE_MIRROR}/djmount/djmount-0.71.tar.gz \
	file://init \
	file://0001-djmount-fixed-crash.patch \
	file://0002-djmount-fixed-crash-when-using-UTF-8-charset.patch \
	file://0003-djmount-fix-hang-with-asset-upnp.patch \
	file://0004-djmount-fix-incorrect-range-when-retrieving-content-.patch \
	file://0005-djmount-Fixes-for-new-autotools-versions.patch \
	file://0006-djmount-fix-newer-gcc.patch \
	file://0007-djmount-support-fstab-mounting.patch \
	file://0008-djmount-support-seeking-in-large-2gb-files.patch \
	file://0009-djmount-pthread-fix.patch \
	file://0010-djmount-fix-build-with-gettext-0.20.x.patch \
	file://0011-djmount-libupnp-1.6.6.patch \
	file://0012-djmount-libupnp-1.6.13.patch \
	file://0013-djmount-avoid-crash-by-using-size_t.patch \
	file://0014-djmount-enable-bigfiles.patch \
	file://0015-djmount-fix-compiler-warnings.patch \
	file://0016-djmount-codeset.patch \
"

SRC_URI[md5sum] = "c922753e706c194bf82a8b6ca77e6a9a"
SRC_URI[sha256sum] = "aa5bb482af4cbd42695a7e396043d47b53d075ac2f6aa18a8f8e11383c030e4f"

do_configure_prepend() {
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/libupnp/config.aux/config.rpath
}

do_compile () {
	oe_runmake LDFLAGS="${LDFLAGS} -Wl,--copy-dt-needed-entries"
}

do_install_append() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/init ${D}/etc/init.d/djmount
}
