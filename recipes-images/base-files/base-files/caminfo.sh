#!/bin/sh

echo "$$" >/tmp/caminfo.pid

#Server-Adresse
URL="127.0.0.1"
#Port Web-IF
PORT="8080"
USER=""
PASS=""
WUP=""
[ "$USER" != "" ] && WUP="$USER:$PASS@"

#Augabedatei
OUT="/tmp/infobar.txt"
#alle x sekunden wird aktualisiert
PAUSE="4"
## ermitteln der Image-Version/Datum
FILE=$(cat /.version)
NAME=`echo "$FILE" | grep imagename`
N1=`echo ${NAME:10:14}`
VERSION=`echo "$FILE" | grep version`
V1=`echo ${VERSION:9:1}`
V2=`echo ${VERSION:10:2}`
BUILD=`echo "$FILE" | grep builddate`
B1=`echo ${BUILD:14:6}`
FILE1=$(cat /boot/startup)
bank=`echo ${FILE1:22:1}`
#touch /tmp/lcd/temperatures

while [ -e /var/.caminfo ]

do

        if [ -e /tmp/ecm.info ] ; then
          if [ -e /var/etc/.gbox ]  ; then
            #
            #---------- /tmp/ecm.info kopieren und auslesen nach caid, from und ecm time ----------
            #---------- /getonidsid auslesen nach sid ----------
            #
            cp -f /tmp/ecm.info /tmp/ecm.copy 
            SID=$(wget -q -O - http://127.0.0.1/control/getonidsid)
            START=$(((${#SID})-5))
            S=`echo ${SID:${START}:4}`
            CA=`grep CaID /tmp/ecm.copy`
            L=`echo $CA | cut -d' '  -f6`
            LA=`echo ${L:2:4}`
            PR=`grep prov /tmp/ecm.copy`
            P=`echo ${PR:5:10}`
            RE=`grep decode /tmp/ecm.copy`
            R=`echo ${RE:7:15}`
            TI=`grep response /tmp/ecm.copy`
            N=`echo ${TI:9:5}`
            echo "| Caid: "$LA" | Sid: "$S" | "$R" | "$N" ms |" > $OUT
            rm -f /tmp/ecm.copy
          fi
          if [ -e /var/.doscam ] || [ -e /var/.oscam ] || [ -e /var/.ncam ] ; then
            #
            #---------- /tmp/ecm.info kopieren und auslesen nach caid, from und ecm time ----------
            #---------- /getonidsid auslesen nach sid ----------
            #
            cp -f /tmp/ecm.info /tmp/ecm.copy 
            SID=$(wget -q -O - http://127.0.0.1/control/getonidsid)
            START=$(((${#SID})-5))
            S=`echo ${SID:${START}:4}`
            CA=`grep caid /tmp/ecm.copy`
            L=`echo ${CA:8:5}`
            PR=`grep prov /tmp/ecm.copy`
            P=`echo ${PR:8:6}`
            RE=`grep reader /tmp/ecm.copy`
            R=`echo ${RE:8:15}`
            TI=`grep ecm /tmp/ecm.copy`
            N=`echo ${TI:10:5}`
            echo "| Caid: "$L" | Prov: "$P" | Sid: "$S" | "$R" | "$N"s |" > $OUT
            rm -f /tmp/ecm.copy
          fi
        else
            echo "|  "AX/Mut@nt HD51 Bank" "$bank" |  Datum: "$(date +"%d.%m.%Y")"  |" > $OUT
        fi
        sleep $PAUSE

done

rm -f $OUT

exit
