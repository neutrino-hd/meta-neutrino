DESCRIPTION = "Tor is a network of virtual tunnels that allows people and groups \
              to improve their privacy and security on the Internet."
HOMEPAGE = "http://tor.eff.org"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"
DEPENDS = "libevent openssl zlib socat tsocks"
RDEPENDS_${PN} = "socat tsocks"
SRC_URI = "http://tor.eff.org/dist/${P}.tar.gz \
           file://tor.init \
	   file://tsocks.conf \
"

inherit autotools update-rc.d

INITSCRIPT_NAME = "tor"

EXTRA_OECONF += "--disable-tool-name-check"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d ${D}${sysconfdir}/tor
	install ${WORKDIR}/tor.init ${D}${sysconfdir}/init.d/tor
	install ${WORKDIR}/tsocks.conf ${D}${sysconfdir}/tor/tor-tsocks.conf
}

SRC_URI[md5sum] = "ec7c9f588c9e1a42c09bcc097a1e55eb"
SRC_URI[sha256sum] = "1df5dd4894bb2f5e0dc96c466955146353cf33ac50cd997cfc1b28ea3ed9c08f"


