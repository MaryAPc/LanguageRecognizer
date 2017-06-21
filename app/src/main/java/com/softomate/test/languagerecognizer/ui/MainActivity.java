package com.softomate.test.languagerecognizer.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.presenter.MainPresenter;
import com.softomate.test.languagerecognizer.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

	@InjectPresenter
	MainPresenter mPresenter;

	@BindView(R.id.activity_main_drawer_layout)
	DrawerLayout mDrawerLayout;

	@BindView(R.id.app_bar_main_toolbar)
	Toolbar mToolbar;

	@BindView(R.id.activity_main_nav_view)
	NavigationView mNavigationView;

	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawerLayout.addDrawerListener(toggle);
		toggle.syncState();

		mNavigationView.setNavigationItemSelectedListener(this);
		mFragmentManager = getSupportFragmentManager();
		if (savedInstanceState != null) {
			Fragment fragment = mFragmentManager.findFragmentById(R.id.activity_main_frame_layout_container);
		} else {
			mPresenter.addFragment(new RecognizerFragment(), mFragmentManager);
		}
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_drawer_new_text:
				break;
			case R.id.menu_drawer_history:
				mPresenter.replaceFragment(new HistoryFragment(), mFragmentManager);
				break;
		}
		mDrawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void showFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.show(fragment);
		fragmentTransaction.commit();
	}
}
