package com.example.horastrabalhadas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private final static String DB_NAME= "lancamento_horas.db";
	private final static int VERSION = 1;
	private final static String SQL_CREATE_TASK_TABLE = "CREATE TABLE IF NOT EXISTS TASK(data VARCHAR, description VARCHAR)";
	
	public DbHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TASK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
