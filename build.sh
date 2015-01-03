mkdir bin
javac src/*/*.java -d bin/
cd bin
jar cvmf ../manifest.txt jgcodesender.jar */*.class
