package com.yunqilai.delivery.ui.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

public class FixedSpeedViewPager extends ViewPager {
	private boolean scrollble = true;

	public FixedSpeedViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FixedSpeedViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (scrollble) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}

	}

	public void setScrollble(boolean scrollble) {
		this.scrollble = scrollble;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (scrollble) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}

	}

	public void setSpeed(int speed) {
		Field field = null;
		try {
			field = ViewPager.class.getDeclaredField("mScroller");
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		field.setAccessible(true);
		FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), new AccelerateInterpolator());
		try {
			field.set(this, scroller);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scroller.setmDuration(speed);
	}

	@Override
	public void setCurrentItem(int item) {
		// TODO Auto-generated method stub
		super.setCurrentItem(item, scrollble);
	}
}
