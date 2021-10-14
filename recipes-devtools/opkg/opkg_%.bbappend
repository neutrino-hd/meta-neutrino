PACKAGECONFIG ??= "curl"

DESCRIPTION += "Additional upgrade script. opkg-upgrade"

LICENSE = "GPLv2+ & AGPL-3.0"

LIC_FILES_CHKSUM += " \
		file://${COMMON_LICENSE_DIR}/AGPL-3.0;md5=73f1eb20517c55bf9493b7dd6e480788 \
"

SRCREV_opkg-upgrade = "${AUTOREV}"
SRCREV_FORMAT = "${BPN}-${PV}"

SRC_URI += "git://github.com/dbt1/opkg-upgrade.git;protocol=https;name=opkg-upgrade"

do_install_append () {
        install -m 755 ${WORKDIR}/git/opkg-upgrade.sh ${D}${bindir}
        install -m 755 ${WORKDIR}/git/system-update ${D}${bindir}
}
