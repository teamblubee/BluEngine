package com.blubee.bluengine;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.blubee.bluengine.engine.World;

public class MainActivity extends Activity {
	World world;
	Handler logicHandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        world = new World(this.getApplicationContext());
		logicHandler = world.getLogicHandler();
		setContentView(world.mView);
	}
	

	@Override
	public boolean onTouchEvent(final MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_MOVE) 
		{
			logicHandler.post(new Runnable()
			{
            	public void run()
            	{
            		//logic.handleInput(e.getX(), e.getY());
            		//world.handleInput(e.getX(), e.getY());
            		
            	}
            });
		}
		return false;
	}

	@Override
	public void onPause() {
		super.onPause();
		world.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		world.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		world.onDestroy();
	}

}
