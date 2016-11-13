package com.iotek.helper;

import com.iotek.utils.DatabaseUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table " + DatabaseUtil.TABLE_CUR + "("
				+ DatabaseUtil._ID + " integer primary key,"
				+ DatabaseUtil.AREA + " varchar," + DatabaseUtil.TEMPERATURE
				+ " varchar," + DatabaseUtil.WEATHER + " varchar,"
				+ DatabaseUtil.WIND_DIRECTION + " varchar,"
				+ DatabaseUtil.WIND_POWER + " varchar,"
				+ DatabaseUtil.TEMPERATURE_TIME + " varchar," + DatabaseUtil.SD
				+ " varchar," + DatabaseUtil.AQI + " integer)";
		db.execSQL(sql);
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
