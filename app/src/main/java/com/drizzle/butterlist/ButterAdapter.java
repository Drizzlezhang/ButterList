package com.drizzle.butterlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;

/**
 * Created by drizzle on 16/5/28.
 */
public class ButterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private List<ButterItem> mButterItemList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public ButterAdapter(List<ButterItem> butterItemList, Context context) {
		this.mContext = context;
		this.mButterItemList = butterItemList;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override public int getItemCount() {
		return mButterItemList == null ? 0 : mButterItemList.size();
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		OneOneViewHolder oneOneViewHolder = (OneOneViewHolder) holder;
		ButterItem item = mButterItemList.get(position);
		oneOneViewHolder.userView.setText(item.getUserName());
	}

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new OneOneViewHolder(mLayoutInflater.inflate(R.layout.item_one_one, parent, false));
	}

	static class OneOneViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.one_one_user) TextView userView;
		@Bind(R.id.one_one_img) OneOneImageView imgView;

		public OneOneViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
