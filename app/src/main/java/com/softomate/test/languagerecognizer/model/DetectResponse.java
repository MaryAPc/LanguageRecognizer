package com.softomate.test.languagerecognizer.model;

import com.google.gson.annotations.SerializedName;

public class DetectResponse {

	@SerializedName("status")
	private String mStatus;

	@SerializedName("language")
	private String mLanguage;

	public String getStatus() {
		return mStatus;
	}

	public String getLanguage() {
		return mLanguage;
	}
}
