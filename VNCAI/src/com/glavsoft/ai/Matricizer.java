package com.glavsoft.ai;

import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class Matricizer {

	public static Mat generateMatrix(WritableRaster raster) {

		
		byte[] pixels;
		int[] data;
		
		
		DataBufferInt buf = (DataBufferInt) raster.getDataBuffer();		
		data = buf.getData();
		pixels = new byte[data.length*3];
		
		mapPixelsOver(data,pixels);//put data into nice bytes
				
		int height = raster.getHeight();
		int width = raster.getWidth();
		

		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	   
		Mat m = new Mat(height, width, CvType.CV_8UC(3));
        m.put(0, 0, pixels);//turn bytes into a matrix for OpenCV functions
	     
	     
		Core.rectangle(m, new Point(150,150), new Point(550,550), new Scalar(0,255,0)); //draw rect on image
		
	
		
		
		return m;
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
			dest[byteIndex+1] = (byte) ((color & greenmask)>>8);
			dest[byteIndex+0] = (byte) ((color & bluemask));	
		}		
	}
	 
}
