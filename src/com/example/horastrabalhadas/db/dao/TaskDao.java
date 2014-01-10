package com.example.horastrabalhadas.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.horastrabalhadas.db.DbHelper;
import com.example.horastrabalhadas.domain.Task;

public class TaskDao {

	public void save(Context context, Task task){
		DbHelper dbHelper = new DbHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("data", task.date);
		cv.put("description", task.description);
		db.insert("TASK", null, cv);
		dbHelper.close();
	}
	
	public List<Task> list(Context context){
		DbHelper dbHelper = new DbHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("TASK", new String[]{"data", "description"}, null, null, null, null, null);
		cursor.moveToFirst();
		List<Task> tasks = new ArrayList<Task>();
		while(!cursor.isAfterLast()){
			tasks.add(new Task(cursor.getString(0), cursor.getString(1)));
			cursor.moveToNext();
		}
		dbHelper.close();
		return tasks;
	}
}
