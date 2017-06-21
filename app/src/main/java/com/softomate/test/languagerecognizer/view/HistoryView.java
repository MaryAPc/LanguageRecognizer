package com.softomate.test.languagerecognizer.view;

import java.util.List;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.softomate.test.languagerecognizer.model.HistoryItem;

@StateStrategyType(SkipStrategy.class)
public interface HistoryView extends MvpView {

	void setList(List<HistoryItem> history);
}
