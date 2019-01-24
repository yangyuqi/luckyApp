/**
 * 欢迎页面
 */
package com.yunqilai.delivery.ui.activity.common;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.activity.MainActivity;
import com.yunqilai.delivery.utils.Constants;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends BaseActivity {
	
	private ViewPager mViewPager;
	
	private ImageView[] imageViews;
	private TypedArray mPictures;

	private String accesstoken ;


	//指示器
	private LinearLayout indicatorLayout;
	//indicator ImageView的集合
	private List<ImageView> listIndicatiors;
	private int currentPosition = 0;
	private boolean isFristUser = false ;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		this.setContentView(R.layout.activity_splash);
		getWindow().setBackgroundDrawable(null);


		isFristUser = (boolean) SharedPreferencesUtil.get(context,SharedPreferencesUtil.isFristUse,true);
		accesstoken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
		if (isFristUser) {
			initView();
			initAction();
			SharedPreferencesUtil.put(context,SharedPreferencesUtil.isFristUse,false);
		}else if (!accesstoken.equals("")){
			startActivity(new Intent(context,MainActivity.class));
			finish();
		}else {
			Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
			SplashActivity.this.startActivity(intent);
			SplashActivity.this.finish();
		}
	}
	
	private void initView() {
		mViewPager = (ViewPager)findViewById(R.id.splash_viewpager);
		indicatorLayout = (LinearLayout)findViewById(R.id.layout_indicator);
	}
	
	private void initAction() {
		initViewPager();
	}
	
	private void initViewPager() {
		mPictures = this.getResources().obtainTypedArray(R.array.array_splash_pictures);
		imageViews = new ImageView[mPictures.length()];
		mViewPager.removeAllViews();
		for (int i = 0; i < imageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageResource(mPictures.getResourceId(i, 0));
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			imageViews[i] = imageView;
		}
		mPictures.recycle();
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);
        
		mViewPager.setCurrentItem(currentPosition);

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				currentPosition = position;
				setIndicatorSelect();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		initIndicator(imageViews.length);
	}

	/**
	 * 设置小点
	 */
	private void initIndicator(int size) {
		listIndicatiors = new ArrayList<>();
		indicatorLayout.removeAllViews();
		if(size>1){
			for (int i = 0; i < size; i++) {
				ImageView imageView = new ImageView(this);
				imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
				imageView.setPadding(getResources().getDimensionPixelOffset(R.dimen.splash_point_padding),0,
						getResources().getDimensionPixelOffset(R.dimen.splash_point_padding),0);
				if(i == (size-1)){
					imageView.setImageResource(R.drawable.btn_arrow_up);
				}else{
					imageView.setImageResource(R.drawable.btn_arrow_right);
				}
				if (i == currentPosition) {
					imageView.setSelected(true);
				} else {
					imageView.setSelected(false);
				}
				listIndicatiors.add(imageView);
				indicatorLayout.addView(imageView);
			}
		}
	}

	public void setIndicatorSelect() {
		for (int i = 0; i < listIndicatiors.size(); i++) {
			if (currentPosition == i) {
				listIndicatiors.get(i).setSelected(true);
			} else {
				listIndicatiors.get(i).setSelected(false);
			}
		}
	}
	
	private class ViewPagerAdapter extends PagerAdapter {

		// 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
		@Override
		public int getCount() {
			return imageViews.length;
		}

		// 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			view.removeView(imageViews[position]);
		}

		// 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			if(position == imageViews.length-1){
				RelativeLayout endPage = (RelativeLayout) LayoutInflater.from(SplashActivity.this).inflate(R.layout.activity_splash_end_page, null);
				ImageView mEnterBtn = (ImageView)endPage.findViewById(R.id.btn_end_page_enter);
				mEnterBtn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
			            SplashActivity.this.startActivity(intent);
			            SplashActivity.this.finish();
					}
				});
				view.addView(endPage, 0);
				return endPage;
			}else{
				view.addView(imageViews[position], 0);
				return imageViews[position];
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe
	public void onEvent(Event event){
		if (event == Event.EXITANDLOGIN) {
			finish();
		}
	}
}
