package com.blubee.bluengine.engine;

import android.os.Handler;
import android.util.Log;

public class Logic implements Runnable {

	private Thread mThread;
	private Handler mHandler;
	private boolean running = false;
	
	World world;
	
	double t;
	double dt;
	static final double wantedDt = 1.0/30.0;
	static final double factor = 1.0/1000.0;
	
    double currentTime;
    double accumulator = 0.0;
    double newTime, frameTime, alpha;
	
	public Logic(World w) {
		this.world = w;		
		mThread = new Thread(this, "Main loop Thread");
		mHandler = new Handler();
		mThread.start();
		running = true;
		
		t = 0.0;
		dt = 1.0/60.0;
		currentTime = System.currentTimeMillis();
	}
	
	public Logic(World w, double deltaTime)
	{
		this.world = w;		
		mThread = new Thread(this, "Main loop Thread");
		mHandler = new Handler();
		mThread.start();
		running = true;
		
		t = 0.0;
		dt = deltaTime;
		Log.v("Logic Constructor ", "setting delta time "+deltaTime);
		currentTime = System.currentTimeMillis();
	}

	
	public void run() {
		this.world.prepare();
		while (running) 
		{
				 newTime = System.currentTimeMillis();
		         frameTime = newTime - currentTime;
		         frameTime = frameTime * factor;
		         currentTime = newTime;
		         	if ( frameTime > wantedDt )
			         	{
		         		    Log.v("logic ", "frameTime "+frameTime+" is greater than max willing to wait time : "+wantedDt);
			              	frameTime = wantedDt;
			         	}
		         accumulator += frameTime;
		         
		         while ( accumulator >= dt )
		         {		              
		        	  update(dt);
		              accumulator -= dt;
		              t += dt;
		         }
		         alpha = accumulator/dt;
		         world.rend.alpha = alpha;
		 
		}
		while(!running)
		{
			//update(0.0);
			//System.out.println("not running ");
		}
	}

	
	public Handler getHandler()
	{
		return this.mHandler;
	}
	
	public synchronized void handleInput(float x, float y)
	{
		world.handleInput(x, y);
	}
	
	public void update(double dt)
	{
		world.update(dt);
	}

	public synchronized void pause() {
		if (!running)
			return;
		else
		{
			running = false;
			System.out.println("pause running is " + running);
		}
	}

	public synchronized void resume() {
		if (running)
			return;
		else
		{
			running = true;
			System.out.println("resume running is " + running);
		}
	}
	
	public synchronized void destroy(){
		if(running)
		{
			running = false;
			//mThread = null;
			System.out.println("destroy running is " + running);
		}
	}
}