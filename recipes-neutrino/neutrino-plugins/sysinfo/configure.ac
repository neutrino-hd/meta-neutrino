AC_INIT([@PN@],[@PV@],[@HP@])
AC_CONFIG_SRCDIR([sysinfo])
AM_INIT_AUTOMAKE([foreign])
m4_ifdef([AM_SILENT_RULES], [AM_SILENT_RULES])
AC_GNU_SOURCE

TUXBOX_APPS
TUXBOX_APPS_DIRECTORY
TUXBOX_APPS_PKGCONFIG
TUXBOX_BOXTYPE

AC_PROG_CC
AC_PROG_CXX
PKG_PROG_PKG_CONFIG
## both disabled => libtool still defaults to static
##                  at least the libtool I tested with ;-)
## --enable-shared => build only shared
## --enable-shared --enable-static => build both
AC_DISABLE_STATIC
AC_SYS_LARGEFILE
LT_INIT

PKG_CHECK_MODULES(CURL,libcurl)
PKG_CHECK_MODULES(FREETYPE,freetype2)
PKG_CHECK_MODULES(PNG,libpng)

AC_ARG_WITH(neutrino-source,
	AS_HELP_STRING([--with-neutrino-source=PATH], [path where Neutrino source is located @<:@default=NONE@:>@]]),
	[NEUTRINO_SOURCE="$withval"],
	[NEUTRINO_SOURCE=""])
AC_SUBST(NEUTRINO_SOURCE)

AC_ARG_WITH(neutrino-build,
	AS_HELP_STRING([--with-neutrino-build=PATH], [path where Neutrino is built @<:@default=NONE@:>@]]),
	[NEUTRINO_BUILD="$withval"],
	[NEUTRINO_BUILD=""])
AC_SUBST(NEUTRINO_BUILD)

AC_CONFIG_FILES([
Makefile
sysinfo/Makefile
])

AC_OUTPUT
