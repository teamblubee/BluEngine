package com.blubee.bluengine.engine;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.Log;

import com.blubee.bluengine.entitysystem.Component;
import com.blubee.bluengine.entitysystem.EntityManager;
import com.blubee.bluengine.entitysystem.Mesh;
import com.blubee.bluengine.entitysystem.Position;
import com.blubee.bluengine.entitysystem.SpriteComponent;
import com.blubee.bluengine.graphics.MyRenderer;
import com.blubee.bluengine.graphics.SpriteSheet;
import com.blubee.bluengine.graphics.Texture;
import com.blubee.bluengine.jniBuffers.JniBuff;



public class World {
	
	public Context context;
	public GLSurfaceView mView;
	Logic logic;
	MyRenderer rend;
	
	//entities
	public EntityManager manager;
	int lama;
	int[] la;
	//!entities
	
	//graphics
	public int program, mModMat, mViewMat, mProjMat, texHandle, colHandle, normHandle;
	public Texture t;
	//!graphics	
	
	int[] ent;
	Random rand = new Random();
	Position p;
	Mesh m;
	float alpha = 350f; 
	int len;
	int len1;
	public EntityManager man;
	int max, min;
	
	ArrayList<? extends Component> al; 
	
	public World(Context context)
	{
		logic = new Logic(this);
		this.context = context;
		mView = new GLSurfaceView(this.context);
		rend = new MyRenderer(this);
		mView.setEGLContextClientVersion(2);
		mView.setRenderer(rend);
		manager = new EntityManager();
		ent = new int[620];
	}
	
	public void createStuff(Texture texture, SpriteSheet sSheet) {
		if(man == null)
		{
			man = new EntityManager();
		} 
		min = 100;
		max = 320;
		ent[0] = man.createEntity();
		p = new Position(0, 0, 0);
		//p.beta = alpha;
		p.beta = min + (int)(Math.random() * ((max - min) + 1));
		man.addComponent(ent[0], p);
		SpriteComponent s = new SpriteComponent(ent[0]);
		s.setSprite(sSheet.getSprite("sun.png"));
		man.addComponent(ent[0], s);
		
		
		ent[2] = man.createEntity();
		p = new Position(0, -500, 0);
		//p.beta = alpha;
		p.beta = min + (int)(Math.random() * ((max - min) + 1));
		man.addComponent(ent[2], p);
		s = new SpriteComponent(ent[1]);
		s.setSprite(sSheet.getSprite("fuji.png"));
		man.addComponent(ent[2], s);
		
		for(int i = 3; i < 60; i++)
		{
			ent[i] = man.createEntity();
			s = new SpriteComponent(ent[i]);
			s.setSprite(sSheet.getSprite("cloud4.png"));
			man.addComponent(ent[i], s);
			p = new Position(0, (-800+(i*140)), 0);
			//p.beta = alpha;
			p.beta = min + (int)(Math.random() * ((max - min) + 1));
			man.addComponent(ent[i], p);			
		}
		
		ByteBuffer bb = JniBuff.newUnsafeByteBuffer(1024);
		System.out.println("total unmanaged memory "+JniBuff.getAllocatedBytesUnsafe());
		JniBuff.disposeUnsafeByteBuffer(bb);
	}
	
	public void update(double delta)
	{ 
		//update all component managers
		//FPScounter.StartCounter();
		test(delta);
		//FPScounter.StopAndPost();
		//rend.pos = man.getComponentOfType(Position.class);
		//rend.sprite = man.getComponentOfType(SpriteComponent.class);
	}
	
	public void test(double delta)
	{
		al = man.getComponentOfType(Position.class);
		len = al.size();
		for(int i = 0; i < len; i++)
		{
			p = (Position)al.get(i);
			p.px = p.x;
			p.py = p.y;
			p.pz = p.z;
			p.x += (p.beta * delta);
			if(p.x > 480) 
				p.beta = -p.beta;
			else
			if(p.x <= -480)
				p.beta = -p.beta;
			
		}
	}
	
	
	public synchronized void prepare()
	{
		try 
		{
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void begin()
	{
		this.notifyAll();
	}
	
	public synchronized void handleInput(float x, float y)
	{
		System.out.println("updating input x: "+x+", y "+y);
	}
	
	public void onPause()
	{
		logic.pause();
		mView.onPause();
	}
	
	public void onResume()
	{
		logic.resume();
		mView.onResume();
	}
	
	public void onDestroy()
	{
		List l = manager.getComponentOfType(SpriteComponent.class);
		if(l == null)
			return;
		int size = l.size();
		for(int i = 0; i < size; i++)
		{
			SpriteComponent sc = (SpriteComponent)l.get(i);
			sc.disposeUnsafe();
		}
		
		logic.destroy();
	}
	
	public Handler getLogicHandler()
	{
		return logic.getHandler();
	}
	
	public final static class FPScounter  
	{  
	    private static int startTime;  
	    private static int endTime;  
	    private static int frameTimes = 0;  
	    private static short frames = 0;  
	  
	    /** Start counting the fps**/  
	    public final static void StartCounter()  
	    {  
	        //get the current time  
	        startTime = (int) System.currentTimeMillis();  
	    }  
	  
	    /**stop counting the fps and display it at the console*/  
	    public final static void StopAndPost()  
	    {  
	        //get the current time  
	        endTime = (int) System.currentTimeMillis();  
	        //the difference between start and end times  
	        frameTimes = frameTimes + endTime - startTime;  
	        //count one frame  
	        ++frames;  
	        //if the difference is greater than 1 second (or 1000ms) post the results  
	        if(frameTimes >= 1000)  
	        {  
	            //post results at the console  
	            Log.i("RPS", Long.toString(frames));  
	            //reset time differences and number of counted frames  
	            frames = 0;  
	            frameTimes = 0;  
	            startTime = endTime;
	        }  
	    }  
	}
}