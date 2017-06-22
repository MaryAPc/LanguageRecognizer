package com.softomate.test.languagerecognizer.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.softomate.test.languagerecognizer.MyApplication;
import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.database.HistoryDb;
import com.softomate.test.languagerecognizer.model.DetectResponse;
import com.softomate.test.languagerecognizer.model.HistoryItem;
import com.softomate.test.languagerecognizer.model.TranslateResponse;
import com.softomate.test.languagerecognizer.network.watson.WatsonRetrofitService;
import com.softomate.test.languagerecognizer.network.yandex.YandexRetrofitService;
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
	private Subscription mDetectSubscription;
	private Subscription mTranslateSubscription;

	private final static int FIRST_ARRAY_TEXT = 0;

	public void detect(String text) {
		if (text.length() == 0) {
			getViewState().showDialog(getStringRes(R.string.error_title), getStringRes(R.string.error_message_empty_text));
		} else {
			getViewState().showProgress();
			mDetectSubscription = requestDetectLanguage(text)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(detectResponse -> {
						if (detectResponse.getStatus().equals(DetectStatus.OK.name())) {
							//Server can return "OK" status, but language won`t be detect
							if (detectResponse.getLanguage().equals("unknown")) {
								getViewState().showDialog(getStringRes(R.string.error_title_unknown_lang), getStringRes(R.string.error_message_unknown_lang));
								getViewState().hideProgress();
							} else {
								translate(text, detectResponse.getLanguage());
							}
						} else if (detectResponse.getStatus().equals(DetectStatus.ERROR.name())) {
							getViewState().showDialog(getStringRes(R.string.error_response_title), getStringRes(R.string.error_response_message));
							getViewState().hideProgress();
						}
					}, throwable -> {
						getViewState().showDialog(getStringRes(R.string.dialog_network_error_title), getStringRes(R.string.dialog_network_error_message));
						getViewState().hideProgress();
					});
		}
	}

	private void translate(String text, String initLang) {
		mTranslateSubscription = requestTranslate(initLang)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.map(TranslateResponse::getText)
				.map(strings -> strings.get(FIRST_ARRAY_TEXT))
				.map(this::firstUpperCase)
				.subscribe(new Subscriber<String>() {
					@Override
					public void onCompleted() {
						getViewState().hideProgress();
					}

					@Override
					public void onError(Throwable e) {
						getViewState().showDialog(getStringRes(R.string.dialog_network_error_title), getStringRes(R.string.dialog_network_error_message));
						getViewState().hideProgress();
					}

					@Override
					public void onNext(String translatedLang) {
						saveInHistory(text, translatedLang);
						getViewState().showDialog(getStringRes(R.string.ok_response_title), translatedLang);
					}
				});
	}

	private String firstUpperCase(String word) {
		if (word == null || word.isEmpty()) {
			return "";
		}
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}

	public void unsubscribe() {
		if (mDetectSubscription != null) {
			mDetectSubscription.unsubscribe();
		}
		if (mTranslateSubscription != null) {
			mTranslateSubscription.unsubscribe();
		}
	}

	private void saveInHistory(String text, String language) {
		HistoryItem item = new HistoryItem();
		item.setText(text);
		item.setLanguage(language);
		mDatabase.addHistory(item);
	}

	private Observable<DetectResponse> requestDetectLanguage(String text) {
		return WatsonRetrofitService.getInstance().createApi().detectLanguage(getStringRes(R.string.watson_api_key), getStringRes(R.string.mode_json), text);
	}

	private Observable<TranslateResponse> requestTranslate(String initText) {
		return YandexRetrofitService.getInstance().createApi().translateText(getStringRes(R.string.yandex_api_key), getStringRes(R.string.translate_language), initText);
	}

	private String getStringRes(int resId) {
		return mContext.getString(resId);
	}

	private enum DetectStatus {
		OK,
		ERROR
	}
}
