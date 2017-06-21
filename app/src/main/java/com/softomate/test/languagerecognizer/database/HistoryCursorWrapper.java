package com.softomate.test.languagerecognizer.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.softomate.test.languagerecognizer.model.HistoryItem;

public class HistoryCursorWrapper extends CursorWrapper {

	public HistoryCursorWrapper(Cursor cursor) {
		super(cursor);
	}

	public HistoryItem mGetHistoryItem() {
		String text = getString(getColumnIndex(HistoryDbSchema.HistoryTable.Cols.TEXT));
		String language = getString(getColumnIndex(HistoryDbSchema.HistoryTable.Cols.LANGUAGE));

		HistoryItem item = new HistoryItem();
		item.setText(text);
		item.setLanguage(language);

		return item;
	}
}
