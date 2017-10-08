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
DEST=$(echo $GIT__URL | cut -d"/" -f1,2,3)

if [ -e $GIT_EXIST ];then
        exit
elif mountpoint -q $DEST;then
        cd /etc
        if [ ! -e /etc/gitconfig ];then
        git config --system user.name "GIT_USER"
        git config --system user.email "MAIL"
        git config --system core.editor "nano"
        git config --system http.sslverify false
        fi
        echo "creating /etc remote"
        etckeeper init
        mkdir -p $GIT__URL
        git init --bare $GIT__URL
        cd /etc && git remote add -f origin $GIT__URL
        git commit -m "initial commit"
        git push origin master
else
        echo "no mounted media found"
fi
