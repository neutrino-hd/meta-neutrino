include nano.inc

PR = "1"

SRC_URI += "file://*.nanorc \
	    file://nanorc \
"

inherit pkgconfig

SRC_URI[md5sum] = "fd58e87c20abbf029e5c4b153c89232a"
SRC_URI[sha256sum] = "07192c320b74c1fb78437021e9affa6a9d55b806ee012de601902392eaa03601"

do_install(){
	install -d ${D}/${datadir}/nano ${D}/${sysconfdir} ${D}/${bindir}
	install -m 644 ${WORKDIR}/*.nanorc ${D}${datadir}/nano/
	install -m 644 ${WORKDIR}/nanorc ${D}${sysconfdir}/
	install -m 755 ${WORKDIR}/build/src/nano ${D}${bindir}/
}

do_install_append() {
	install -d ${D}/home/root/.local/share
}

FILES_${PN} += "/home/root"
