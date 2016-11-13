package com.iotek.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class CurrentWeather implements Parcelable{
	private String temperature_time;
	private String wind_direction;
	private String wind_power;
	private int aqi;
	private String sd;
	private String weather;
	private String temperature;
	private String area;
	public CurrentWeather() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurrentWeather(String temperature_time, String wind_direction,
			String wind_power, int aqi, String sd, String weather,
			String temperature,String area) {
		super();
		this.temperature_time = temperature_time;
		this.wind_direction = wind_direction;
		this.wind_power = wind_power;
		this.aqi = aqi;
		this.sd = sd;
		this.weather = weather;
		this.temperature = temperature;
		this.area = area;
	}
	public String getTemperature_time() {
		return temperature_time;
	}
	public void setTemperature_time(String temperature_time) {
		this.temperature_time = temperature_time;
	}
	public String getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}
	public String getWind_power() {
		return wind_power;
	}
	public void setWind_power(String wind_power) {
		this.wind_power = wind_power;
	}
	public int getAqi() {
		return aqi;
	}
	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String toString() {
		return "CurrentWeather [temperature_time=" + temperature_time
				+ ", wind_direction=" + wind_direction + ", wind_power="
				+ wind_power + ", aqi=" + aqi + ", sd=" + sd + ", weather="
				+ weather + ", temperature=" + temperature + ", area=" + area
				+ "]";
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(temperature_time);
		dest.writeString(wind_direction);
		dest.writeString(wind_power);
		dest.writeInt(aqi);
		dest.writeString(sd);
		dest.writeString(weather);
		dest.writeString(temperature);
		dest.writeString(area);
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}


	public static final Parcelable.Creator<CurrentWeather> CREATOR = new Creator<CurrentWeather>() {
		
		@Override
		public CurrentWeather[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CurrentWeather[size];
		}
		
		@Override
		public CurrentWeather createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			CurrentWeather cw = new CurrentWeather();
			cw.temperature_time = source.readString();
			cw.wind_direction = source.readString();
			cw.wind_power = source.readString();
			cw.aqi = source.readInt();
			cw.sd = source.readString();
			cw.weather = source.readString();
			cw.temperature = source.readString();
			cw.area = source.readString();
			return cw;
		}
	};
	
	public boolean equals(Object o) {
		Log.d("Test_Current136",this.area+">>>>>>>"+((CurrentWeather)o).area);
		return this.area.equals(((CurrentWeather)o).area);
	};
	
}
