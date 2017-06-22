package com.softomate.test.languagerecognizer.network.yandex;

import com.softomate.test.languagerecognizer.model.TranslateResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface YandexApiInterface {

	@POST(YandexApiUrls.Methods.TRANSLATE)
	Observable<TranslateResponse> translateText(@Query("key") String apiKey, @Query("lang") String translateLang, @Query("text") String text);
}
