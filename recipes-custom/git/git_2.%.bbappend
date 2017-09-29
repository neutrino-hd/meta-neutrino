DEPENDS = "zlib virtual/libiconv"

EXTRA_OEMAKE += "NO_GETTEXT=1 \
		 NO_PERL=1 \
		 NO_OPENSSL=1 \
		 NO_EXPAT=1 \
		 NO_TCLTK=1 \
		 NO_GETTEXT=1 \
		 NO_CURL=1 \
		 NO_PYTHON=1 \
		 NO_BLK_SHA1=1 \
"
perl_native_fixup () {
:
}

PERLTOOLS = ""

