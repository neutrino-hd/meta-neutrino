SUMMARY = "Source code documentation for Neutrino"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${THISDIR}/${PN}/COPYING;md5=801f80980d171dd6425610833a22dbe6"

PACKAGE_ARCH = "all"

PV = "${FLAVOUR}-${SRCPV}"
SRCREV = "${AUTOINC}"
PR = "r1"

S = "${WORKDIR}/src"
SRC_PREFIX = "git"

DEPLOY_DOC_DIR = "${DEPLOY_DIR}/source-doc"

SRCREV_doc = "${AUTOREV}"
SRCREV_widget = "${AUTOREV}"
SRCREV_components = "${AUTOREV}"
SRCREV_FORMAT = "doc"

SRC_URI = "	git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https;name=doc;subpath=doc;destsuffix=${SRC_PREFIX}_doc \
		git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https;name=widget;subpath=src/gui/widget;destsuffix=${SRC_PREFIX}_widget \
		git://github.com/tuxbox-neutrino/gui-neutrino.git;protocol=https;name=components;subpath=src/gui/components;destsuffix=${SRC_PREFIX}_components \
"

do_patch () {
	cp -a ${WORKDIR}/${SRC_PREFIX}_doc/nhttpd ${S}
	rm -rf ${S}/nhttpd/.git

	rm -rf ${S}/widget
	cp -a ${WORKDIR}/${SRC_PREFIX}_widget ${S}/widget
	rm -rf ${S}/widget/.git
	sed -i -e 's/= 1.0/= ${PV}/' ${S}/widget/Doxyfile
	sed -i -e 's/AUTOINC/git/' ${S}/widget/Doxyfile
	sed -i -e 's/Widget Documentation/Neutrino Widget Classes/' ${S}/widget/Doxyfile

	rm -rf ${S}/components
	cp -a ${WORKDIR}/${SRC_PREFIX}_components ${S}/components
	rm -rf ${S}/components/.git
	sed -i -e 's/= 1.0/= ${PV}/' ${S}/components/Doxyfile
	sed -i -e 's/AUTOINC/git/' ${S}/components/Doxyfile
	sed -i -e 's/CComponents Documentation/Neutrino Component Classes/' ${S}/components/Doxyfile
}

do_compile () {
	cd ${S}/widget && doxygen ${S}/widget/Doxyfile
	cd ${S}/components && doxygen ${S}/components/Doxyfile
}

do_install () {
	# Clean up.
	rm -rf ${DEPLOY_DOC_DIR}

	# Install content from source directory into deploy directory.
	install -d ${DEPLOY_DOC_DIR}/nhttpd
	cp -a ${S}/nhttpd ${DEPLOY_DOC_DIR}

	install -d ${DEPLOY_DOC_DIR}/widget
	cp -a ${S}/widget/doc/html/* ${DEPLOY_DOC_DIR}/widget

	install -d ${DEPLOY_DOC_DIR}/components
	cp -a ${S}/components/doc/html/* ${DEPLOY_DOC_DIR}/components
}

# not required
do_package () {
	:
}

# not required
do_package_write_ipk () {
	:
}
