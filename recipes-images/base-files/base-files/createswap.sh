#!/bin/sh 

cat /proc/swaps >> /dev/null && echo "Swap already present" && exit 0

mkswap /dev/mmcblk0p10
swapon /dev/mmcblk0p10