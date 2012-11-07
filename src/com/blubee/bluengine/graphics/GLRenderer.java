package com.blubee.bluengine.graphics;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

import com.blubee.bluengine.engine.World;

public abstract class GLRenderer implements GLSurfaceView.Renderer{

	public World world;
	public double alpha;
	
	public GLRenderer(World world)
	{
		this.world = world;
		create();
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		surfaceCreated(gl, config);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		surfaceChanged(gl, width, height);
		
	}

	public void onDrawFrame(GL10 gl) 
	{
		drawFrame(gl);
	}

	public abstract void create();
	public abstract void surfaceCreated(GL10 gl, EGLConfig config);
	public abstract void surfaceChanged(GL10 gl, int width, int height);
	public abstract void drawFrame(GL10 gl);
	public abstract void pause();
	public abstract void resume();
	public abstract void destroy();
}
