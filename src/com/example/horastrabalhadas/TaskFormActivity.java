package com.example.horastrabalhadas;

import com.example.horastrabalhadas.db.dao.TaskDao;
import com.example.horastrabalhadas.domain.Task;
import com.example.horastrabalhadas.utils.UIFieldAccess;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class TaskFormActivity extends Activity {
	
	private UIFieldAccess fieldAccess = new UIFieldAccess();
	private TaskDao taskDao = new  TaskDao();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		addSubmitEvent();
	}

	private void addSubmitEvent() {
		final Activity ac = this;
		findViewById(R.id.task_add_task).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(isInvalid((EditText) findViewById(R.id.task_description)) ){
					Toast.makeText(getApplicationContext(), R.string.description_missing, Toast.LENGTH_SHORT).show();
				}
				else {
					close();
				}
			}

			private void close() {
				taskDao.save(getApplicationContext(), getTask(ac));
				Log.i("TASKS", taskDao.list(getApplicationContext()).toString());
				Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_SHORT).show();
				finish();
			}

			private Task getTask(final Activity ac) {
				DatePicker datePicker = (DatePicker) findViewById(R.id.task_date);
				String date = datePicker.getYear() + "-" + datePicker.getMonth()+1 + "-" + datePicker.getDayOfMonth();
				Task task = new Task(date, fieldAccess.getTextById(ac, R.id.task_description));
				return task;
			}

			private boolean isInvalid(final EditText description) {
				return description.getText() == null || description.getText().length() == 0;
			}
		});
	}
}
