#!/bin/sh

echo "starting firstboot script"

for DEV in /dev/mmcblk0p[3,5,7,9];do
if blkid $DEV | grep 'TYPE=' &> /dev/null;then
        echo "$DEV already present"
else
        mkfs.ext4 $DEV
fi
done

if blkid /dev/mmcblk0p10 | grep 'TYPE=' &> /dev/null;then
        echo "swapspace already present"
else
        mkswap /dev/mmcblk0p10
fi

(
echo r # Enter repair options
echo d # Create GPT backup
echo w # Write changes
echo y # confirm changes
) | gdisk /dev/mmcblk0


echo "first boot script work done"

#job done, remove it from systemd services
systemctl disable firstboot.service

echo "firstboot script disabled"

