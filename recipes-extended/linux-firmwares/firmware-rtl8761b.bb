DESCRIPTION = "Firmware for rtl8761b"

SRC_URI = "file://rtl8761b_config.bin \
	   file://rtl8761b_fw.bin \
	   file://license \
"

LICENSE = "proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license;md5=17a6b3d5436a55985b200c725761907a"


do_install() {
	install -d ${D}${base_libdir}/firmware/rtl_bt
	install -m 644 ${WORKDIR}/rtl8761b_config.bin ${D}${base_libdir}/firmware/rtl_bt
	install -m 644 ${WORKDIR}/rtl8761b_fw.bin ${D}${base_libdir}/firmware/rtl_bt
}

FILES_${PN} = "${base_libdir}"

