package com.glavsoft.ai;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;

import org.omg.CORBA.Environment;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;



public class Intel {

	Raster raster;
	byte[] pixels;
	int[] data;
	int mouseX;
	int mouseY;
	
	
	public Intel()
	{
		

		
	      
	     
	}
	
	
boolean exported = false;
int framecount = 0;
	public void setRaster(WritableRaster raster) {
		this.raster= raster;
		
		DataBufferInt buf = (DataBufferInt) raster.getDataBuffer();		
		data = buf.getData();
		pixels = new byte[data.length*3];
		
		mapPixelsOver(data,pixels);
		
		
		
		int height = raster.getHeight();
		int width = raster.getWidth();
		
		
       // for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
		
	
		 
		//800 by 600

		 System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	     // Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
	     // System.out.println( "mat = " + mat.dump() );
	      		
		//Java int is 32 bits signed, and there is only 1 channel here
	//	Mat m = new Mat(height, width, CvType.CV_32SC1);
		 
		//Mat gray = new Mat(height, width, CvType.CV_32SC(1));
		Mat m = new Mat(height, width, CvType.CV_8UC(3));
     
		//Imgproc.cvtColor(gray, m, Imgproc.COLOR_GRAY2RGBA, 4);
		
		
		
	     m.put(0, 0, pixels);
	     
	     
		//Core.rectangle(m, new Point(150,150), new Point(550,550), new Scalar(0,255,0)); //draw rect on image
		
		framecount++;
		if(!exported && framecount > 45)
		{
		 String path =   System.getProperty("user.home") + "/Desktop";
		Highgui.imwrite(path + "/opencvexport.png", m);
		exported = true;
		System.out.println("EXPORTED IMAGE");
		}
		 
		
		
		int index = (mouseY*width + mouseX);
		
			if(index <0) return;//prevents crashing when mouse is out of bounds
				
		 
		
		int rgb = pixels[index];
		
          /* int argb = 0;
           argb += -16777216; // 255 alpha
           argb += ((int) pixels[pixel] & 0xff); // blue
           argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
           argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
           */
          
           Color c = new Color(rgb);
        		   
        		   
           System.out.println(c);
           
           
           //result[row][col] = argb;
                      
          // col++;
          // if (col == width) {
           //   col = 0;
           //   row++;
           //}
       // }
						
		//Color.getColor(p);
	}


	 static void mapPixelsOver(int[] src, byte[] dest) {
			//ColorModel colorModel = new DirectColorModel(24, 0xff0000, 0xff00, 0xff);
		 
		for(int i=0;i<src.length;i++)
		{
			int byteIndex = i*3;
			int color = src[i];
			int redmask = 0xff0000;
			int greenmask = 0x00ff00;
			int bluemask = 0x0000ff;
		
			dest[byteIndex+2] = (byte) ((color & redmask)>>16);
			
			//only the red comes over! need to do a bitshift on these
			dest[byteIndex+1] = (byte) ((color & greenmask)>>8);
			dest[byteIndex+0] = (byte) ((color & bluemask));		
			
		
			
			
		}
		
	}


	public void setMousePos(int rX, int rY) {
		mouseX = rX;
		mouseY = rY;
		
	}

	
	
	
	
	
}
