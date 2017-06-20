package com.softomate.test.languagerecognizer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.presenter.RecognizerPresenter;
import com.softomate.test.languagerecognizer.view.RecognizerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecognizerFragment extends MvpAppCompatFragment implements RecognizerView, View.OnClickListener {

	@InjectPresenter
	RecognizerPresenter mPresenter;

	@BindView(R.id.fragment_recognizer_edit_text_input_text)
	EditText mEditText;

	@BindView(R.id.fragment_recognizer_floating_button)
	FloatingActionButton mFloatingActionButton;

	@BindView(R.id.fragment_recognizer_progress_bar)
	ProgressBar mProgressBar;

	private String mLastText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recognizer, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mFloatingActionButton.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mLastText != null) {
			mEditText.setText(mLastText);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		mLastText = mEditText.getText().toString();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.fragment_recognizer_floating_button:
				mPresenter.detect(mEditText.getText().toString());
				break;
		}
	}

	@Override
	public void showDialog(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public void showProgress() {
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		mProgressBar.setVisibility(View.INVISIBLE);
	}
}

