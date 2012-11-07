package com.blubee.bluengine.graphics;

import android.opengl.GLES20;

public class Texture {
	
	int width, height, id;
	TextureFilter minFilter;
	TextureFilter magFilter;
	TextureWrap sWrap;
	TextureWrap tWrap;
	
	public Texture()
	{
		width = height = 0;
		id = -1;
	}
	
	public Texture(Texture t)
	{
		this.id = t.id;
		this.width = t.width;
		this.height = t.height;
		this.magFilter = t.magFilter;
		this.minFilter = t.minFilter;
		this.sWrap = t.sWrap;
		this.tWrap = t.tWrap;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public float getWidth(){
		return this.width;
	}
	
	public float getHeight(){
		return this.height;
	}
	
	public TextureFilter getMinFilter(){
		return this.minFilter;
	}
	
	public TextureFilter getMagFilter(){
		return this.magFilter;
	}
	
	public TextureWrap getSwrap(){
		return this.sWrap;
	}
	
	public TextureWrap getTwrap(){
		return this.tWrap;
	}
	
	public enum TextureWrap {
		ClampToEdge(GLES20.GL_CLAMP_TO_EDGE), Repeat(GLES20.GL_REPEAT);

		final int glEnum;

		TextureWrap (int glEnum) {
			this.glEnum = glEnum;
		}

		public int getGLEnum () {
			return glEnum;
		}
	}
	
	public enum TextureFilter {
		Nearest(GLES20.GL_NEAREST), Linear(GLES20.GL_LINEAR), MipMap(GLES20.GL_LINEAR_MIPMAP_LINEAR), MipMapNearestNearest(
			GLES20.GL_NEAREST_MIPMAP_NEAREST), MipMapLinearNearest(GLES20.GL_LINEAR_MIPMAP_NEAREST), MipMapNearestLinear(
			GLES20.GL_NEAREST_MIPMAP_LINEAR), MipMapLinearLinear(GLES20.GL_LINEAR_MIPMAP_LINEAR);
		final int glEnum;

		TextureFilter (int glEnum) {
			this.glEnum = glEnum;
		}

		public boolean isMipMap () {
			return glEnum != GLES20.GL_NEAREST && glEnum != GLES20.GL_LINEAR;
		}

		public int getGLEnum () {
			return glEnum;
		}
	}
	
	public String toString(){
		return "texture ( id "+id+" w: "+width+" h: "+height+" s: "+sWrap+", t: "+tWrap+" min: "+minFilter+", mag: "+magFilter+" )";
	}
}
