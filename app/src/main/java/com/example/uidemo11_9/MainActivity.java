package com.example.uidemo11_9;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.iotek.entity.CurrentWeather;
import com.iotek.fragment.CityFragment;
import com.iotek.helper.DBHelper;
import com.iotek.utils.DatabaseUtil;
import com.iotek.utils.JSONUtil;
import com.iotek.utils.RequestUtil;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity extends FragmentActivity implements OnClickListener{
	ViewPager vp_main;
	Button bt_add_city;
	LinkedList<CurrentWeather> data;
	ArrayList<Fragment> pagers;
	
	MyPagerAdapter adapter;
	
	
	SQLiteDatabase db;
	DBHelper helper;
	
	public static final int UPDATE_SUCCES = 0x100;
	public static final int UPDATE_FAULT = 0x101;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        listener();
    }

    private void initData(){
    	helper = new DBHelper(this, DatabaseUtil.DB, null, DatabaseUtil.VISION);
    	db = helper.getWritableDatabase();
		data = DatabaseUtil.query(db);
    	//数据库为空，无需更新数据，反之更新
    	if(data != null){
    		new Thread(){
    			public void run() {
    				Message msg = new Message();
    				LinkedList<CurrentWeather> temp = new LinkedList<CurrentWeather>();
    				for (int i = 0; i < data.size(); i++) {
						String res = RequestUtil.getWeaher(data.get(i).getArea());
						CurrentWeather cw= JSONUtil.getInstance(res, MainActivity.this);
						if(cw != null){
							temp.add(cw);
						}else{
							handler.sendEmptyMessage(UPDATE_FAULT);
							return;
						}
    				}
    				msg.obj = temp;
    				handler.sendMessage(msg);
    			};
    		}.start();
    	}
    }
    
    private void initView(){
    	bt_add_city = (Button) findViewById(R.id.bt_add_city);
    	vp_main = (ViewPager) findViewById(R.id.vp_main);
    }
    
    private void listener(){
    	bt_add_city.setOnClickListener(this);
    }
    
    @Override
    protected void onStart() {
    	//进行Fragment和ViewPager
    	super.onStart();
    	updateUI();
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	//更新前先删除所有表中原数据
    	db.delete(DatabaseUtil.TABLE_CUR, null, null);
    	for (int i = 0; i < data.size(); i++) {
			DatabaseUtil.insert(db, data.get(i));
		}
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	db.close();
    }
    
    
    public void updateUI(){
    	pagers = new ArrayList<Fragment>();
    	for (int i = 0; i < data.size(); i++) {
			pagers.add(CityFragment.newInstance(data.get(i)));
		}
    	adapter = new MyPagerAdapter(getSupportFragmentManager());
    	vp_main.setAdapter(adapter);
    }
    
    
    Handler handler = new Handler(){
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
			case UPDATE_SUCCES:
				data = (LinkedList<CurrentWeather>) msg.obj;
				updateUI();
				break;
			case UPDATE_FAULT:
				Toast.makeText(MainActivity.this, "数据更新失败，请检查网络", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
    	};
    };
    
    public class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return pagers.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pagers.size();
		}
    	
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(arg0, arg1, arg2);
    	switch (arg1) {
		case UPDATE_SUCCES:
			CurrentWeather cw = arg2.getParcelableExtra("city");
	    	Log.i("123", cw.getArea());
	    	if(data.contains(cw)){
	    		int i = 0;
	    		for (; i < data.size(); i++) {
					if(cw.equals(data.get(i))){
						Log.i("123", i+"");
						break;
					}
				}
	    		data.remove(i);
	    	}else{
				
	    		data.add(cw);
	    	}
			break;
		case UPDATE_FAULT:
			Toast.makeText(MainActivity.this, "数据更新失败，请检查网络", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
    	
    	
    }
    
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, CitySelectActivity.class);
		startActivityForResult(intent, 0x10);
	}
    
    
    
    
}
