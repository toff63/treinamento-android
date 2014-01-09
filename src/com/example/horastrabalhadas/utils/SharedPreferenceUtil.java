package com.example.horastrabalhadas.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

	public void addString(Activity ac, String content, String key){
		SharedPreferences pref = ac.getPreferences(Context.MODE_PRIVATE);
		pref.edit().putString(key, content).commit();
	}
	
	public String getString(Activity ac, String key){
		SharedPreferences pref = ac.getPreferences(Context.MODE_PRIVATE);
		return pref.getString(key, null);
	}
}
