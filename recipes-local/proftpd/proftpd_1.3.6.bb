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
           "

SRC_URI[md5sum] = "13270911c42aac842435f18205546a1b"
SRC_URI[sha256sum] = "91ef74b143495d5ff97c4d4770c6804072a8c8eb1ad1ecc8cc541b40e152ecaf"

DEPENDS += "libpam ncurses shadow libpcre"
RDEPENDS_${PN} = "pam-plugin-listfile"

inherit autotools-brokensep useradd systemd gettext

EXTRA_OECONF += " \
	--enable-dependency-tracking \
        --enable-curses \
        --without-getopt \
        --enable-shadow \
        --enable-ipv6 \
        --disable-strip \
        --enable-largefile \
        --enable-dso \
	--enable-nls \
	--enable-pcre \
"


# proftpd uses libltdl which currently makes configuring using
# autotools.bbclass a pain...
do_configure () {
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.guess ${S}
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.sub ${S}
    oe_runconf
    cp ${STAGING_BINDIR_CROSS}/${HOST_SYS}-libtool ${S}/libtool
}

do_configure_append() {
    # Configuring  test for fprint %llu ability fails if cross compiling.
    # This breaks the shown filesize for large files. Let's hack this, because we know we have it.
    sed -i "s|HAVE_LU|HAVE_LLU|" ${S}/configure
}

FTPUSER = "ftp"
FTPGROUP = "ftp"

do_install () {
    oe_runmake DESTDIR=${D} install
    [ -d ${D}${libexecdir} ] && rm -rf ${D}${libexecdir}
    [ -d ${D}${libdir} ] && rm -rf ${D}${libdir}
    sed -i '/ *User[ \t]*/s/ftp/${FTPUSER}/' ${D}${sysconfdir}/proftpd.conf
    sed -i '/ *Group[ \t]*/s/ftp/${FTPGROUP}/' ${D}${sysconfdir}/proftpd.conf
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/proftpd-basic.init ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!/var/!${localstatedir}/!g' ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!^PATH=.*!PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}${sysconfdir}/init.d/proftpd

    install -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/default ${D}${sysconfdir}/default/proftpd
    install -m 644 ${WORKDIR}/ftpusers ${D}${sysconfdir}/ftpusers
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

    # ftpmail perl script, which reads the proftpd log file and sends
    # automatic email notifications once an upload finishs,
    # depends on an old perl Mail::Sendmail
    # The Mail::Sendmail has not been maintained for almost 10 years
    # Other distribution not ship with ftpmail, so do the same to
    # avoid confusion about having it fails to run
    rm -rf ${D}${bindir}/ftpmail
    rm -rf ${D}${mandir}/man1/ftpmail.1
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
