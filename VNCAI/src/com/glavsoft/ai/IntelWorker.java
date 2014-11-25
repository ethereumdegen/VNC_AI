package com.glavsoft.ai;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class IntelWorker implements Runnable{

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
						
			if(loopTimer >= LOOP_TIMER_MAX){
				
				update(tpf);
				loopTimer = 0;
			}else{
				loopTimer+=tpf;
			}
			
			lastMillis = System.currentTimeMillis();
		}
	}


	private void update(float tpf) {
		
		System.out.println("updating AI" + tpf +"." + loopTimer);
		
		
		
		
		
		
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
}
