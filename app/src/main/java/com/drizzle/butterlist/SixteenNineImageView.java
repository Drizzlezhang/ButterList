package com.drizzle.butterlist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by drizzle on 16/5/29.
 *
 * 宽高比例16:9的ImageView
 */
public class SixteenNineImageView extends ImageView {

	public SixteenNineImageView(Context context) {
		super(context);
	}

	public SixteenNineImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SixteenNineImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override protected void onMeasure(int widthSpec, int heightSpec) {
		int sixteenNineHeight =
			MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthSpec) * 9 / 16, MeasureSpec.EXACTLY);
		super.onMeasure(widthSpec, sixteenNineHeight);
	}
}
