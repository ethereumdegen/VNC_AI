package com.glavsoft.ai.ideas;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ColorBlob extends Entity{

	
	byte[] color = new byte[3];
	long pixelCount = 0;
	Rect boundingRect;
	RotatedRect boundingEllipse;
	
	public ColorBlob(Mat sourceMat, byte[] startColor, Scalar threshold,int pixelCount) {
		color = startColor;
		this.pixelCount = pixelCount;  //ignore me if this is too low! insignificant blob..
		
		Mat erodedMat = new Mat();
		
		
		  //convert the image to black and white does (8 bit), commenting this crashes
        Imgproc.Canny(sourceMat, erodedMat, 50, 50);

        //apply gaussian blur to smoothen lines of dots, commenting this crashes
        Imgproc.GaussianBlur(erodedMat, erodedMat, new Size(5, 5), 5);

        //find the contours
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(erodedMat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        MatOfPoint allcontours = new MatOfPoint();
               
        for(MatOfPoint mat : contours)
        {
        	mat.copyTo(allcontours);//take all of the contours and put them into one giant map
        }
        
        if( allcontours.toArray().length > 4)
        {
        MatOfPoint new_mat1 = new MatOfPoint( allcontours.toArray() );
        MatOfPoint2f new_mat2 = new MatOfPoint2f( allcontours.toArray() );
		
		boundingRect = Imgproc.boundingRect( new_mat1 );
		boundingEllipse = Imgproc.fitEllipse( new_mat2 );
        }
	}

	public Rect getBoundingRect() {
		return boundingRect;
	}

	public long getPixelCount() {
		return pixelCount;
	}


	
	
	
	
	
	
	
}
