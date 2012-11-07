package com.blubee.bluengine.graphics;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class SpriteSheetLoader {
	
	private Context context;
	Texture texture;
	JSONObject list;
	HashMap<String, SpriteSheetRegion> regions;
	
	public SpriteSheetLoader(Context context)
	{
		this.context = context;
		regions = new HashMap<String, SpriteSheetRegion>();
	}
	
	public SpriteSheetLoader(Context context, Texture texture)
	{
		this.context = context;
		this.texture = texture;
		regions = new HashMap<String, SpriteSheetRegion>();
	}
	
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	public void loadId(int id)
	{
		makeSheet(loadSheet(id));
	}
	
	public void loadString(String file)
	{
		makeSheet(file);
	}
	
	@SuppressWarnings("unused")
	private void makeSheet(String sheet){
		int len;
		try {
			list = new JSONObject(sheet);
			JSONObject meta = (JSONObject)list.get("meta");
			String format = meta.getString("format");
			String ver = meta.getString("version");
			String img = meta.getString("image");
			float scale = (float) meta.getDouble("scale");
			JSONObject dim = meta.getJSONObject("size");
			float tW = dim.getInt("w");
			float tH = dim.getInt("h");
			JSONArray parts = (JSONArray) list.get("frames");
			len = parts.length();
			JSONObject frame;
			String name;
			float x = 0, y = 0, w = 0, h = 0, smin = 0, smax = 0, tmin = 0, tmax = 0;
			boolean rotated = false;
			boolean trimmed = false;
			for(int i = 0; i < len; i++){
				JSONObject obj = (JSONObject) parts.get(i);
				frame = obj.getJSONObject("frame");
				name = obj.getString("filename");
				rotated = obj.getBoolean("rotated");
				trimmed = obj.getBoolean("trimmed");
				if(trimmed)
				{
					continue;
				}
				if(rotated){
					y = frame.getInt("y");
					x = frame.getInt("x");
					w = frame.getInt("h");
					h = frame.getInt("w");
					
					smin = (x+0.5f)/tW;
					smax = (x+w-0.5f)/tW;
					tmin = (y+0.5f)/tH;
					tmax = (y+h-0.5f)/tH;
				}else{
				x = frame.getInt("x");
				y = frame.getInt("y");
				w = frame.getInt("w");
				h = frame.getInt("h");
				smin = (x+0.5f)/tW;
				smax = (x+w-0.5f)/tW;
				tmin = (y+0.5f)/tH;
				tmax = (y+h-0.5f)/tH;
				}
				regions.put(name, new SpriteSheetRegion(texture, w/scale, h/scale, smin, smax, tmin, tmax, rotated));
			}
			//Log.v("Sprite sheet loader", "loaded file : "+img+" format : "+format+" version: "+ver+" rot: "+rotated);
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}
	
	private String loadSheet(int id){
		StringBuilder spriteSheet = new StringBuilder();
		try{
		InputStream is = context.getResources().openRawResource(id);
		byte[] buffer = new byte[is.available()];
		while(is.read(buffer) != -1){
			String parts = new String(buffer);
			spriteSheet.append(parts);
		}
		}catch(IOException e){
			e.printStackTrace();		
		}
		return spriteSheet.toString();
	}
	
	public SpriteSheetRegion getSprite(String n){
		SpriteSheetRegion r = null;
		for(int i = 0; i < regions.size(); i++){
			//Log.v("spritesheet loader getsprite", "getting "+n+" is null "+(r));
			r = regions.get(n);
		}
		return r;
	}
	
	
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
}
