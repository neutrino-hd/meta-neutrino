# Base this image on core-image-minimal

include neutrino-image-base.inc

IMAGESIZE ?= "" 
IMAGE_INSTALL += "${@'${BIG_IMAGE_FILES} ${KERNEL_WIFI_DRIVERS} ${EXTRA_KERNEL_WIFI_DRIVERS} ${EXTERNAL_WIFI_DRIVERS}' if IMAGESIZE != 'small' else ''}"
