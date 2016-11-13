package com.iotek.utils;


import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;

public class RequestUtil {
	public static final String SIGN = "*****************";
	public static final String APPID = "*****";
	public static final String DES = "http://route.showapi.com/9-2";
	
	public static String getWeaher(String area){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = null;
		try {
			
			String des = URLDecoder.decode(DES+"?"+"showapi_appid="+APPID+"&showapi_sign="+SIGN+"&area="+area, "UTF-8");
			URL url = new URL(des);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//设置请求格式
			conn.setRequestMethod("GET");
			//设置超时时间
			conn.setReadTimeout(1000*5);
			//获取连接输入流
			in = conn.getInputStream();
			//执行流读写
			/*请求返回码 
			 * 2**  请求成功   3** 重定向   4** 请求有误   5** 服务器有误
			 */
			
			if(conn.getResponseCode() == 200){
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = in.read(buffer))!=-1){
					out.write(buffer, 0, len);
				}
			}
			Log.i("123", "download:"+out.toString());
			return out.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	

}
