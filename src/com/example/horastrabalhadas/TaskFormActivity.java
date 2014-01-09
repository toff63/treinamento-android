package com.example.horastrabalhadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class TaskFormActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		addSubmitEvent();
	}

	private void addSubmitEvent() {
		findViewById(R.id.task_add_task).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final DatePicker date = (DatePicker) findViewById(R.id.task_date);
				final EditText description = (EditText) findViewById(R.id.task_description);
				if(description.getText() == null || description.getText().length() == 0 ) Toast.makeText(getApplicationContext(), R.string.description_missing, Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		});
	}
}
