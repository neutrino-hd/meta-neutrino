DESCRIPTION = "An implementation of RFC 7049 - Concise Binary Object Representation (CBOR)."
DEPENDS +="${PYTHON_PN}-setuptools-scm-native"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI[sha256sum] = "09e9607ea50e2ebb718791d475f11620cb244b0cc3758ce2e76cf36e8372ad64"
SRC_URI[md5sum] = "b034d7edfd22c1f98e600aaedbd90f39"

inherit pypi setuptools3 ptest

SRC_URI += " \
        file://run-ptest \
"

RDEPENDS_${PN}-ptest += " \
       ${PYTHON_PN}-pytest \
       ${PYTHON_PN}-unixadmin \
"

do_install_ptest() {
      install -d ${D}${PTEST_PATH}/tests
        cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-datetime \
"

BBCLASSEXTEND = "native nativesdk"
