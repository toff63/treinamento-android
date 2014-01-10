package com.example.horastrabalhadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.horastrabalhadas.keys.PreferenceKeys;
import com.example.horastrabalhadas.utils.SharedPreferenceUtil;
import com.example.horastrabalhadas.utils.UIFieldAccess;

public class MainActivity extends Activity {

	private int counterPause = 0;
	private int counterStart= 0;
	private UIFieldAccess fieldAccess = new UIFieldAccess();
	private SharedPreferenceUtil pref = new SharedPreferenceUtil();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addSubmitEvent();
	}
	
	private void addSubmitEvent() {
		findViewById(R.id.main_emit).setOnClickListener(new LoginListener(this));
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("CHANGED", "Application started " + ++counterStart + " times");
		Toast.makeText(getApplicationContext(), "Application started " + counterStart + " times", Toast.LENGTH_SHORT).show();
		if(pref.getString(this, PreferenceKeys.NAME) != null && pref.getString(this, PreferenceKeys.PASSWORD) != null){
			startActivity(getIntent(fieldAccess.getTextById(this, R.id.nome), fieldAccess.getTextById(this, R.id.senha)));
		} else 	fillFormBasedOnPreferences();
	}

	private Intent getIntent(String name, String password) {
		Intent intent = new Intent(getBaseContext(), FormResultActivity.class);
		intent.putExtra("name", name);
		intent.putExtra("senha", password);
		return intent;
	}
	
	private void fillFormBasedOnPreferences() {
		fillFromPreference(PreferenceKeys.NAME, R.id.nome);
		fillFromPreference(PreferenceKeys.PASSWORD, R.id.senha);
	}

	private void fillFromPreference(String key, int id) {
		fieldAccess.setTextById(this, id, pref.getString(this, key));
	}

	@Override
	protected void onStop() {
		super.onStart();
		Log.d("CHANGED", "Application stopped");
	}

	@Override
	protected void onPause(){
		super.onPause();
		counterPause++;
		Log.d("CHANGED", "Application paused " + counterPause + " times");
		Toast.makeText(getApplicationContext(), "Application paused " + counterPause + " times", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
