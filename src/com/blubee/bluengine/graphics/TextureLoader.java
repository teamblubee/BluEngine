package com.blubee.bluengine.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.blubee.bluengine.graphics.Texture.TextureFilter;
import com.blubee.bluengine.graphics.Texture.TextureWrap;


public class TextureLoader {
	
	 private Context context;
	 //private Bitmap bitmap;
	 private Bitmap tmpBitmap;
	 private int[] tmp;
	 private Texture texture;
	 
	 public TextureLoader(Context context)
	 {
		 this.context = context;
	 }
	 
	 public Texture loadTexture(int id)
	 {
		 
		 Log.v("texture loader", "loading asset id "+id);
		 tmp = new int[1];
		 texture = new Texture();
		 Matrix flip = new Matrix();
		 flip.postScale(1, 1);
		 BitmapFactory.Options options = new BitmapFactory.Options();
		 options.inScaled = false;
		 
		 tmpBitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
		 //bitmap = Bitmap.createBitmap(tmpBitmap, 0, 0, tmpBitmap.getWidth(), tmpBitmap.getHeight(), flip, true);
		 
		 //tmpBitmap.recycle();
		 
		 
		 GLES20.glGenTextures(1, tmp, 0);
		 GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tmp[0]);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		 GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, tmpBitmap, 0);
		 GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
		 
		 texture.width = tmpBitmap.getWidth();
		 texture.height = tmpBitmap.getHeight();
		 texture.magFilter = TextureFilter.Nearest;
		 texture.minFilter = TextureFilter.Nearest;
		 texture.sWrap = TextureWrap.ClampToEdge;
		 texture.tWrap = TextureWrap.ClampToEdge;
		 texture.id = tmp[0];
		 
		 //bitmap.recycle();
		 tmpBitmap.recycle();
		 GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		 
		 Log.v("texture loader", "returning texture id "+tmp[0]);
		 return texture;
	 }
}

/*
 * public Bitmap loadBitmap(int id)
	 {
		 Log.v("texture loader", "loading bitmap asset id "+id);
		 tmp = new int[1];
		 texture = new Texture();
		 Matrix flip = new Matrix();
		 flip.postScale(1, -1);
		 BitmapFactory.Options options = new BitmapFactory.Options();
		 options.inScaled = false;
		 
		 tmpBitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
		 
		 //tmpBitmap.recycle();
		 
		 return Bitmap.createBitmap(tmpBitmap, 0, 0, tmpBitmap.getWidth(), tmpBitmap.getHeight(), flip, true);
	 }
	 
	 public Texture loadTexture(Bitmap bitmap)
	 {
		 this.bitmap = bitmap;
		 texture = new Texture();
		 
		 GLES20.glGenTextures(1, tmp, 0);
		 GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tmp[0]);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		 GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		 GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, bitmap, 0);
		 GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
		 
		 texture.width = bitmap.getWidth();
		 texture.height = bitmap.getHeight();
		 texture.magFilter = TextureFilter.Nearest;
		 texture.minFilter = TextureFilter.Nearest;
		 texture.sWrap = TextureWrap.ClampToEdge;
		 texture.tWrap = TextureWrap.ClampToEdge;
		 texture.id = tmp[0];
		 
		 //bitmap.recycle();
		 GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		 
		 Log.v("texture loader", "returning texture id "+tmp[0]);
		 
		 return texture;
	 }
 */
