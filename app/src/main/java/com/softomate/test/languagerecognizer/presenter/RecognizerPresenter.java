package com.softomate.test.languagerecognizer.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.softomate.test.languagerecognizer.MyApplication;
import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.database.HistoryDb;
import com.softomate.test.languagerecognizer.model.DetectResponse;
import com.softomate.test.languagerecognizer.model.HistoryItem;
import com.softomate.test.languagerecognizer.network.RetrofitService;
import com.softomate.test.languagerecognizer.view.RecognizerView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class RecognizerPresenter extends MvpPresenter<RecognizerView> {

	private Context mContext = MyApplication.getInstance();
	private HistoryDb mDatabase = new HistoryDb(mContext);
	private Subscription mSubscription;

	public void detect(String text) {
		if (text.length() == 0) {
			getViewState().showDialog(getStringRes(R.string.error_title), getStringRes(R.string.error_message_empty_text));
		} else {
			getViewState().showProgress();
			mSubscription = requestDetectLanguage(text)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<DetectResponse>() {
						@Override
						public void onCompleted() {
							getViewState().hideProgress();
						}

						@Override
						public void onError(Throwable e) {
							getViewState().showDialog(getStringRes(R.string.dialog_network_error_title), getStringRes(R.string.dialog_network_error_message));
						}

						@Override
						public void onNext(DetectResponse detectResponse) {
							if (detectResponse.getStatus().equals(DetectStatus.OK.name())) {
								//Server can return "OK" status, but language won`t be detect
								if (detectResponse.getLanguage().equals("unknown")) {
									getViewState().showDialog(getStringRes(R.string.error_title_unknown_lang), getStringRes(R.string.error_message_unknown_lang));
								} else {
									saveInHistory(text, detectResponse.getLanguage());
									getViewState().showDialog(getStringRes(R.string.ok_response_title), detectResponse.getLanguage());
								}
							} else if (detectResponse.getStatus().equals(DetectStatus.ERROR.name())) {
								getViewState().showDialog(getStringRes(R.string.error_response_title), getStringRes(R.string.error_response_message));
							}
						}
					});
		}
	}

	public void unsubscribe() {
		if (mSubscription != null) {
			mSubscription.unsubscribe();
		}
	}

	private void saveInHistory(String text, String language) {
		HistoryItem item = new HistoryItem();
		item.setText(text);
		item.setLanguage(language);
		mDatabase.addHistory(item);
	}

	private Observable<DetectResponse> requestDetectLanguage(String text) {
		return RetrofitService.getInstance().createApi().detectLanguage(getStringRes(R.string.api_key), getStringRes(R.string.mode_json), text);
	}

	private String getStringRes(int resId) {
		return mContext.getString(resId);
	}

	private enum DetectStatus {
		OK,
		ERROR
	}
}
