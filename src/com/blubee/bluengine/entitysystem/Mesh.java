package com.blubee.bluengine.entitysystem;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class Mesh extends Component{

	public FloatBuffer mVertsBuffer, mColBuffer, mNormBuffer, mTextBuffer;
	public FloatBuffer mVertsColNormBuffer;
	public ShortBuffer mIndicesBuffer;
	public float[] mVerts, mCol, mNorm, mText;
	public short[] mIndices;
	public float width, height, xTrans, yTrans, zTrans, xRot, yRot, zRot, angle;
	public int mTextureId, numIndices, stride;	
	public boolean textureSet;
	
	public Mesh()
	{

	}
	
	public Mesh(float[] v)
	{
		setVertices(v);
		mVertsBuffer = ByteBuffer.allocateDirect(mVerts.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		mVertsBuffer.put(mVerts).position(0);
	}
	
	public void setVertices(float[] verts)
	{
		int len = verts.length;
		mVerts = new float[len];
		for(int i = 0; i < len; i++)
		{
			mVerts[i] = verts[i];
		}
	}
	
	
	

	@Override
	public String toString()
	{
		//return "Mesh ( verts count "+mVerts.length+", texId "+mTextureId+", numIndices "+numIndices+" )";
		return "Mesh ( texId "+mTextureId+" mVerts size "+mVertsBuffer+" , numIndices "+numIndices/*+" x, y, z rot: "+xRot+" "+yRot+" "+zRot+" )"*/;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(angle);
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + Arrays.hashCode(mCol);
		result = prime * result
				+ ((mColBuffer == null) ? 0 : mColBuffer.hashCode());
		result = prime * result + Arrays.hashCode(mIndices);
		result = prime * result
				+ ((mIndicesBuffer == null) ? 0 : mIndicesBuffer.hashCode());
		result = prime * result + Arrays.hashCode(mNorm);
		result = prime * result
				+ ((mNormBuffer == null) ? 0 : mNormBuffer.hashCode());
		result = prime * result + Arrays.hashCode(mText);
		result = prime * result
				+ ((mTextBuffer == null) ? 0 : mTextBuffer.hashCode());
		result = prime * result + mTextureId;
		result = prime * result + Arrays.hashCode(mVerts);
		result = prime * result
				+ ((mVertsBuffer == null) ? 0 : mVertsBuffer.hashCode());
		result = prime
				* result
				+ ((mVertsColNormBuffer == null) ? 0 : mVertsColNormBuffer
						.hashCode());
		result = prime * result + numIndices;
		result = prime * result + stride;
		result = prime * result + (textureSet ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(width);
		result = prime * result + Float.floatToIntBits(xRot);
		result = prime * result + Float.floatToIntBits(xTrans);
		result = prime * result + Float.floatToIntBits(yRot);
		result = prime * result + Float.floatToIntBits(yTrans);
		result = prime * result + Float.floatToIntBits(zRot);
		result = prime * result + Float.floatToIntBits(zTrans);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesh other = (Mesh) obj;
		if (Float.floatToIntBits(angle) != Float.floatToIntBits(other.angle))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (!Arrays.equals(mCol, other.mCol))
			return false;
		if (mColBuffer == null) {
			if (other.mColBuffer != null)
				return false;
		} else if (!mColBuffer.equals(other.mColBuffer))
			return false;
		if (!Arrays.equals(mIndices, other.mIndices))
			return false;
		if (mIndicesBuffer == null) {
			if (other.mIndicesBuffer != null)
				return false;
		} else if (!mIndicesBuffer.equals(other.mIndicesBuffer))
			return false;
		if (!Arrays.equals(mNorm, other.mNorm))
			return false;
		if (mNormBuffer == null) {
			if (other.mNormBuffer != null)
				return false;
		} else if (!mNormBuffer.equals(other.mNormBuffer))
			return false;
		if (!Arrays.equals(mText, other.mText))
			return false;
		if (mTextBuffer == null) {
			if (other.mTextBuffer != null)
				return false;
		} else if (!mTextBuffer.equals(other.mTextBuffer))
			return false;
		if (mTextureId != other.mTextureId)
			return false;
		if (!Arrays.equals(mVerts, other.mVerts))
			return false;
		if (mVertsBuffer == null) {
			if (other.mVertsBuffer != null)
				return false;
		} else if (!mVertsBuffer.equals(other.mVertsBuffer))
			return false;
		if (mVertsColNormBuffer == null) {
			if (other.mVertsColNormBuffer != null)
				return false;
		} else if (!mVertsColNormBuffer.equals(other.mVertsColNormBuffer))
			return false;
		if (numIndices != other.numIndices)
			return false;
		if (stride != other.stride)
			return false;
		if (textureSet != other.textureSet)
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		if (Float.floatToIntBits(xRot) != Float.floatToIntBits(other.xRot))
			return false;
		if (Float.floatToIntBits(xTrans) != Float.floatToIntBits(other.xTrans))
			return false;
		if (Float.floatToIntBits(yRot) != Float.floatToIntBits(other.yRot))
			return false;
		if (Float.floatToIntBits(yTrans) != Float.floatToIntBits(other.yTrans))
			return false;
		if (Float.floatToIntBits(zRot) != Float.floatToIntBits(other.zRot))
			return false;
		if (Float.floatToIntBits(zTrans) != Float.floatToIntBits(other.zTrans))
			return false;
		return true;
	}

	
	/*
	static final int X0 = 0;
	static final int Y0 = 1;
	static final int Z0 = 2;
	static final int XN0 = 3;
	static final int YN0 = 4;
	static final int ZN0 = 5;
	static final int U0 = 6;
	static final int V0 = 7;
	
	static final int X1 = 8;
	static final int Y1 = 9;
	static final int Z1 = 10;
	static final int XN1 = 11;
	static final int YN1 = 12;
	static final int ZN1 = 13;
	static final int U1 = 14;
	static final int V1 = 15;
	
	static final int X2 = 16;
	static final int Y2 = 17;
	static final int Z2 = 18;
	static final int XN2 = 19;
	static final int YN2 = 20;
	static final int ZN2 = 21;
	static final int U2 = 22;
	static final int V2 = 23;
	
	static final int X3 = 24;
	static final int Y3 = 25;
	static final int Z3 = 26;
	static final int XN3 = 27;
	static final int YN3 = 28;
	static final int ZN3 = 29;
	static final int U3 = 30;
	static final int V3 = 31;*/
}
