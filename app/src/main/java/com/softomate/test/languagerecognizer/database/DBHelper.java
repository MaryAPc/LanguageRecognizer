package com.softomate.test.languagerecognizer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "history.db";
	private static final int VERSION = 1;


	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL("create table " + HistoryDbSchema.HistoryTable.NAME + "(" +
		                       " _id integer primary key autoincrement, " +
		                       HistoryDbSchema.HistoryTable.Cols.TEXT + ", " +
		                       HistoryDbSchema.HistoryTable.Cols.LANGUAGE + ")"
		                      );
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		//Write this method, if database version was upgraded
	}
}
