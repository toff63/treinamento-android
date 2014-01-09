package com.example.horastrabalhadas.utils;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

public class UIFieldAccess {

	public String getTextById(Activity activity, int id){
		return ((EditText) activity.findViewById(id)).getText().toString();
	}
	
	public void setTextById(Activity activity, int id, String content){
		TextView textName = (TextView) activity.findViewById(id);
		textName.setText(content);
	}
	
}
