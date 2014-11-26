package com.glavsoft.VisualComputing;



import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import com.glavsoft.ai.ideas.ColorBlob;
import com.glavsoft.ai.util.Colors;


/**
 * 
 * A set of visual processing algorithms to help an AI understand 3d objects in a 2d video feed
 * 
 * 
 * @author mazzolaa
 *
 */

public class VizionProc {


	public static ColorBlob identifyColorBlob(Mat src, Mat mask, Point start, Scalar threshold, int minPixels)
	{
		Mat dest = Mat.zeros(src.rows(), src.cols(), CvType.CV_8U);
				
		
		byte[] destFill = new byte[]{(byte) 255};
		int pixelCount = 0;
		
		List<Point> nodes = new ArrayList<Point>();
		
		nodes.add(start);
		
		byte[] startColor = new byte[3];				
		src.get((int) start.y, (int) start.x, startColor);
		
		
		
		byte[] colorOfN = new byte[3];
		byte[] colorOfDest = new byte[1];
		while(!nodes.isEmpty())
		{
			Point n = nodes.get(0);
			nodes.remove(n);
			
			if(n.x > 0 && n.x < src.cols() && n.y > 0 && n.y < src.rows()){//make sure it is within bounds
			
			
			src.get((int) n.y, (int) n.x, colorOfN);
			
			
			dest.get((int) n.y, (int) n.x, colorOfDest);
			
			if(Colors.colorsSimilar(startColor,colorOfN,threshold) && Colors.colorsDifferent(colorOfDest,destFill))
			{
				dest.put((int) n.y, (int) n.x, destFill); //draw on the output
				if(mask!=null)
				{
					mask.put((int) n.y, (int) n.x, destFill);  //draw on the mask
				}
				
				pixelCount++;
				
				//add adjacents to the node list
				
				nodes.add(new Point(n.x+1,n.y));
				nodes.add(new Point(n.x,n.y+1));
				nodes.add(new Point(n.x-1,n.y));
				nodes.add(new Point(n.x,n.y-1));
				
				
			}
			
			}
			
		}
		
		if(pixelCount > 100)
		{
			return new ColorBlob(dest, startColor, threshold, pixelCount);
		}
		
		return null;
	}
	
/**
 * 
 * Custom floodfill algorithm written to be easier to understand.  Outputs to a dest instead of overwriting the src
 * 
 * 
 * @author mazzolaa
 *
 */
	public static Mat floodFill(Mat src, Mat dest, Point start, Scalar threshold)
	{
		byte[] destFill = new byte[]{(byte) 255};
		int pixelCount = 0;
		
		List<Point> nodes = new ArrayList<Point>();
		
		nodes.add(start);
		
		byte[] startColor = new byte[3];				
		src.get((int) start.y, (int) start.x, startColor);
		
		while(!nodes.isEmpty())
		{
			Point n = nodes.get(0);
			nodes.remove(n);
			
			if(n.x > 0 && n.x < src.cols() && n.y > 0 && n.y < src.rows()){
			
			byte[] colorOfN = new byte[3];
			src.get((int) n.y, (int) n.x, colorOfN);
			
			byte[] colorOfDest = new byte[1];
			dest.get((int) n.y, (int) n.x, colorOfDest);
			
			if(Colors.colorsSimilar(startColor,colorOfN,threshold) && Colors.colorsDifferent(colorOfDest,destFill))
			{
				dest.put((int) n.y, (int) n.x, destFill); //draw on the mask
				pixelCount++;
				
				//add adjacents to the node list
				
				nodes.add(new Point(n.x+1,n.y));
				nodes.add(new Point(n.x,n.y+1));
				nodes.add(new Point(n.x-1,n.y));
				nodes.add(new Point(n.x,n.y-1));
				
				
			}
			
			}
			
		}
		
		
		return dest;
		
	}
	


	
	
	
	
}
