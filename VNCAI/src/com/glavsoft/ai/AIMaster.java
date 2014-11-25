package com.glavsoft.ai;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


/**
This class is the main brain of the AI.  It pulls in the raw video feed and converts it to a matrix-friendly format for OpenCV.

It can run OpenCV functions on the matrix image and then use the Java Robot class to send commands through the VNC connection as if it were a human.

Hopefully, middle-man code will be built in between the OpenCV pattern analysis and the keystrokes to make the AI more human-like.  


*/

public class AIMaster {

	
	int mouseX;
	int mouseY;
	
	Mat matrix;
	
	IntelWorker intel;
	
	public AIMaster()
	{
	
		
			intel = new IntelWorker();
			 new Thread(intel).start();
	}
	
	



	public void setRaster(WritableRaster raster) {
		
		matrix = Matricizer.generateMatrix(raster);
		
		intel.updateMatrix(matrix);
		
		
		
		 
	}






	public void setMousePos(int rX, int rY) {
		mouseX = rX;
		mouseY = rY;
		
	}


	
	
	
}
