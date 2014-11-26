package com.glavsoft.ai.ideas;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import com.glavsoft.VisualComputing.VizionProc;
import com.glavsoft.ai.util.Colors;

public class ColorBlobGroup {

	List<ColorBlob> blobs = new ArrayList<ColorBlob>();
	Mat src;
	//Mat mask;//points already looked at
	Scalar threshold;//for the 'magic wand'
	
	public ColorBlobGroup(Mat src, Scalar threshold) {
		this.src=src;
		this.threshold=threshold;
		
		
		identifyAllLargeBlobs();
			
		
	}

	List<Point> pointsToCheck = new ArrayList<Point>();
	
	static final int BLOB_IDENTIFICATION_FIDELITY = 4;
	
	private void identifyAllLargeBlobs() {
		
		Mat mask = Mat.zeros(src.rows(), src.cols(), CvType.CV_8U) ;
		
		
		for(int x=1;x<src.cols()-1;x+=BLOB_IDENTIFICATION_FIDELITY)
		{
			for(int y=1;y<src.rows()-1;y+=BLOB_IDENTIFICATION_FIDELITY)
			{
			
				
				
				byte[] maskColor = new byte[1];
				mask.get( y, x,maskColor);
				
				final int minPixels = 1000;
				
				if(Colors.colorsSame(maskColor, new byte[]{0}) ){
					ColorBlob newblob = VizionProc.identifyColorBlob(src, mask,  new Point(x,y), threshold, minPixels) ;
					if(newblob !=null ){
						blobs.add(newblob);
					
					}
				}
				
				
			}			
		}
		
		
		
			
		
		/*
		
		blobs.add( VizionProc.identifyColorBlob(src, null, new Point(1,1), threshold,100)     );
		blobs.add( VizionProc.identifyColorBlob(src, null, new Point(240,140), threshold,100)     );
		blobs.add( VizionProc.identifyColorBlob(src, null, new Point(140,240), threshold,100)     );
		
		blobs.add( VizionProc.identifyColorBlob(src, null, new Point(340,140), threshold,100)     );
		blobs.add( VizionProc.identifyColorBlob(src, null, new Point(140,340), threshold,100)     );
		*/
		
	}

	private boolean colorsAreSame(double[] ds, double[] ds2) {
		
		return false;
	}

	Scalar toleranceUsed;


	public List<ColorBlob> getBlobs() {
		return blobs;
	}
	
	
}
