package com.drizzle.butterlist;

/**
 * Created by drizzle on 16/5/28.
 */
public class ButterItem {

	private String imageUrl;
	private int imageType;
	private String userName;

	public static final int TYPE_ONE_ONE = 0;//宽高比例1:1的图片类型
	public static final int TYPE_FOUR_THREE = 1;//宽高比例4:3的图片类型
	public static final int TYPE_SIXTEEN_NINE = 2;//宽高比例16:9的图片类型

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getImageType() {
		return imageType;
	}

	public void setImageType(int imageType) {
		this.imageType = imageType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
