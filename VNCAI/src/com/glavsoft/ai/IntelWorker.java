package com.glavsoft.ai;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.glavsoft.VisualComputing.VizionProc;
import com.glavsoft.ai.ideas.ColorBlob;
import com.glavsoft.ai.ideas.ColorBlobGroup;

public class IntelWorker implements Runnable{

	Mat matrices[] = new Mat[10]; //stores the last 10 frames of video feed for comparison 
	
	Robot outputrobot;
	
	boolean alive = true; //make this false if the AI acquires a mind of its own and starts taking over the world
	
	long lastMillis;
	float loopTimer = 0;
	final float LOOP_TIMER_MAX = 16;//the AI is throttled so it never goes faster than 60FPS
	
	
	
	
	
	
	@Override
	public void run()
	{
		lastMillis = System.currentTimeMillis();		
		
		while(alive)
		{
			float tpf = System.currentTimeMillis() - lastMillis;
			lastMillis = System.currentTimeMillis();	
			
			
			if(loopTimer >= LOOP_TIMER_MAX){
				
				update(tpf);
				loopTimer = 0;
			}else{
				loopTimer+=tpf;
			}
			
			
		}
	}


	private void update(float tpf) {
		
		System.out.println("updating AI" + tpf +"." + loopTimer);
		
		
		debuggingCode();
		
		
		
	}
	

boolean exported = false;
int framecount = 0;
	private void debuggingCode() {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		if(getCurrentMatrix() == null) return;
		
		
		
		//Mat output = Mat.zeros(getCurrentMatrix().rows(),getCurrentMatrix().cols(),  CvType.CV_8UC1);
		//Imgproc.blur(getCurrentMatrix(), output, new Size(3, 3) );
		//Imgproc.floodFill(getCurrentMatrix(),output, new Point(140,150), new Scalar(0,0,255));
		//Imgproc.floodFill(getCurrentMatrix(),output, new Point(140,150), new Scalar(255,255,255), new Rect(0,0,getCurrentMatrix().cols(),getCurrentMatrix().rows()), new Scalar(10,10,10), new Scalar(10,10,10), Imgproc.FLOODFILL_MASK_ONLY);
		
		
		Mat output = getCurrentMatrix().clone();
		
		Scalar tolerance = new Scalar(10,10,10);
		ColorBlobGroup myFirstBlobGroup = new ColorBlobGroup(getCurrentMatrix(),tolerance);
		
		for( ColorBlob blob : myFirstBlobGroup.getBlobs())
		{
			//draw rects based on the blob group
			if(blob!=null && blob.getBoundingRect()!=null)
			{
			Core.rectangle(output, new Point(blob.getBoundingRect().x,blob.getBoundingRect().y), new Point(blob.getBoundingRect().x+blob.getBoundingRect().width,blob.getBoundingRect().y+blob.getBoundingRect().height), new Scalar(0,255,0)); //draw rect on image
			}
		}
		
		
		
		
		//EXPORT IMAGE FOR DEBUGGING!
				framecount++;
				if(!exported && framecount > 4)
				{
				 String path =   System.getProperty("user.home") + "/Desktop";
				Highgui.imwrite(path + "/opencvexport.png", output);
				exported = true;
				System.out.println("EXPORTED IMAGE");
				}
		
	}

	
	
	
	private Mat getCurrentMatrix() {
		return matrices[0];
	}


	void testOutputs()
	{
		

		try {
			outputrobot = new Robot();
			
			outputrobot.keyPress(KeyEvent.VK_A);
			
			outputrobot.mouseMove(220, 220);    
			outputrobot.mousePress(InputEvent.BUTTON1_MASK);
			outputrobot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}


	public void updateMatrix(Mat matrix) {
				
		for(int i=matrices.length-1;i>0;i--)
		{
			matrices[i] =  matrices[i-1];//shift them all down (hopefully this works correctly!)	
		}
		matrices[0] = matrix; //put the newest one in at 'zero'
		
	}
}
