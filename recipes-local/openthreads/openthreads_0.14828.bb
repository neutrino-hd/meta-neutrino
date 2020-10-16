SUMMARY = "OpenThreads is a cross platform, object orientated threading library."
DESCRIPTION = "OpenThreads is a cross platform, object orientated threading library."
HOMEPAGE = "http://www.openscenegraph.org/"
SECTION = "libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2c38926f611bfbd5a3be0f817c8d2dad \
"
DEPENDS = ""

SRCREV = "f8444d7002fde691198c68e66888cf589a233628"

SRC_URI = "git://github.com/neutrino-images/ni-openthreads;protocol=https \
"

S = "${WORKDIR}/git"



inherit cmake 

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release \
                  -DCMAKE_SYSTEM_NAME=Linux \
                  -D_OPENTHREADS_ATOMIC_USE_GCC_BUILTINS_EXITCODE=1 \
                  -D_OPENTHREADS_ATOMIC_USE_GCC_BUILTINS=1 \
"

do_install_append() {
	[ -d ${D}/usr/lib64 ] && mv ${D}/usr/lib64 ${D}/usr/lib || exit 0;
}

ARM_INSTRUCTION_SET = "arm"
