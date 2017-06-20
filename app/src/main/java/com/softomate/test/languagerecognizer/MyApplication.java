package com.softomate.test.languagerecognizer;

import com.arellomobile.mvp.MvpApplication;

public class MyApplication extends MvpApplication {

	private static MyApplication sInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
	}

	public static MyApplication getInstance() {
		return sInstance;
	}
}
