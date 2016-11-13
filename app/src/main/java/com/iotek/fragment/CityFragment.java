package com.iotek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uidemo11_9.R;
import com.iotek.entity.CurrentWeather;

public class CityFragment extends Fragment {
	
	public static CityFragment newInstance(CurrentWeather cw){
		CityFragment f = new CityFragment();
		
		Bundle bundle = new Bundle();
		bundle.putParcelable("weather", cw);
		f.setArguments(bundle);
		
		return f;
	}
	
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main, null);
		TextView tv_city = (TextView) v.findViewById(R.id.tv_main);
		
		Bundle bundle = getArguments();
		CurrentWeather cw = bundle.getParcelable("weather");
		
		tv_city.setText(cw.toString());
		
		return v;
	}
	
	
	
	
	
	
	
	
}
