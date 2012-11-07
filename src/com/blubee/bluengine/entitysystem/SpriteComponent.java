package com.blubee.bluengine.entitysystem;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

import android.graphics.Color;

import com.blubee.bluengine.graphics.SpriteSheetRegion;
import com.blubee.bluengine.jniBuffers.JniBuff;



public class SpriteComponent extends Component{
	private int EID;
	public float[] vertices;
	public short[] indices;
	public FloatBuffer vertBuffer;
	public ShortBuffer indxBuffer;
	SpriteSheetRegion region;
	public float width, height, sMin, sMax, tMin, tMax, angle;
	public float xScale, yScale, xOrigin, yOrigin;
	public boolean rotated;
	public float angleVel;
	public Color color;
	public int posHandle, texHandle, texUnifHandle, stride;
	
	public SpriteComponent(int entity)
	{
		this.EID = entity;
		vertices = new float[32];
		indices = new short[6];
	}
	
	public void setSprite(SpriteSheetRegion spriteRegion)
	{
		setRegion(spriteRegion);
		//System.out.println("set sprite width "+this.width+" height "+this.height);
	}
	
	
	private void setRegion(SpriteSheetRegion region){
		stride = 32;
		if(region.isRoatated)
			this.angle = 90.0f;
		else
			this.angle = 0.0f;
		this.region = region;
		this.width =  region.width;
		this.height = region.height;
		this.sMax = region.sMax;
		this.sMin = region.sMin;
		this.tMax = region.tMax;
		this.tMin = region.tMin;
		this.rotated = region.isRoatated;
	
		vertices[0] = -width*0.5f;
		vertices[1] = -height*0.5f;
		vertices[2] = 0.0f;
		vertices[3] = 0;
		vertices[4] = 0;
		vertices[5] = 0;
		vertices[6] = sMin;
		vertices[7] = tMax;
		vertices[8] = width*0.5f;
		vertices[9] = -height*0.5f;
		vertices[10] = 0.0f;
		vertices[11] = 0;
		vertices[12] = 0;
		vertices[13] = 0;
		vertices[14] = sMax;
		vertices[15] = tMax;
		vertices[16] = -width*0.5f;
		vertices[17] = height*0.5f;
		vertices[18] = 0.0f;
		vertices[19] = 0;
		vertices[20] = 0;
		vertices[21] = 0;
		vertices[22] = sMin;
		vertices[23] = tMin;
		vertices[24] = width*0.5f;
		vertices[25] = height*0.5f;
		vertices[26] = 0.0f;
		vertices[27] = 0;
		vertices[28] = 0;
		vertices[29] = 0;
		vertices[30] = sMax;
		vertices[31] = tMin;
				
		indices[0] = 0;
		indices[1] = 1;
		indices[2] = 2;
		indices[3] = 2;
		indices[4] = 1;
		indices[5] = 3;
		
		/*
		vertBuffer = JniBuff.newUnsafeByteBuffer(vertices.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertBuffer.put(vertices).position(0);
		indxBuffer = JniBuff.newUnsafeByteBuffer(indices.length*2).order(ByteOrder.nativeOrder()).asShortBuffer();
		indxBuffer.put(indices).position(0);
		/*
		vertBuffer = ByteBuffer.allocateDirect(vertices.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertBuffer.put(vertices).position(0);
		indxBuffer = ByteBuffer.allocateDirect(indices.length*2).order(ByteOrder.nativeOrder()).asShortBuffer();
		indxBuffer.put(indices).position(0);
		//Log.d("Sprite component", "Sprite W&H scaled "+(width*0.5f)+" "+(height*0.5f)+" unscaled "+(width)+" "+(height));		
		*/
		vertBuffer = JniBuff.newFloatBuffer(vertices.length*4).put(vertices);
		vertBuffer.rewind();
		indxBuffer = JniBuff.newShortBuffer(indices.length*2).put(indices);
		indxBuffer.rewind();
	}
	
	@Override
	public String toString(){
		return "Sprite Component ( vertice "+vertices.length+" inices "+indices.length+" vb: "+vertBuffer+" ib: "+indxBuffer+" )";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EID;
		result = prime * result + Float.floatToIntBits(angle);
		result = prime * result + Float.floatToIntBits(angleVel);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + Arrays.hashCode(indices);
		result = prime * result
				+ ((indxBuffer == null) ? 0 : indxBuffer.hashCode());
		result = prime * result + posHandle;
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + (rotated ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(sMax);
		result = prime * result + Float.floatToIntBits(sMin);
		result = prime * result + stride;
		result = prime * result + Float.floatToIntBits(tMax);
		result = prime * result + Float.floatToIntBits(tMin);
		result = prime * result + texHandle;
		result = prime * result + texUnifHandle;
		result = prime * result
				+ ((vertBuffer == null) ? 0 : vertBuffer.hashCode());
		result = prime * result + Arrays.hashCode(vertices);
		result = prime * result + Float.floatToIntBits(width);
		result = prime * result + Float.floatToIntBits(xOrigin);
		result = prime * result + Float.floatToIntBits(xScale);
		result = prime * result + Float.floatToIntBits(yOrigin);
		result = prime * result + Float.floatToIntBits(yScale);
		return result;
	}
	
	public void disposeUnsafe()
	{
		System.out.println(JniBuff.getAllocatedBytesUnsafe());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpriteComponent other = (SpriteComponent) obj;
		if (EID != other.EID)
			return false;
		if (Float.floatToIntBits(angle) != Float.floatToIntBits(other.angle))
			return false;
		if (Float.floatToIntBits(angleVel) != Float
				.floatToIntBits(other.angleVel))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (!Arrays.equals(indices, other.indices))
			return false;
		if (indxBuffer == null) {
			if (other.indxBuffer != null)
				return false;
		} else if (!indxBuffer.equals(other.indxBuffer))
			return false;
		if (posHandle != other.posHandle)
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (rotated != other.rotated)
			return false;
		if (Float.floatToIntBits(sMax) != Float.floatToIntBits(other.sMax))
			return false;
		if (Float.floatToIntBits(sMin) != Float.floatToIntBits(other.sMin))
			return false;
		if (stride != other.stride)
			return false;
		if (Float.floatToIntBits(tMax) != Float.floatToIntBits(other.tMax))
			return false;
		if (Float.floatToIntBits(tMin) != Float.floatToIntBits(other.tMin))
			return false;
		if (texHandle != other.texHandle)
			return false;
		if (texUnifHandle != other.texUnifHandle)
			return false;
		if (vertBuffer == null) {
			if (other.vertBuffer != null)
				return false;
		} else if (!vertBuffer.equals(other.vertBuffer))
			return false;
		if (!Arrays.equals(vertices, other.vertices))
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		if (Float.floatToIntBits(xOrigin) != Float
				.floatToIntBits(other.xOrigin))
			return false;
		if (Float.floatToIntBits(xScale) != Float.floatToIntBits(other.xScale))
			return false;
		if (Float.floatToIntBits(yOrigin) != Float
				.floatToIntBits(other.yOrigin))
			return false;
		if (Float.floatToIntBits(yScale) != Float.floatToIntBits(other.yScale))
			return false;
		return true;
	}

	
}
