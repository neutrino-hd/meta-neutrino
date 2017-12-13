#!/bin/sh

echo "starting firstboot script"

rootdevice=$(sed -e 's/^.*root=//' -e 's/ .*$//' < /proc/cmdline)


if [ $rootdevice = /dev/mmcblk0p3 ]; then
        for DEV in /dev/mmcblk0p[3,5,7,9];do
                if blkid $DEV | grep 'TYPE=' &> /dev/null;then
                        echo "$DEV already present"
                else
                        mkfs.ext4 $DEV
                fi
        done

        (
                echo r # Enter repair options
                echo d # Create GPT backup
                echo w # Write changes
                echo y # confirm changes
        ) | gdisk /dev/mmcblk0


        if blkid /dev/mmcblk0p10 | grep 'TYPE=' &> /dev/null;then
                echo "swapspace already present"
        else
                mkswap /dev/mmcblk0p10
        fi

        echo "first boot script work done"

        #job done, remove it from systemd services
        systemctl disable firstboot.service

        echo "firstboot script disabled ... reboot"
        systemctl reboot

else
        echo "Nothing to do"

        #job done, remove it from systemd services
        systemctl disable firstboot.service

        echo "firstboot script disabled"
fi

