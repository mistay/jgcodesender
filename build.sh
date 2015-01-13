#!/bin/bash

set -e

if [ -d bin ];
then
	rm -Rf bin
fi

mkdir bin

cd src
javac jgcodesender/Main.java -d ../bin/
cd ../bin
jar cvmf ../manifest.txt jgcodesender.jar */*.class

exit 0
