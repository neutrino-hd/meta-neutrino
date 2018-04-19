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
fi

if [ -z $(localedef --list-archive) ]; then
        # create directory needed for localedef
        mkdir /usr/lib/locale
        # create en-us as default utf8 locale
        localedef -c -f UTF-8 -i en_US en_US.UTF-8
        localedef -c -f UTF-8 -i de_DE de_DE.UTF-8
        localedef -c -f UTF-8 -i fr_FR fr_FR.UTF-8
        localedef -c -f UTF-8 -i ru_RU ru_RU.UTF-8
fi

echo "first boot script work done"
#job done, remove it from systemd services
systemctl disable firstboot.service
echo "firstboot script disabled"
echo "... reboot"
systemctl reboot
