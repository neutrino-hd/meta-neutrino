# Base this image on core-image-minimal

include neutrino-image-base.inc

IMAGESIZE ?= "" 
IMAGE_INSTALL += "${@'${BIG_IMAGE_FILES}' if IMAGESIZE != 'small' else ''}"
