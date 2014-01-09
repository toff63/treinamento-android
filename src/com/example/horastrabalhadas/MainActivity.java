package com.example.horastrabalhadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int counterPause = 0;
	private int counterStart= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addSubmitEvent();
	}
	
	private void addSubmitEvent() {
		final EditText name = (EditText) findViewById(R.id.nome);
		final EditText password = (EditText) findViewById(R.id.senha);
		
		findViewById(R.id.main_emit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), name.getText().toString() + " has password " + password.getText().toString(), Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getBaseContext(), FormResultActivity.class);
				intent.putExtra("name", name.getText().toString() );
				intent.putExtra("senha", password.getText().toString() );
				finish();
				startActivity(intent);
			}
		});
		
	}
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		if(requestCode == REQUEST_CODE) Toast.makeText(getApplicationContext(), "Activty filha fechou", Toast.LENGTH_SHORT).show();
//	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("CHANGED", "Application started " + ++counterStart + " times");
		Toast.makeText(getApplicationContext(), "Application started " + counterStart + " times", Toast.LENGTH_SHORT).show();
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
