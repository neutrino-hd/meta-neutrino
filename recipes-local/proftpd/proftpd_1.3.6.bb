SUMMARY = "Secure and configurable FTP server"
SECTION = "console/network"
HOMEPAGE = "http://www.proftpd.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb0d1484d11915fa88a6a7702f1dc184"

SRC_URI = "ftp://ftp.proftpd.org/distrib/source/${BPN}-${PV}.tar.gz \
           file://basic.conf.patch \
           file://proftpd-basic.init \
           file://default \
           file://close-RequireValidShell-check.patch \
           file://contrib.patch  \
           file://build_fixup.patch \
           file://proftpd.service \
	   file://ftpusers \
	   file://proftpd_banner \
	   file://proftpd.conf \
	   file://ftp.pam \
	   file://Makefile.patch \
           "

SRC_URI[md5sum] = "13270911c42aac842435f18205546a1b"
SRC_URI[sha256sum] = "91ef74b143495d5ff97c4d4770c6804072a8c8eb1ad1ecc8cc541b40e152ecaf"

DEPENDS += "libpam ncurses shadow libtool-native"
RDEPENDS_${PN} = "pam-plugin-listfile"

FTPUSER = "ftp"
FTPGROUP = "ftp"

inherit autotools-brokensep useradd systemd gettext

EXTRA_OECONF += " \
	--enable-dependency-tracking \
        --enable-curses \
        --without-getopt \
        --enable-shadow \
        --enable-ipv6 \
        --disable-strip \
        --enable-largefile \
        --disable-dso \
	--enable-nls \
	--disable-pcre \
"


# proftpd uses libltdl which currently makes configuring using
# autotools.bbclass a pain...
do_configure () {
    oe_runconf
}

do_configure_append() {
    # Configuring  test for fprint %llu ability fails if cross compiling.
    # This breaks the shown filesize for large files. Let's hack this, because we know we have it.
    sed -i "s|HAVE_LU|HAVE_LLU|" ${S}/configure
}

do_compile () {
    make
}

do_install () {
    make install-proftpd DESTDIR=${D}                
    make install-locales DESTDIR=${D}                
    make install-utils DESTDIR=${D}              
    make install-modules DESTDIR=${D}
    install -m 644 ${WORKDIR}/proftpd.conf ${D}${sysconfdir}/proftpd.conf
    install -m 644 ${WORKDIR}/proftpd_banner ${D}${sysconfdir}/welcome.msg
    install -d ${D}${sysconfdir}/pam.d/
    cp ${WORKDIR}/ftp.pam ${D}${sysconfdir}/pam.d/ftp
    sed -i "s:/lib/security:${base_libdir}/security:" ${D}${sysconfdir}/pam.d/ftp

    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/proftpd.service ${D}/${systemd_unitdir}/system
    sed -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
        -e 's,@SYSCONFDIR@,${sysconfdir},g' \
        -e 's,@SBINDIR@,${sbindir},g' \
        -i ${D}${systemd_unitdir}/system/*.service

    sed -e 's|--sysroot=${STAGING_DIR_HOST}||g' \
        -e 's|${STAGING_DIR_NATIVE}||g' \
        -e 's|-fdebug-prefix-map=[^ ]*||g' \
        -i ${D}/${bindir}/prxs
}

do_install_append () {
    rm -rf ${D}${libexecdir}
    find ${D}${datadir}/locale/. -type d -maxdepth 1 -not -name en_US -exec rm -rf {} \;
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "proftpd.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system ${FTPGROUP}"
USERADD_PARAM_${PN} = "--system -g ${FTPGROUP} --home-dir /var/lib/${FTPUSER} --no-create-home \
                       --shell /bin/false ${FTPUSER}"

FILES_${PN} += "/home/${FTPUSER} \
		/usr/share/locale \
"

RDEPENDS_${PN} += "perl"
