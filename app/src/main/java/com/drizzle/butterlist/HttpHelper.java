package com.drizzle.butterlist;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by drizzle on 16/5/28.
 *
 * 使用HttpUrlConnection进行网络请求
 */
public class HttpHelper {

	public interface CallBack {
		void onResponse(String response);

		void onError(String errorMessage);
	}

	public static void execute(String path, CallBack callBack) {
		InputStream inputStream = null;
		HttpURLConnection urlConnection = null;

		try {
			URL url = new URL(path);
			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			urlConnection.setRequestProperty("Accept", "application/json");

			urlConnection.setRequestMethod("GET");

			int statusCode = urlConnection.getResponseCode();

			if (statusCode == 200) {
				inputStream = new BufferedInputStream(urlConnection.getInputStream());
				String response = convertStreamToString(inputStream);
				callBack.onResponse(response);
			} else {
				callBack.onError("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}

	private static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
