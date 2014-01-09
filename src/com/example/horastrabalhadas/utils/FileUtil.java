package com.example.horastrabalhadas.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class FileUtil {
	public String read(Activity ac, String filename){
		try {
			FileInputStream is = ac.openFileInput(filename);
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			StringBuilder total = new StringBuilder();
			String line;
			try {
				while ((line = r.readLine()) != null) total.append(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
			is.close();
			return total.toString();
		} catch (Exception e) {
			Log.e("IO", "Failed to read file", e);
		}
		return "";
		
	}
	
	public void write(Activity ac, String filename, String content){
		try {
			FileOutputStream fos = ac.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
