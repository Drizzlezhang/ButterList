package com.drizzle.butterlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
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

	@Override public int getItemViewType(int position) {
		return mButterItemList.get(position).getImageType();
	}

	@Override public int getItemCount() {
		return mButterItemList == null ? 0 : mButterItemList.size();
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		ButterItem item = mButterItemList.get(position);
		switch (getItemViewType(position)) {
			case ButterItem.TYPE_ONE_ONE:
				OneOneViewHolder oneOneViewHolder = (OneOneViewHolder) holder;
				oneOneViewHolder.userView.setText(item.getUserName());
				setUrlForImageView(item.getImageUrl(), oneOneViewHolder.imgView);
				break;
			case ButterItem.TYPE_FOUR_THREE:
				FourThreeViewHolder fourThreeViewHolder = (FourThreeViewHolder) holder;
				fourThreeViewHolder.userView.setText(item.getUserName());
				setUrlForImageView(item.getImageUrl(), fourThreeViewHolder.imgView);
				break;
			case ButterItem.TYPE_SIXTEEN_NINE:
				SixteenNineViewHolder sixteenNineViewHolder = (SixteenNineViewHolder) holder;
				sixteenNineViewHolder.userView.setText(item.getUserName());
				setUrlForImageView(item.getImageUrl(), sixteenNineViewHolder.imgView);
				break;
		}
	}

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		switch (viewType) {
			case ButterItem.TYPE_ONE_ONE:
				return new OneOneViewHolder(mLayoutInflater.inflate(R.layout.item_one_one, parent, false));
			case ButterItem.TYPE_FOUR_THREE:
				return new FourThreeViewHolder(mLayoutInflater.inflate(R.layout.item_four_three, parent, false));
			case ButterItem.TYPE_SIXTEEN_NINE:
				return new SixteenNineViewHolder(mLayoutInflater.inflate(R.layout.item_sixteen_nine, parent, false));
		}
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

	static class FourThreeViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.four_three_user) TextView userView;
		@Bind(R.id.four_three_img) FourThreeImageView imgView;

		public FourThreeViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	static class SixteenNineViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.sixteen_nine_user) TextView userView;
		@Bind(R.id.sixteen_nine_img) FourThreeImageView imgView;

		public SixteenNineViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	private void setUrlForImageView(String url, ImageView imageView) {
		Glide.with(mContext).load(url).centerCrop().error(R.mipmap.ic_launcher).crossFade().into(imageView);
	}
}
