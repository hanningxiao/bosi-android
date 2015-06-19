package com.bosi.chineseclass.fragments.hzcs;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import com.bosi.chineseclass.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

//造字方法
public class ZzffDitalFragment extends AbsHzcsFragment{


	
	private void initDataArray(){
		mDataArrayJsjs = new String[1];
		mDataArrayJsjs[0]="http://www.yuwen100.cn/yuwen100/hzzy/Android/hanziqiyuan/cjzz/cjzz-1.png";
		
		mDataArrayCjzz= new String[2];
		mDataArrayCjzz[0] = "http://www.yuwen100.cn/yuwen100/hzzy/Android/hanziqiyuan/cjzz/cjzz-1.png";
		mDataArrayCjzz[1] = "http://www.yuwen100.cn/yuwen100/hzzy/Android/hanziqiyuan/cjzz/cjzz-2.png";
		
		
		mDataArrayFxbg = new String[1];
		mDataArrayFxbg[0] = "http://www.yuwen100.cn/yuwen100/hzzy/Android/hanziqiyuan/fxbg/fxbg-1.png";
	}
	@OnClick(R.id.bt_hzcs_dital_jsjs)
	public void actionClickJsJs(View mView){
		mCurrentData = mDataArrayJsjs;
		mBtRight.setVisibility(View.GONE);
		mBtLeft.setVisibility(View.GONE);
		currentPosition = 0;
		updateDitalPg();
	}
	
	@OnClick(R.id.bt_hzcs_dital_cjzz)
	public void actionClickCjzz(View mView){
		mCurrentData = mDataArrayCjzz;
		mBtRight.setVisibility(View.VISIBLE);
		mBtLeft.setVisibility(View.GONE);
		currentPosition = 0;
		updateDitalPg();
	}
	
	@OnClick(R.id.bt_hzcs_dital_fxbg)
	public void actionClickFxbg(View mView){
		mCurrentData = mDataArrayFxbg;
		mBtRight.setVisibility(View.GONE);
		mBtLeft.setVisibility(View.GONE);
		currentPosition = 0;
		updateDitalPg();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void initMenu() {
		mImageLoader.getBitmapFactory().display(mIvDital,
				"assets/hzqy/cjzz.png");
		
		View mMenuView = View.inflate(mActivity, R.layout.layout_zzff_menu, null);
		mLayoutMenu.addView(mMenuView);
		ViewUtils.inject(this, mMenuView);
		mBtLeft.setVisibility(View.GONE);
		mBtRight.setVisibility(View.GONE);
	}

	@Override
	protected void afterViewInject() {
		
		super.afterViewInject();
		initDataArray();
		downloadimgs();
	}

	String mDataArrayJsjs [] ;
	String mDataArrayCjzz [];
	String mDataArrayFxbg [];
	
	@Override
	public void initWholeArray() {
		mAllDataArray = new String [mDataArrayJsjs.length+mDataArrayCjzz.length+mDataArrayFxbg.length];
		System.arraycopy(mDataArrayJsjs, 0, mAllDataArray, 0, mDataArrayJsjs.length);
		System.arraycopy(mDataArrayCjzz, 0, mAllDataArray, mDataArrayJsjs.length, mDataArrayCjzz.length);
		System.arraycopy(mDataArrayFxbg, 0, mAllDataArray,  mDataArrayJsjs.length+mDataArrayCjzz.length, mDataArrayFxbg.length);
		
	}

}
