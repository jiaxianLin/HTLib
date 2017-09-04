package com.ht.htlib.template;

import android.os.Bundle;

import com.ht.htlibrary.ui.activity.BaseTempLateActivity;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class DemoTemplateActivity extends BaseTempLateActivity {

	@Override
	public String getTemplateFileName() {
		return "demo_template.xml";
	}

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected int getToolBarId() {
		return 0;
	}
}
