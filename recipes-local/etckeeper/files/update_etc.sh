#!/bin/sh

/usr/bin/findkerneldevice.py

kerneldevice=$(ls -lh /dev/kernel)
kernelpartition=$(echo $kerneldevice | cut -d"/" -f5)


if [ $kernelpartition = mmcblk0p2 ]; then
        GIT__URL='/media/sda1/service/partition1/git/etc.git'
elif [ $kernelpartition = mmcblk0p4 ]; then
        GIT__URL='/media/sda1/service/partition2/git/etc.git'
elif [ $kernelpartition = mmcblk0p6 ]; then
        GIT__URL='/media/sda1/service/partition3/git/etc.git'
elif [ $kernelpartition = mmcblk0p8 ]; then
        GIT__URL='/media/sda1/service/partition4/git/etc.git'
fi

GIT_EXIST=$(echo $GIT__URL"/HEAD")

if [ -e $GIT_EXIST ];then
        if [ ! -e /etc/gitconfig ];then
                git config --system user.name "GIT_USER"
                git config --system user.email "MAIL"
                git config --system core.editor "nano"
                git config --system http.sslverify false
        fi
        if [ "$(cd $GIT__URL && git log -1 --pretty=format:"%cd")" == "$(cd /etc && git log -1 --pretty=format:"%cd")" ];then
                break
        else
                systemctl stop neutrino.service
                echo "writing back /etc git remote from /dev/sda1"  > /dev/dbox/oled0
                cd /etc && etckeeper init
                git remote add origin $GIT__URL
                git fetch -a
                git reset --hard origin/master
                rm /etc/ssh/ssh_host*
                echo "...done"
                sync
                sleep 2
                echo "rebooting"
                systemctl reboot
        fi
else
        echo "no remote found"
fi




