#!/bin/bash

set -e

mkdir bin
javac src/*/*.java -d bin/
cd bin
jar cvmf ../manifest.txt jgcodesender.jar */*.class

exit 0
