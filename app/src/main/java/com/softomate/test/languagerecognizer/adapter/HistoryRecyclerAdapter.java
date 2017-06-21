package com.softomate.test.languagerecognizer.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.adapter.holder.HistoryViewHolder;
import com.softomate.test.languagerecognizer.model.HistoryItem;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

	private HistoryViewHolder mViewHolder;
	private List<HistoryItem> mHistoryList = new ArrayList<>();

	public HistoryRecyclerAdapter(List<HistoryItem> historyItems) {
		mHistoryList = historyItems;
	}

	@Override
	public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
		mViewHolder = new HistoryViewHolder(view);
		return mViewHolder;
	}

	@Override
	public void onBindViewHolder(HistoryViewHolder holder, int position) {
		holder.bind(mHistoryList.get(position));
	}

	@Override
	public int getItemCount() {
		return mHistoryList.size();
	}

	public void setCollection(@Nullable List<HistoryItem> history) {
		if (history == null) {
			mHistoryList = Collections.emptyList();
		} else {
			mHistoryList = history;
		}
		notifyDataSetChanged();
	}
}
