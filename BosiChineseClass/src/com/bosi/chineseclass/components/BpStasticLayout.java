package com.bosi.chineseclass.components;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bosi.chineseclass.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
//爆破统计相关组件 用于统计当前学习的信息 是否已经记住
public class BpStasticLayout {
	
	private View mViewBase;
	@ViewInject(R.id.tv_bp_statistic_totalnum)
	private TextView mTvNumber;
	@ViewInject(R.id.bt_bphz_statistic_renumber)
	private Button mBtRember;
	@ViewInject(R.id.bt_bphz_statistic_unrenumber)
	private Button mBtUnRember;
	@ViewInject(R.id.bt_bphz_statistic_next)
	private Button mNext;

	public interface OnBpStasticListener {
		public void onRemberListener();

		public void onUnRemberListener();

		public void onNextListener();
	}

	Context mContext;
	
	OnBpStasticListener mOnBpStasticListener;

	public void setCallback(OnBpStasticListener mOnBpStasticListener) {
		this.mOnBpStasticListener = mOnBpStasticListener;
	}

	public BpStasticLayout(Context mContext) {
		this.mContext = mContext;
		
	    mViewBase = makeView();
		
		ViewUtils.inject(mViewBase);
	}
	
	public View getBaseView(){
		return mViewBase;
	}

	public View makeView() {
		return View.inflate(mContext, R.layout.layout_bp_statistics, null);
	}

}
