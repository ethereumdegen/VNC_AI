package com.glavsoft.ai.util;

import org.opencv.core.Scalar;

public class Colors {

	public static boolean colorsDifferent(byte[] c1, byte[] c2) {
		for(int i=0;i<c1.length;i++){
			if(c1[i] != c2[i])
			{
				return true;
			}
		}
		return false;
	}

	public static boolean colorsSimilar(byte[] c1, byte[] c2, Scalar threshold) {
		for(int i=0;i<c1.length;i++){
			if(Math.abs(c1[i] - c2[i]) > threshold.val[i])
			{
				return false;
			}
		}
		return true;
	}

	public static boolean colorsSame(byte[] c1, byte[] c2) {
		return !colorsDifferent(c1,c2);
	}
	
	
	
	
}
