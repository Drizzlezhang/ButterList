package com.drizzle.butterlist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by drizzle on 16/5/28.
 *
 * 宽高比例1:1的ImageView
 */
public class OneOneImageView extends ImageView {
	public OneOneImageView(Context context) {
		super(context);
	}

	public OneOneImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public OneOneImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override protected void onMeasure(int widthSpec, int heightSpec) {
		int oneOneHeight = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthSpec), MeasureSpec.EXACTLY);
		super.onMeasure(widthSpec, oneOneHeight);
	}
}
