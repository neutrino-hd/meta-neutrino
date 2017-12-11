DESCRIPTION = "Driver for Realtek USB wireless devices"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://os_dep/linux/os_intfs.c;endline=19;md5=72c75de415f1e8a42587d170459677e2"

inherit module machine_kernel_pr

SRC_URI = "git://github.com/pvaret/rtl8192cu-fixes.git;protocol=https \
	"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"

MACHINE_KERNEL_PR_append = ".0"

CFLAGS_append += "-Wno-error=date-time -Wno-int-conversion --Wno-error=implicit-function-declaration"

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/8192cu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}



