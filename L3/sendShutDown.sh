#! /bin/sh
if .\/sendToMyServe.sh $1 $2
	then
		echo "Envoi reussi"
		shutdown -h now
fi

