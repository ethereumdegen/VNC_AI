VNC_AI
======

A modified version of the Java TightVNC client code that uses a new folder called 'AI' to perform OpenCV image pattern analysis functions on the video feed, conduct middle-man intelligence on the recognized patterns, and then output keyboard and mouse commands using Java's [`Robot`](https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html) class.  This gives programmers a nice platform to research and expand upon visual pattern recognition, which is a key piece of artificial intelligence research moving forward.

This software is distributed under the GNU General Public Licence as published by the Free Software Foundation due to the packaged TightVNC client source code.

The license does not permit incorporating this software into proprietary programs.

##Setup

1. Download and install OpenCV 2.4.9 from [here](http://opencv.org/downloads.html) 
2. Add `OpenCV.jar` to your build path and point its natives folder to the x86 or x64 folder

>TLDR: I used [this tutorial](http://docs.opencv.org/master/doc/tutorials/introduction/java_eclipse/java_eclipse.html)

