package com.example.horastrabalhadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FormResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_result);
		setupWelcome();
		handleCloseButton();
		handleAddTask();
	}

	private void handleAddTask() {
		findViewById(R.id.activity).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), TaskFormActivity.class));
			}
		});
	}

	private void handleCloseButton() {
		findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	private void setupWelcome() {
		Toast.makeText(getApplicationContext(), getIntent().getStringExtra("name") + " has password " + getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
		TextView text = (TextView) findViewById(R.id.form_name);
		text.setText("Bemvindo " + getIntent().getStringExtra("name"));
	}
}
