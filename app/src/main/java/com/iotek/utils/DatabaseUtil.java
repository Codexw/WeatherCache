package com.iotek.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iotek.entity.CurrentWeather;

import java.util.LinkedList;

public class DatabaseUtil {
	public static final String DB = "weather";
	public static final String TABLE_CUR = "CurrentWeather";
	public static final String _ID = "_id";//主键、非有关项
	public static final String AREA = "area";
	public static final String TEMPERATURE = "temperature";
	public static final String WEATHER = "weather";
	public static final String SD = "sd";
	public static final String WIND_POWER = "wind_power";
	public static final String WIND_DIRECTION = "wind_direction";
	public static final String TEMPERATURE_TIME = "temperature_time";
	public static final String AQI = "aqi";
	public static final int VISION = 1;
	
	
	 /**
	  * 数据库插入方法，工具方法
	  * @author sjv
	  * @param db 需要操作的数据库
	  * @param cw 插入的实体类对象
	  * @return 如果为false表示插入失败，反之成功
	  */
	public static boolean insert(SQLiteDatabase db,CurrentWeather cw){
		ContentValues cv = new ContentValues();
		cv.put(AREA, cw.getArea());
		cv.put(WEATHER, cw.getWeather());
		cv.put(TEMPERATURE, cw.getTemperature());
		cv.put(AQI, cw.getAqi());
		cv.put(SD, cw.getSd());
		cv.put(WIND_DIRECTION, cw.getWind_direction());
		cv.put(WIND_POWER, cw.getWind_power());
		cv.put(TEMPERATURE_TIME, cw.getTemperature_time());
		if(db.insert(TABLE_CUR, null, cv) == -1){
			return false;
		}else{
			return true;
		}
	}
	
	public static LinkedList<CurrentWeather> query(SQLiteDatabase db){
		LinkedList<CurrentWeather> data = new LinkedList<CurrentWeather>();
		
		Cursor cursor = db.query(TABLE_CUR, null, null, null, null, null, null);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			CurrentWeather cw = new CurrentWeather();
			cw.setArea(cursor.getString(cursor.getColumnIndex(AREA)));
			cw.setAqi(cursor.getInt(cursor.getColumnIndex(AQI)));
			cw.setSd(cursor.getString(cursor.getColumnIndex(SD)));
			cw.setTemperature(cursor.getString(cursor.getColumnIndex(TEMPERATURE)));
			cw.setTemperature_time(cursor.getString(cursor.getColumnIndex(TEMPERATURE_TIME)));
			cw.setWeather(cursor.getString(cursor.getColumnIndex(WEATHER)));
			cw.setWind_direction(cursor.getString(cursor.getColumnIndex(WIND_DIRECTION)));
			cw.setWind_power(cursor.getString(cursor.getColumnIndex(WIND_POWER)));
			data.add(cw);
		}
		
		return data;
	}
	
}
