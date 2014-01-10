package com.example.horastrabalhadas;

import java.util.ArrayList;
import java.util.List;

import com.example.horastrabalhadas.db.dao.TaskDao;
import com.example.horastrabalhadas.domain.Task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FormResultActivity extends Activity {
	
	private TaskDao taskDao = new  TaskDao();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_result);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		setupWelcome();
		handleCloseButton();
		handleAddTask();
		fillTaskList(retrieveTasks());
	}
	
	private List<Task> retrieveTasks() {
		return taskDao.list(getApplicationContext());
	}

	private void fillTaskList(List<Task> tasks) {
		ListView mDrawerList = (ListView) findViewById(R.id.form_list_task);
		mDrawerList.setAdapter(new TaskAdapter(getBaseContext(), R.layout.activity_task_item, tasks));
	}
	
	public class TaskAdapter extends ArrayAdapter<Task> {

		private List<Task> itens;

		public TaskAdapter(Context context, int textViewResourceId, List<Task> itens) {
			super(context, textViewResourceId, itens);
			this.itens = itens;	
		}

		@Override
		public int getCount() {
			return itens.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View gridView = convertView;
			if (convertView == null) { // if it's not recycled, initialize some
				// attributes
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				gridView = inflater.inflate(R.layout.activity_task_item, parent, false);
			}
			Task task = itens.get(position);
			((TextView) gridView.findViewById(R.id.task_item_date)).setText(task.date);
			((TextView) gridView.findViewById(R.id.task_item_description)).setText(task.description);
			return gridView;
		}

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
