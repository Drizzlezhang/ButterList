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

	private String url = "xxx";
	private String moreDataUrl = "xxx";

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
						loadMoreData();
					}
				}
			}
		});
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mButton.setVisibility(View.GONE);
				loadData();
			}
		});
		mRefreshLayout.setOnRefreshListener(this);
	}

	@Override public void onRefresh() {
		loadData();
	}

	/**
	 * 第一次加载或者刷新数据
	 */
	private void loadData() {
		if (!mRefreshLayout.isRefreshing()) {
			swipeRefresh(true);
		}
		mButterItemList.clear();
		new Thread(new Runnable() {
			@Override public void run() {
				HttpHelper.execute(url, new HttpHelper.CallBack() {
					@Override public void onResponse(String response) {
						mButterItemList.addAll(convertStringToList(response));
						mButterAdapter.notifyDataSetChanged();
						swipeRefresh(false);
					}

					@Override public void onError(String errorMessage) {
						swipeRefresh(false);
					}
				});
			}
		}).start();

		OkHttpHelper.get(url, new OkHttpHelper.CallBack() {
			@Override public void onResponse(String response) {
				mButterItemList.addAll(convertStringToList(response));
				mButterAdapter.notifyDataSetChanged();
				swipeRefresh(false);
			}

			@Override public void onError(String errorMessage) {
				swipeRefresh(false);
			}
		});
	}

	/**
	 * 加载更多数据
	 */
	private void loadMoreData() {
		if (!mRefreshLayout.isRefreshing()) {
			swipeRefresh(true);
		}
		new Thread(new Runnable() {
			@Override public void run() {
				HttpHelper.execute(moreDataUrl, new HttpHelper.CallBack() {
					@Override public void onResponse(String response) {
						mButterItemList.addAll(convertStringToList(response));
						mButterAdapter.notifyDataSetChanged();
						swipeRefresh(false);
					}

					@Override public void onError(String errorMessage) {
						swipeRefresh(false);
					}
				});
			}
		}).start();

		OkHttpHelper.get(moreDataUrl, new OkHttpHelper.CallBack() {
			@Override public void onResponse(String response) {
				mButterItemList.addAll(convertStringToList(response));
				mButterAdapter.notifyDataSetChanged();
				swipeRefresh(false);
			}

			@Override public void onError(String errorMessage) {
				swipeRefresh(false);
			}
		});
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

	private List<ButterItem> convertStringToList(String response) {
		//将请求返回的数据转换成列表项List
		//没有找到区分照片宽高比例的字段,只能写了一个模拟的方法
		return null;
	}
}
