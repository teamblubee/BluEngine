package com.blubee.bluengine.graphics;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.blubee.bluengine.R;
import com.blubee.bluengine.engine.World;
import com.blubee.bluengine.entitysystem.Component;
import com.blubee.bluengine.entitysystem.Mesh;
import com.blubee.bluengine.entitysystem.Position;
import com.blubee.bluengine.entitysystem.SpriteComponent;


public class MyRenderer extends GLRenderer {

	int sProgram, vShader, fShader;
	ArrayList<? extends Component> position;
	ArrayList<? extends Component> mesh;
	int posIt;
	int meshIt;
	Position mPos;
	Mesh mMesh;
	
	int posHandle, modMatHandle, viewMatHandle, projMatHandle, textureHandle, texCoordHandle, texId;
	float[] modMat, viewMat, projMat;
	
	TextureLoader tLoader;
	SpriteSheetLoader sLoader;
	SpriteSheet sSheet;
	Texture texture;
	
	
	ArrayList<? extends Component> pos;
	ArrayList<? extends Component> sprite;
	int posSize;
	int spriteSize;
	Position po;
	SpriteComponent sc;
	float xx;
	
	public MyRenderer(World world) {
		super(world);
		modMat = new float[16];
		viewMat = new float[16];
		projMat = new float[16];
		
		
		tLoader = new TextureLoader(world.context);
		sLoader = new SpriteSheetLoader(world.context);
	}

	@Override
	public void create() {
		
	}

	@Override
	public void surfaceCreated(GL10 gl, EGLConfig config) {
		final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = 3.0f;
        final float centerX = 0.0f;
        final float centerY = 0.0f;
        final float centerZ = 0.0f;
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;
        
        Matrix.setLookAtM(viewMat, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        
		vShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		GLES20.glShaderSource(vShader, vCode);
		GLES20.glCompileShader(vShader);

		fShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		GLES20.glShaderSource(fShader, fCode);
		GLES20.glCompileShader(fShader);

		sProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(sProgram, vShader);
		GLES20.glAttachShader(sProgram, fShader);
		GLES20.glLinkProgram(sProgram);

		posHandle = GLES20.glGetAttribLocation(sProgram, "aPos");
		texCoordHandle = GLES20.glGetAttribLocation(sProgram, "aTexPos");
		
		modMatHandle = GLES20.glGetUniformLocation(sProgram, "uModMat");
		viewMatHandle = GLES20.glGetUniformLocation(sProgram, "uViewMat");
		projMatHandle = GLES20.glGetUniformLocation(sProgram, "uProjMat");
		textureHandle = GLES20.glGetUniformLocation(sProgram, "texture");
		
		

		long start = System.currentTimeMillis();
		System.out.println("start loading");
		texture = tLoader.loadTexture(R.raw.wphd);
		sSheet = new SpriteSheet(world.context, texture, R.raw.wp_datahd);
		long end = System.currentTimeMillis();
		System.out.println("took "+(end-start)+" ms to load .....");
		
		world.createStuff(texture, sSheet);
		texId = texture.getId();
		
		this.world.begin();
	}

	@Override
	public void surfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
		Matrix.frustumM(projMat, 0, -width*0.5f, width*0.5f, -height*0.5f, height*0.5f, 1, 100);
		
		GLES20.glUseProgram(sProgram);
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texId);
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);		
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glCullFace(GLES20.GL_BACK);
	}

	@Override
	public void drawFrame(GL10 gl) {
		FPScounter.StartCounter(); 
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		
		 pos = world.man.getComponentOfType(Position.class);
		 sprite = world.man.getComponentOfType(SpriteComponent.class);
		 posSize = pos.size();
		 spriteSize = sprite.size();
		 for(int i = 0; i < posSize; i++)
		 {
			 assert posSize != spriteSize;
			 po = (Position)pos.get(i);
			 sc = (SpriteComponent)sprite.get(i);
			 xx = (float) (po.x * alpha + po.px * (1.0 - alpha));
			 Matrix.setIdentityM(modMat, 0);
			 Matrix.translateM(modMat, 0, xx, po.y, po.z);
			 //Matrix.translateM(modMat, 0, po.x, po.y, po.z);
			 
			 sc.vertBuffer.position(0);
			 GLES20.glVertexAttribPointer(posHandle, 3, GLES20.GL_FLOAT, false, 32, sc.vertBuffer);
	         GLES20.glEnableVertexAttribArray(posHandle);
	        
	         sc.vertBuffer.position(6);
	         GLES20.glVertexAttribPointer(texCoordHandle, 2, GLES20.GL_FLOAT, false, 32, sc.vertBuffer);
	         GLES20.glEnableVertexAttribArray(texCoordHandle);
	         
	         GLES20.glUniformMatrix4fv(modMatHandle, 1, false, modMat, 0);
	         GLES20.glUniformMatrix4fv(viewMatHandle, 1, false, viewMat, 0);
	         GLES20.glUniformMatrix4fv(projMatHandle, 1, false, projMat, 0);
		
	         GLES20.glUniform1i(textureHandle, 0);		 
	         GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, sc.indxBuffer);			 
		 
		 }
		 
		 //for(int i = 0; i < 3500; i++)
			 //GLES20.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
		 
		//GLES20.glDisable(GLES20.GL_CULL_FACE);
		//GLES20.glDisable(GLES20.GL_BLEND);
		//GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		 
		FPScounter.StopAndPost();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private final String vCode = 
			  "uniform mat4 uModMat;            \n"
			+ "uniform mat4 uViewMat;           \n"
			+ "uniform mat4 uProjMat;           \n"
			+ "attribute vec4 aPos;             \n"
			+ "attribute vec4 aCol;             \n"
			+ "attribute vec2 aTexPos;          \n"
			+ "varying vec2 vTexPos;            \n"
			+ "varying vec4 vCol;               \n"
			+ "void main(){                     \n"
			+ " vCol = aCol;                    \n"
			+ " mat4 mv = uViewMat * uModMat;   \n"
			+ " mat4 mvp = uProjMat * mv;       \n"
			//+ " gl_Position =  aPos;          \n"
			+ " vTexPos = aTexPos;                 \n"
			+ " gl_Position = mvp * aPos;       \n"
			+ " }                               \n";

	private final String fCode = 
			  "precision mediump float;        \n"
			+ "uniform sampler2D texture;      \n"
			+ "varying vec4 vCol;              \n"
			+ "varying vec2 vTexPos;           \n"
			+ "void main(){                    \n"
			//+ " gl_FragColor = vec4(1.0, 1.0, 0.0, 1.0);       \n"
			+ " gl_FragColor = texture2D(texture, vTexPos);  \n"
			+ " }                              \n";

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
	            Log.i("FPS", Long.toString(frames));  
	            //reset time differences and number of counted frames  
	            frames = 0;  
	            frameTimes = 0;  
	            startTime = endTime;
	        }  
	    }  
	}
}
