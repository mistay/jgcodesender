jgcodesender
============


build
=====
$ git clone https://github.com/mistay/jgcodesender
Cloning into 'jgcodesender'...
remote: Counting objects: 39, done.
remote: Compressing objects: 100% (27/27), done.
remote: Total 39 (delta 8), reused 34 (delta 6)
Unpacking objects: 100% (39/39), done.
Checking connectivity... done.
armins-MacBook-Pro:foo arminlanghofer$ ls
jgcodesender

$ cd jgcodesender/
$ ./build.sh 1>/dev/null

running
=======
(within directory jgcodesender/bin)
$ java -jar jgcodesender.jar 
clean up lockfile: /var/lock/LK.021.018.004
install http://blog.iharder.net/2009/08/18/rxtx-java-6-and-librxtxserial-jnilib-on-intel-mac-os-x/ 
for macos: http://blog.iharder.net/wp-content/uploads/2009/08/librxtxSerial.jnilib
trying to connect to /dev/tty.usbmodem1411 with 115200bits/sec
Experimental:  JNI_OnLoad called.
Stable Library
=========================================
Native lib Version = RXTX-2.1-7
Java lib Version   = RXTX-2.1-7
trying to open port. make sure exits /var/lock (mkdir /var/lock) and chmod go+rwx /var/lock
