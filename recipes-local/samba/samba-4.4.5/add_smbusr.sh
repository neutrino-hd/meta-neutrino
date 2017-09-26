#! /bin/sh 

if pdbedit -L | grep root >> /dev/null;then
break
else
smbpasswd -a -n root
fi

