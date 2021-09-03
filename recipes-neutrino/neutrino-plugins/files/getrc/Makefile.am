AM_CPPFLAGS =  \
	@FREETYPE_CFLAGS@

# AM_CPPFLAGS +=  \
# 	-I$(top_srcdir)/include

bin_PROGRAMS = getrc

getrc_SOURCES = \
	getrc.c \
	rc_device.c \
	io.c

getrc_LDADD = \
	@FREETYPE_LIBS@
