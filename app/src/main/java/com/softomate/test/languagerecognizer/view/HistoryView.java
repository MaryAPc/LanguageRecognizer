package com.softomate.test.languagerecognizer.view;

import java.util.List;

import com.arellomobile.mvp.MvpView;
import com.softomate.test.languagerecognizer.model.HistoryItem;

public interface HistoryView extends MvpView {
	void setList(List<HistoryItem> history);
}
