package com.softomate.test.languagerecognizer.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.softomate.test.languagerecognizer.model.HistoryItem;

import rx.Observable;

public class HistoryDb {

	private static SQLiteDatabase mDatabase;

	public HistoryDb(Context context) {
		if (mDatabase == null) {
			mDatabase = new DBHelper(context).getWritableDatabase();
		}
	}

	public void addHistory(HistoryItem request) {
		ContentValues values = getContentValues(request);
		mDatabase.insert(HistoryDbSchema.HistoryTable.NAME, null, values);
	}

	private static ContentValues getContentValues(HistoryItem request) {
		ContentValues values = new ContentValues();
		values.put(HistoryDbSchema.HistoryTable.Cols.TEXT, request.getText());
		values.put(HistoryDbSchema.HistoryTable.Cols.LANGUAGE, request.getLanguage());
		return values;
	}

	private HistoryCursorWrapper queryHistory(String whereClause, String[] whereArgs) {
		Cursor cursor = mDatabase.query(HistoryDbSchema.HistoryTable.NAME, null, whereClause, whereArgs, null, null, null);
		return new HistoryCursorWrapper(cursor);
	}

	public Observable<List<HistoryItem>> getHistory() {
		List<HistoryItem> history = new ArrayList<>();
		HistoryCursorWrapper cursor = queryHistory(null, null);
		try {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				history.add(cursor.mGetHistoryItem());
				cursor.moveToNext();
			}
		} finally {
			cursor.close();
		}
		return Observable.just(history);
	}
}
