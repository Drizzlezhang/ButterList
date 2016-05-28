package com.drizzle.butterlist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

	@Bind(R.id.main_refresh) SwipeRefreshLayout mRefreshLayout;
	@Bind(R.id.main_list) RecyclerView mRecyclerView;
	@Bind(R.id.main_btn) Button mButton;

	private ButterAdapter mButterAdapter;
	private List<ButterItem> mButterItemList;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initData();
		initViews();
	}

	private void initData() {
		mButterItemList = new ArrayList<>();
		mButterAdapter = new ButterAdapter(mButterItemList, this);
	}

	private void initViews() {
		final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(mButterAdapter);
		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
					if (linearLayoutManager.findLastCompletelyVisibleItemPosition()
						== linearLayoutManager.getItemCount() - 1) {
						swipeRefresh(true);
						loadMoreData();
					}
				}
			}
		});
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				swipeRefresh(true);
				loadData();
			}
		});
		mRefreshLayout.setOnRefreshListener(this);
	}

	@Override public void onRefresh() {
		loadData();
	}

	private void loadData() {
		mButterItemList.clear();
		mButton.setVisibility(View.GONE);
		for (int i = 0; i < 10; i++) {
			ButterItem item = new ButterItem();
			item.setUserName(i + "");
			mButterItemList.add(item);
		}
		mButterAdapter.notifyDataSetChanged();
		swipeRefresh(false);
	}

	private void loadMoreData() {
		for (int i = 0; i < 10; i++) {
			ButterItem item = new ButterItem();
			item.setUserName(i + "");
			mButterItemList.add(item);
		}
		mButterAdapter.notifyDataSetChanged();
		swipeRefresh(false);
	}

	private void swipeRefresh(final boolean refresh) {
		mRefreshLayout.post(new Runnable() {
			@Override public void run() {
				if (refresh) {
					mRefreshLayout.setRefreshing(true);
				} else {
					mRefreshLayout.setRefreshing(false);
				}
			}
		});
	}
}
