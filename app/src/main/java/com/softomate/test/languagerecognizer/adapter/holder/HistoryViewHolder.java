package com.softomate.test.languagerecognizer.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.softomate.test.languagerecognizer.R;
import com.softomate.test.languagerecognizer.model.HistoryItem;

import butterknife.BindView;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.item_history_text_view_input_text)
	TextView mInputTextView;

	@BindView(R.id.item_history_text_view_language)
	TextView mLanguageTextView;

	public HistoryViewHolder(View itemView) {
		super(itemView);
	}

	public void bind(HistoryItem model) {
		mInputTextView.setText(model.getText());
		mLanguageTextView.setText(model.getText());
	}
}
