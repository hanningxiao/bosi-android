package com.bosi.chineseclass.activitys;


import android.content.Intent;

import android.view.View;

import com.bosi.chineseclass.BaseActivity;
import com.bosi.chineseclass.R;
import com.bosi.chineseclass.control.HzcsDitalContarol;
import com.bosi.chineseclass.control.SampleHolderControlMake;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_layout_hzcs)
public class HzcsActivity extends BaseActivity{
	//汉字起源 趣味汉字 造字方法 汉字演变
    //造字方法
	@OnClick(R.id.activity_hzcs_iv_zzff)
	public void actionZzff(View mView){
		
	}
	//汉字起源	
	@OnClick(R.id.activity_hzcs_iv_hzqy)
	public void actionHzqy(View mView){
		Intent mIntent = new Intent(this,SampleHolderActivity.class);
		mIntent.putExtra(SampleHolderControlMake.mControlName, "HzcsDitalContarol");
		mIntent.putExtra(HzcsDitalContarol.KEY_HZCSFUNCNAME, "HzqyDitalFragment");
		startActivity(mIntent);
	}
	//汉字演变
	public void actionHzyb(){
		
	}
	//趣味汉字
	public void actionQwhz(){
		
	}



}