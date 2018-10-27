inherit pypi setuptools
require python-twisted.inc

RDEPENDS_${PN}-core += "${PYTHON_PN}-contextlib"

SRC_URI_append += "file://fix-writing-after-channel-is-closed.patch"
