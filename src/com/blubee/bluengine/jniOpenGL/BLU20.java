package com.blubee.bluengine.jniOpenGL;

public class BLU20 {
 
	static
	{
		System.loadLibrary("BLU");
	}
	
	native public static void glDrawElements(int mode, int count, int type,	int offset);
	native public static void glVertexAttribPointer(int indx, int size,	int type, boolean normalized, int stride, int offset);
}
