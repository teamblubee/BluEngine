package com.blubee.bluengine.graphics;


public class SpriteSheetRegion {

	public Texture texture;
	public float width;
	public float height;
	public boolean isRoatated;
	public boolean xTrim, yTrim;
	public float sMin, sMax, tMin, tMax;
	
	public SpriteSheetRegion(Texture t, float width, float height, float sMin, float sMax, float tMin, float tMax, boolean rotated){
		this.texture = t;
		this.width = width;
		this.height = height;
		this.sMin = sMin;
		this.sMax = sMax;
		this.tMin = tMin;
		this.tMax = tMax;
		this.isRoatated = rotated;
	}
}
