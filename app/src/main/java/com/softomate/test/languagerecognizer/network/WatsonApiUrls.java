package com.softomate.test.languagerecognizer.network;

public class WatsonApiUrls {

	static final String BASE_URL = "https://gateway-a.watsonplatform.net";
	static final String CALL = BASE_URL + "/calls";
	static final String INPUT = CALL + "/text";

	public static class Methods {

		static final String LANGUAGE_DIRECTION = INPUT + "/TextGetLanguage";
	}
}
