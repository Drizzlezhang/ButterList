package com.drizzle.butterlist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by drizzle on 16/5/29.
 *
 *  * 宽高比例4:3的ImageView
 */
public class FourThreeImageView extends ImageView {

	public FourThreeImageView(Context context) {
		super(context);
	}

	public FourThreeImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FourThreeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override protected void onMeasure(int widthSpec, int heightSpec) {
		int fourThreeHeight = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthSpec) * 3 / 4, MeasureSpec.EXACTLY);
		super.onMeasure(widthSpec, fourThreeHeight);
	}
}
