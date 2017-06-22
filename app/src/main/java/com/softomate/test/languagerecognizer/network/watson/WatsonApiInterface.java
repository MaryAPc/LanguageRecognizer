package com.softomate.test.languagerecognizer.network.watson;

import com.softomate.test.languagerecognizer.model.DetectResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface WatsonApiInterface {

	@POST(WatsonApiUrls.Methods.LANGUAGE_DIRECTION)
	Observable<DetectResponse> detectLanguage(@Query("apikey") String apiKey, @Query("outputMode") String outputMode, @Query("text") String text);
}
