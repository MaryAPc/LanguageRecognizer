package com.softomate.test.languagerecognizer.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TranslateResponse {

	@SerializedName("text")
	private List<String> mText;

	public List<String> getText() {
		return mText;
	}
}
