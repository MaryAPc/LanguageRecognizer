package com.softomate.test.languagerecognizer.presenter;

import java.util.List;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.softomate.test.languagerecognizer.MyApplication;
import com.softomate.test.languagerecognizer.database.HistoryDb;
import com.softomate.test.languagerecognizer.model.HistoryItem;
import com.softomate.test.languagerecognizer.view.HistoryView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {

	private Context mContext = MyApplication.getInstance();
	private HistoryDb mDatabase = new HistoryDb(mContext);

	public void requestDatabase() {
		requestHistory()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<HistoryItem>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
					}

					@Override
					public void onNext(List<HistoryItem> history) {
						getViewState().setList(history);
					}
				});
	}

	private Observable<List<HistoryItem>> requestHistory() {
		return mDatabase.getHistory();
	}
}
