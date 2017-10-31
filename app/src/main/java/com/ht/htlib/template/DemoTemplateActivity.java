package com.ht.htlib.template;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ht.htlib.R;
import com.ht.htlibrary.ui.activity.BaseTempLateActivity;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class DemoTemplateActivity extends BaseTempLateActivity implements View.OnClickListener {

	Button btn_commit;

	@Override
	protected int getLayout() {
		return R.layout.activity_demo_template;
	}

	@Override
	public String getTemplateFileName() {
		return "demo_template.xml";
	}

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected void initData() {
		super.initData();
		btn_commit = (Button) findViewById(R.id.btn_commit);
		btn_commit.setOnClickListener(this);

	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	@Override
	protected void doOnNext(Object o) {

	}

	@Override
	public void onClick(View v) {
		mAdapter.notifyDataSetChanged();
		mAdapter.getValueMap();
	}
}
