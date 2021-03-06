DESCRIPTION = "xupnpd - eXtensible UPnP agent"
HOMEPAGE = "http://xupnpd.org"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=193ff0a3bc8b0d2cb0d1d881586d3388"

DEPENDS += "virtual/lua openssl"
SRCREV = "${AUTOREV}"
SRC_URI = "\
	git://github.com/clark15b/xupnpd.git;branch=master \
	file://xupnpd.patch \
	file://xupnpd-dont-bind-daemon-to-specific-ip-address.patch \
	file://xupnpd.service \
"	

PV = "${SRCPV}"

S = "${WORKDIR}/git/src"

inherit base systemd

SYSTEMD_SERVICE_${PN} = "xupnpd.service"

# this is very ugly, but the xupnpd makefile is utter crap :-(
SRC = "main.cpp soap.cpp mem.cpp mcast.cpp luaxlib.cpp luaxcore.cpp luajson.cpp luajson_parser.cpp"

CFLAGS += "-I${STAGING_INCDIR}/luajit-2.1"

do_compile () {
	${CC} -O2 -c -o md5.o md5c.c
	${CC} ${CFLAGS} ${LDFLAGS} -DWITH_URANDOM -o xupnpd ${SRC} md5.o -lluajit-5.1 -lm -ldl -lstdc++ -rdynamic -lssl -lcrypto
}


do_install () {
	install -d -m 0644 ${D}/usr/share/xupnpd/config ${D}/usr/share/xupnpd/playlists ${D}/usr/share/xupnpd/plugins ${D}${systemd_unitdir}/system ${D}${sysconfdir}/systemd/system/multi-user.target.wants/ ${D}${sysconfdir}/systemd/system/timers.target.wants/
	install -m 644 ${WORKDIR}/xupnpd.service ${D}${systemd_unitdir}/system/xupnpd.service
	ln -sf ${systemd_unitdir}/system/xupnpd.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/xupnpd.service
	install -D -m 0755 ${S}/xupnpd ${D}${bindir}/xupnpd
	install -m 644 ${S}/plugins/xupnpd_stb.lua ${D}/usr/share/xupnpd/plugins
	cp -r ${S}/profiles	${D}/usr/share/xupnpd/
	cp -r ${S}/ui		${D}/usr/share/xupnpd/
	cp -r ${S}/www		${D}/usr/share/xupnpd/
	cp ${S}/*.lua		${D}/usr/share/xupnpd/
}
