package com.iotek.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.iotek.entity.CurrentWeather;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {
	public static final String AREA = "area";
	public static final String TEMPERATURE = "temperature";
	public static final String WEATHER = "weather";
	public static final String SD = "sd";
	public static final String WIND_POWER = "wind_power";
	public static final String WIND_DIRECTION = "wind_direction";
	public static final String TEMPERATURE_TIME = "temperature_time";
	public static final String AQI = "aqi";

	public static CurrentWeather getInstance(String res,Context context){
		CurrentWeather cw = null;
		try {
			JSONObject obj = new JSONObject(res);
			if(obj.getInt("showapi_res_code") != 0){
				Toast.makeText(context, obj.getString("showapi_res_error"), Toast.LENGTH_SHORT).show();
				Log.i("123", "code:"+obj.getInt("showapi_res_code"));
				return null;
			}
			cw = new CurrentWeather();
			JSONObject body = obj.getJSONObject("showapi_res_body");
			//获取area
			JSONObject cityInfo = body.getJSONObject("cityInfo");
			StringBuffer buffer = new StringBuffer();
			buffer.append(cityInfo.getString("c9")+"/");
			buffer.append(cityInfo.getString("c7")+"/");
			
			if(!cityInfo.get("c5").equals(cityInfo.getString("c7"))){
				buffer.append(cityInfo.get("c5")+"/");
			}
			if(!cityInfo.getString("c3").equals(cityInfo.getString("c5"))){
				buffer.append(cityInfo.getString("c3"));
			}
			Log.i("123", "buffer:"+buffer.toString());
			cw.setArea(buffer.toString());
			//获取当前天气情况
			JSONObject now = body.getJSONObject("now");
			cw.setAqi(now.getInt(AQI));
			cw.setSd(now.getString(SD));
			cw.setTemperature(now.getString(TEMPERATURE));
			cw.setTemperature_time(now.getString(TEMPERATURE_TIME));
			cw.setWeather(now.getString(WEATHER));
			cw.setWind_direction(now.getString(WIND_DIRECTION));
			cw.setWind_power(now.getString(WIND_POWER));
			return cw;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	



}
