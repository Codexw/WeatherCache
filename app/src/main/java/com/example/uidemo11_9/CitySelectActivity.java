package com.example.uidemo11_9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.iotek.entity.CurrentWeather;
import com.iotek.utils.JSONUtil;
import com.iotek.utils.RequestUtil;

public class CitySelectActivity extends Activity {
	EditText et;
	Button bt_add;
	
	String city;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selet_city);
		et = (EditText) findViewById(R.id.et);
		bt_add = (Button) findViewById(R.id.bt_add);	
		
		bt_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				city = et.getText()+"";
				
				new Thread(){
					public void run() {
						String res = RequestUtil.getWeaher(city);
						CurrentWeather cw= JSONUtil.getInstance(res, CitySelectActivity.this);
						Message msg = new Message();
						Log.i("123", cw.toString());
						msg.what = 0x00;
						msg.obj = cw;
						handler.sendMessage(msg);
					};
				}.start();
			}
		});
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x00:
				if(msg.obj == null){
					setResult(MainActivity.UPDATE_FAULT);
				}else{
					Intent intent = new Intent();
					CurrentWeather cw = (CurrentWeather) msg.obj;
					intent.putExtra("city", cw);
					setResult(MainActivity.UPDATE_SUCCES, intent);
					Log.d("Test_CitySelect",cw.toString());
				}
				break;

			default:
				break;
			}
			finish();
		};
	};
	
	
	
	
	
}
