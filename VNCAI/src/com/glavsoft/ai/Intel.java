package com.glavsoft.ai;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class Intel {

	Raster raster;
	int[] pixels;
	int mouseX;
	int mouseY;

	public void setRaster(WritableRaster raster) {
		this.raster= raster;
		
		DataBufferInt buf = (DataBufferInt) raster.getDataBuffer();		
		pixels = buf.getData();
		
		
		int height = raster.getHeight();
		int width = raster.getWidth();
		
		 int[][] result = new int[height][width];
		
       // for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
		
		//pixel index
		 
		
		 
		int index = (mouseY*width + mouseX);
		
			if(index <0)
				return;
		 
		
		int argb = pixels[index];
		
          /* int argb = 0;
           argb += -16777216; // 255 alpha
           argb += ((int) pixels[pixel] & 0xff); // blue
           argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
           argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
           */
          
           Color c = new Color(argb);
        		   
        		   
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


	public void setMousePos(int rX, int rY) {
		mouseX = rX;
		mouseY = rY;
		
	}

	
	
	
	
	
}
