package com.softomate.test.languagerecognizer.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

	public void addFragment(Fragment fragment, FragmentManager fragmentManager) {

			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.add(R.id.activity_main_frame_layout_container, fragment);
			fragmentTransaction.commit();
		}

}
