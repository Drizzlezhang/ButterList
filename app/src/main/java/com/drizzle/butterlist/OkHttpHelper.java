package com.drizzle.butterlist;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by drizzle on 16/5/28.
 *
 * 使用OkHttp进行请求
 */
public class OkHttpHelper {

	public interface CallBack {
		void onResponse(String response);

		void onError(String errorMessage);
	}

	public static void get(String url, final CallBack callBack) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override public void onFailure(Call call, IOException e) {
				callBack.onError(e.getMessage());
			}

			@Override public void onResponse(Call call, Response response) throws IOException {
				callBack.onResponse(response.body().string());
			}
		});
	}
}
