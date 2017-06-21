package com.softomate.test.languagerecognizer.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.adapter.HistoryRecyclerAdapter;
import com.softomate.test.languagerecognizer.model.HistoryItem;
import com.softomate.test.languagerecognizer.presenter.HistoryPresenter;
import com.softomate.test.languagerecognizer.view.HistoryView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView {

	@InjectPresenter
	HistoryPresenter mPresenter;

	@BindView(R.id.fragment_history_recycler_view)
	RecyclerView mRecyclerView;

	@BindView(R.id.fragment_history_text_view_empty_history)
	TextView mEmptyHistoryTextView;

	private HistoryRecyclerAdapter mAdapter;
	private List<HistoryItem> mHistoryList = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new HistoryRecyclerAdapter(mHistoryList);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_history, container, false);
		ButterKnife.bind(this, view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setAdapter(mAdapter);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mPresenter.requestDatabase();
	}

	@Override
	public void setList(List<HistoryItem> history) {
		if (history.size() == 0) {
			mEmptyHistoryTextView.setVisibility(View.VISIBLE);
		} else {
			mEmptyHistoryTextView.setVisibility(View.GONE);
			mAdapter.setCollection(history);
		}
	}
}
