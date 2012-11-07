package com.blubee.bluengine.graphics;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class SpriteSheet {

	Context context;
	Texture texture;
	JSONObject list;
	HashMap<String, SpriteSheetRegion> regions;

	public SpriteSheet(Context c, Texture t, int id) {
		this.context = c;
		this.texture = t;
		regions = new HashMap<String, SpriteSheetRegion>();
		makeSheet(loadSheet(id));
	}

	@SuppressWarnings("unused")
	public void makeSheet(String sheet) {
		int len;
		try {
			list = new JSONObject(sheet);
			JSONObject meta = (JSONObject) list.get("meta");
			// System.out.println("meta "+meta.toString());
			String format = meta.getString("format");
			String ver = meta.getString("version");
			String img = meta.getString("image");
			float scale = (float) meta.getDouble("scale");
			JSONObject dim = meta.getJSONObject("size");
			float tW = dim.getInt("w");
			float tH = dim.getInt("h");
			// System.out.println(ver+" "+img+" "+scale+" "+tW+" "+tH+" "+format);

			JSONArray parts = (JSONArray) list.get("frames");
			len = parts.length();
			JSONObject frame;
			String name;
			float x = 0, y = 0, w = 0, h = 0, smin = 0, smax = 0, tmin = 0, tmax = 0;
			boolean rotated = false;
			boolean trimmed = false;
			for (int i = 0; i < len; i++) {
				JSONObject obj = (JSONObject) parts.get(i);
				// System.out.println("parts "+obj.toString());
				frame = obj.getJSONObject("frame");
				name = obj.getString("filename");
				rotated = obj.getBoolean("rotated");
				trimmed = obj.getBoolean("trimmed");
				if (rotated) {
					y = frame.getInt("y");
					x = frame.getInt("x");
					w = frame.getInt("h");
					h = frame.getInt("w");

					smin = (x + 0.5f) / tW;
					smax = (x + w - 0.5f) / tW;
					tmin = (y + 0.5f) / tH;
					tmax = (y + h - 0.5f) / tH;
				} else {
					x = frame.getInt("x");
					y = frame.getInt("y");
					w = frame.getInt("w");
					h = frame.getInt("h");
					smin = (x + 0.5f) / tW;
					smax = (x + w - 0.5f) / tW;
					tmin = (y + 0.5f) / tH;
					tmax = (y + h - 0.5f) / tH;
				}
				// System.out.println("sizes "+tW+" "+tH+" "+x+" "+y+" "+w+" "+h);
				// System.out.println(smin+" "+smax+" "+tmin+" "+tmax+" rotated "+rotated);
				regions.put(name, new SpriteSheetRegion(texture, w / scale, h
						/ scale, smin, smax, tmin, tmax, rotated));
				// System.out.println("index "+i+" "+name+" "+x+" "+y+" "+w/scale+" "+h/scale+" "+smin+" "+smax+" "+tmin+" "+tmax+" rot "+rotated+" tr "+trimmed);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public String loadSheet(int id) {
		StringBuilder spriteSheet = new StringBuilder();
		try {
			InputStream is = context.getResources().openRawResource(id);
			byte[] buffer = new byte[is.available()];
			while (is.read(buffer) != -1) {
				String parts = new String(buffer);
				// JSONArray entries = new JSONArray(parts);
				// x = "JSON parsed.\nThere are [" + entries.length() + "]\n\n";
				spriteSheet.append(parts);
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		return spriteSheet.toString();
	}

	public SpriteSheetRegion getSprite(String n) {
		SpriteSheetRegion r = null;
		for (int i = 0; i < regions.size(); i++) {
			r = regions.get(n);
		}
		return r;
	}

	public static float round(double unrounded, int precision, int roundingMode) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, roundingMode);
		return rounded.floatValue();
	}
}
