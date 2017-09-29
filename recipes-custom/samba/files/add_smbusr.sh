#! /bin/sh 

if pdbedit -L | grep root >> /dev/null;then
break
else
smbpasswd -n -a root
fi
