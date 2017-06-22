package com.softomate.test.languagerecognizer.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface RecognizerView extends MvpView {

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showDialog(String title, String message);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress();

	@StateStrategyType(AddToEndSingleStrategy.class)
	void hideProgress();
}
