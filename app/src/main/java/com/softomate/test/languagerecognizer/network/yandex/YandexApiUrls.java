package com.softomate.test.languagerecognizer.network.yandex;

public class YandexApiUrls {

	static final String BASE_URL = "https://translate.yandex.net";
	static final String API_VERSION = BASE_URL + "/api/v1.5";
	static final String RESPONSE_TYPE = API_VERSION + "/tr.json";

	public static class Methods {

		static final String TRANSLATE = RESPONSE_TYPE + "/translate";
	}
}
