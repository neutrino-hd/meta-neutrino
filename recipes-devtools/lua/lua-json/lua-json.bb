DESCRIPTION = "Simple JSON Encode/Decode in Pure Lua"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = " \
	file://JSON.lua;beginline=1;endline=16;md5=47b3a45c4ad6038f261c8a6670f02fb5 \
"
HOMEPAGE = "http://regex.info/blog/lua/json/"
DEPENDS="virtual/lua"

include ../lua.inc

SRC_URI = " \
	http://regex.info/code/JSON.lua \
"

SRC_URI[md5sum] = "dc70adca6d27ec74b4547913ecd5957d"
SRC_URI[sha256sum] = "abea53010e9e6897095623967eee6b6d74b7f8da13442176dcef04e01160e4f2"

S = "${WORKDIR}"

FILES_${PN} += "/usr/share/lua/${LUA_VER}"
# no need for -dev and -dbg..
PACKAGES = "${PN}"

do_install () {
	install -d ${D}/usr/share/lua/${LUA_VER}/
	install -m 644 ${S}/JSON.lua ${D}/usr/share/lua/${LUA_VER}/json.lua
}

BBCLASSEXTEND = "native nativesdk"
