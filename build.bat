echo "warning, this is untested"
mkdir bin
javac src\*i\*.java -d bin\
cd bin
jar cvmf ..\manifest.txt jgcodesender.jar *\*.class
