package com.bosi.chineseclass.fragments;


import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bosi.chineseclass.BaseFragment;
import com.bosi.chineseclass.R;
import com.bosi.chineseclass.bean.BphzBean;
import com.bosi.chineseclass.db.BPHZ;
import com.bosi.chineseclass.han.components.HeadLayoutComponents;
import com.bosi.chineseclass.han.util.PreferencesUtils;
import com.bosi.chineseclass.model.BphzLevAdapter;
import com.bosi.chineseclass.model.BphzLevvAdapter;
import com.bosi.chineseclass.views.BSGridView;
import com.lidroid.xutils.view.annotation.ViewInject;
//因为sampleHolder 的control 不具备接收数据的能力  采用本地shar 去读取配置值
//因为继承无法直接读取到父类的 注解 因此 这个还是个问题  不能直接集成到 lv上去做 ，这一块我还没有想好怎么去重构
public class BphzLevvFragment extends BaseFragment{

	@ViewInject(R.id.ll_bphz_body)
	LinearLayout mLayoutBody;
	
	BSGridView mGridView;
	@ViewInject(R.id.headactionbar)
	View mViewHead;
	
	List<BphzBean> mAdapterDataList = new ArrayList<BphzBean>();
	
	
	@ViewInject(R.id.bphz_remote_body)
	LinearLayout mLayoutRemoteBody;
	@Override
	protected View getBasedView() {
		return View.inflate(mActivity, R.layout.layout_bphz, null);
	}

	BphzLevAdapter mBphzLevAdapter ;
	
	int mCurrentXY;
	@Override
	protected void afterViewInject() {
		mCurrentXY = PreferencesUtils.getInt(mActivity, "position");
		HeadLayoutComponents mHead = new HeadLayoutComponents(mActivity, mViewHead);
		mHead.setTextMiddle("爆破汉字", -1);
		mGridView = new BSGridView(mActivity);
		mGridView.setNumColumns(4);
		mGridView.setVerticalSpacing(20);
		mGridView.setCacheColorHint(0);
		mLayoutBody.addView(mGridView);
		
	    mBphzLevAdapter = new BphzLevvAdapter(mActivity, mAdapterDataList);
	    mGridView.setAdapter(mBphzLevAdapter);
	    mLayoutRemoteBody.setVisibility(View.VISIBLE);
	   
	    initRemoteView();
	    getDataAsy();
	}
	
	
//	模拟一次进度
	private void getDataAsy(){
		mActivity.updateProgress(1, 2);
		
		AsyTaskBaseThread(new Runnable() {
			
			@Override
			public void run() {
				mAdapterDataList = getLists();
			}
		},new Runnable() {
			
			@Override
			public void run() {
				mActivity.runOnUiThread( new Runnable() {
					
					@Override
					public void run() {
						mActivity.updateProgress(2, 2);
						updateUI();
					}
				});
			}
		});
	}
	
	private void updateUI(){
		mBphzLevAdapter.changeDataSource(mAdapterDataList);
	}
	
	
	BPHZ mbphz = new BPHZ();
	private void initRemoteView(){
		//delete_bphz_basedictindexbetween
		
		View mViewRemote = View.inflate(mActivity, R.layout.bphz_levv_remote, null);
		
		Button mButtonClear = (Button) mViewRemote.findViewById(R.id.bt_bphzlvv_clear_logdata);
		mButtonClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// 弹出一个提示框 用于确认是否删除数据
				int mCurrentStartSize = mCurrentXY*500+1 ;
				int mCurrentEndSize = mCurrentXY*500+500 ;
	            String mdeleteSql = getResources().getString(R.string.delete_bphz_basedictindexbetween);
	            String format = String.format(mdeleteSql, mCurrentStartSize+"",mCurrentEndSize+"");
	            
	            mActivity.showToastShort("准备清除数据了"+mCurrentStartSize+"-"+mCurrentEndSize);
	           // mbphz.deleteDataFromDb(format);
			}
		});
		
		 mLayoutRemoteBody.addView(mViewRemote);
	}
	
	
	//放到异步任务中去做
	private List<BphzBean> getLists(){
		BPHZ mBphz = new BPHZ();
		List<BphzBean> mLists = new ArrayList<BphzBean>();
		int mCurrentBegan = mCurrentXY*500;
		for(int i = 1 ; i < 11 ;i++){
			BphzBean  mBpHzBean = new BphzBean();
			mBpHzBean.mDictIndex = i-1;
			String startSize = (mBpHzBean.mDictIndex*50+1+mCurrentBegan)+"";
			String endSize = (i*50 +mCurrentBegan)+"";
			mBpHzBean.mNumberBetween = startSize+"-"+endSize;
			
			String sqlSelectBphzLvStastic =  getResources().getString(R.string.select_bphz_lev1data);
			String sqlFormat = String.format(sqlSelectBphzLvStastic, "0","1",startSize,endSize);
			mBphz.getListBpHzBeans(sqlFormat,mBpHzBean);
			mLists.add(mBpHzBean);
		}
		
		return mLists;
	}

}