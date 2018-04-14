#! /bin/sh 

if pdbedit -L | grep root >> /dev/null;then
	exit
else
	smbpasswd -n -a root
fi
