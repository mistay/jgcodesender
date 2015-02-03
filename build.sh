#!/bin/bash

set -e

WORKINGDIR=$1
if [ "$WORKINGDIR" == "" ];
then
	WORKINGDIR="."
fi
echo "WORKINGDIR: $WORKINGDIR"
cd $WORKINGDIR

if [ -d bin ];
then
	rm -Rf bin
fi

mkdir bin

cd src
javac main/Main.java -d ../bin/

cd ../bin
jar cvmf ../manifest.txt jgcodesender.jar */*.class */*/*.class

if [ -f "jgcodesender.jar" ];
then
	echo "successfully built $WORKINGDIR/bin/jgcodesender.jar"
fi

exit 0
