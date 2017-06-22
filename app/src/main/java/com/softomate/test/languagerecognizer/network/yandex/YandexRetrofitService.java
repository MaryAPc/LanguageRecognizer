package com.softomate.test.languagerecognizer.network.yandex;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YandexRetrofitService {

	private static YandexRetrofitService sRetrofitService;
	private final Retrofit mRetrofit;

	public static YandexRetrofitService getInstance() {
		if (sRetrofitService == null) {
			sRetrofitService = new YandexRetrofitService();
		}
		return sRetrofitService;
	}

	private YandexRetrofitService() {
		mRetrofit = new Retrofit.Builder()
				.baseUrl(YandexApiUrls.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
	}

	public YandexApiInterface createApi() {
		return mRetrofit.create(YandexApiInterface.class);
	}
}

