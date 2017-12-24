#!/bin/sh

echo Starting wlan0

/usr/sbin/wpa_cli terminate
sleep 1

wpa_supplicant -B -D nl80211 -i wlan0 -c/etc/wpa_supplicant.conf
