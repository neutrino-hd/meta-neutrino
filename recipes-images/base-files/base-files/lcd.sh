#!/bin/sh

readlink -f /sys/class/block/sd[a-z]/device | grep ata && echo 1 > /proc/stb/lcd/symbol_hdd
readlink -f /sys/class/block/sd[a-z]/device | grep usb && echo 1 > /proc/stb/lcd/symbol_usb
