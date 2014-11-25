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
import org.opencv.highgui.Highgui;

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
		
		
		debuggingCode();
		
		 
	}



boolean exported = false;
int framecount = 0;
	private void debuggingCode() {
		//double[] color = matrix.get(mouseY,mouseX);
		// System.out.println(color);
		
		Core.rectangle(matrix, new Point(150,150), new Point(550,550), new Scalar(0,255,0)); //draw rect on image
		
		
		
		//EXPORT IMAGE FOR DEBUGGING!
				framecount++;
				if(!exported && framecount > 45)
				{
				 String path =   System.getProperty("user.home") + "/Desktop";
				Highgui.imwrite(path + "/opencvexport.png", matrix);
				exported = true;
				System.out.println("EXPORTED IMAGE");
				}
		
	}




	public void setMousePos(int rX, int rY) {
		mouseX = rX;
		mouseY = rY;
		
	}


	
	
	
}
