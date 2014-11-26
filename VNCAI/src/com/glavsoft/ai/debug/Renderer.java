package com.glavsoft.ai.debug;

public class Renderer {
	Renderer()
	{
		//draws a Mat feed in real time..
		
		 Mat image_tmp = your image

				    MatOfByte matOfByte = new MatOfByte();

				    Highgui.imencode(".jpg", image_tmp, matOfByte); 

				    byte[] byteArray = matOfByte.toArray();
				    BufferedImage bufImage = null;

				    try {

				        InputStream in = new ByteArrayInputStream(byteArray);
				        bufImage = ImageIO.read(in);
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				    
				    
				    
	}
}
