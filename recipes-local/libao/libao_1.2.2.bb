SUMMARY = "Cross-platform audio output library and plugins"
DESCRIPTION = "Libao is a cross-platform audio library that allows programs to \
               output audio using a simple API on a wide variety of platforms."
SECTION = "multimedia"
HOMEPAGE = "https://www.xiph.org/ao/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI="git://git.xiph.org/libao.git;tag=1.2.2;protocol=https"

DEPENDS = "pulseaudio alsa-lib"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGES += "${BPN}-ckport"
PACKAGES_DYNAMIC += "^${BPN}-plugin-.*"

do_install_append () {
    find "${D}" -name '*.la' -exec rm -f {} +
}

python populate_packages_prepend () {
    rootdir = bb.data.expand('${libdir}/ao/plugins-4', d)
    rootdir_dbg = bb.data.expand('${libdir}/ao/plugins-4/.debug', d)
    do_split_packages(d, rootdir, '^(.*)\.so$', output_pattern='${BPN}-plugin-%s', description='AO %s plugin')
    do_split_packages(d, rootdir_dbg, '^(.*)\.so$', output_pattern='${BPN}-plugin-%s-dbg', description='AO %s plugin debug data')
}

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'alsa pulseaudio', d)}"
PACKAGECONFIG[esound] = "--enable-esd,--disable-esd,esound"
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"
FILES_${BPN}-ckport = "${libdir}/ckport"
