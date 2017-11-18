#!/bin/sh

if dmesg | grep -i SATA | grep -i "link up";then
        echo 1 > /proc/stb/lcd/symbol_hdd
fi

if dmesg | grep -i USB | grep -i "USB Mass Storage device detected";then
        echo 1 > /proc/stb/lcd/symbol_usb
fi
