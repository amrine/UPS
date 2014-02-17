#! /bin/sh
if [ $# -ne 2 ]
	then
		echo "Usage : $0 Folder/File Adr">&2
		exit 1
	elif [ -d $1 ]
		then
			option="-r"
		else option=""
fi
if scp $option $1 $2\:\/media\/disk40 
	then
		echo "$0 : transfert reussi"
	else
		echo "$0 : transfert impossible"
fi
exit 0
