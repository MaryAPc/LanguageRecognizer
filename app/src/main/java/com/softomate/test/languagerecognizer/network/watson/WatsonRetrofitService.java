package com.softomate.test.languagerecognizer.network.watson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WatsonRetrofitService {

	private static WatsonRetrofitService sRetrofitService;
	private final Retrofit mRetrofit;

	public static WatsonRetrofitService getInstance() {
		if (sRetrofitService == null) {
			sRetrofitService = new WatsonRetrofitService();
		}
		return sRetrofitService;
	}

	private WatsonRetrofitService() {
		mRetrofit = new Retrofit.Builder()
				.baseUrl(WatsonApiUrls.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
	}

	public WatsonApiInterface createApi() {
		return mRetrofit.create(WatsonApiInterface.class);
	}
}

