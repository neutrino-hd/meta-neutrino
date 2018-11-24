#!/bin/sh

echo "### Starting Neutrino ###"
/usr/bin/neutrino; RET=$?

if [ $RET -eq 0 ]; then
        :
elif [ $RET -eq 1 ]; then
        echo "...Shutdown" > /dev/dbox/oled0
        systemctl poweroff
elif [ $RET -eq 2 ]; then
        echo "...Reboot" > /dev/dbox/oled0
        systemctl reboot
fi

