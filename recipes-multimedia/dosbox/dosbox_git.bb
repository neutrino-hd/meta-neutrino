SUMMARY = "DOSBox is a DOS-emulator that uses the SDL2-library"
HOMEPAGE = "http://www.dosbox.com/" 

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f" 

DEPENDS = "libsdl2 libsdl2-net libpng virtual/libgles2 fluidsynth"

inherit autotools pkgconfig

SRC_URI = "git://github.com/aqualung99/dosbox-0.74-ES.git;protocol=https \
	   file://0001-use-pkgconfig-to-find-sdl2.patch \
	   file://0001-sdlmain.cpp-this-one-is-available-only-on-windows.patch \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
PV = "0.74-ES"

