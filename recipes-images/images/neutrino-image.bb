# Base this image on core-image-minimal

include neutrino-image-base.inc

IMAGE_INSTALL += " \
	virtual/neutrino \
	neutrino-plugins \
	neutrino-lua-plugins \
	neutrino-plugin-mediathek \
	neutrino-plugin-rockpalast \
	"


KERNEL_WIFI_DRIVERS = " \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-zd1211 \
	\
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-r8712u \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-zd1211rw \
	"

EXTRA_KERNEL_WIFI_DRIVERS = " \
	firmware-rtl8192cu \
	\
	kernel-module-r8188eu \
	kernel-module-rtl8192cu \
	"

EXTERNAL_WIFI_DRIVERS = " \
	firmware-rtl8192cu \
	\
	rtl8192cu \
	rtl8188eu \
	rtl8192eu \
	"
