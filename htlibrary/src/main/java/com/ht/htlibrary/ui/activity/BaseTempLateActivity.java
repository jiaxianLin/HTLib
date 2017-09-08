package com.ht.htlibrary.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ht.htlibrary.R;
import com.ht.htlibrary.template.TemplateAdapter;
import com.ht.htlibrary.template.TemplateUtil;
import com.ht.htlibrary.template.bean.TemplateList;

/**
 * Created by Administrator on 2017/9/4 0004.
 * 模板配置基类
 */

public abstract class BaseTempLateActivity extends BaseActivity {

	protected TemplateAdapter mAdapter;

	RecyclerView mRecyclerView;

	TemplateList templates;

	@Override
	protected int getLayout() {
		return R.layout.activity_base_template;
	}

	@Override
	protected void initView() {
		super.initView();
		mRecyclerView = (RecyclerView) findViewById(R.id.list_template);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

	}

	@Override
	protected void initData() {
		//获取数据list
		templates = TemplateUtil.parseAssetsFile(this, getTemplateFileName());
		//创建adapter
		try{
			mAdapter = new TemplateAdapter(templates);
		} catch (Exception e){
			e.printStackTrace();
		}

		mRecyclerView.setAdapter(mAdapter);

	}

	/**
	 * 获取配置文件名
	 * @return
	 */
	public abstract String getTemplateFileName();

}
