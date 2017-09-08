package com.ht.htlib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.ht.htlib.template.DemoTemplateActivity;
import com.ht.htlibrary.ui.activity.BaseActivity;
import com.ht.htlibrary.util.BarUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

	Button btn_list, btn_fragment, btn_tab, btn_template;

	SeekBar mSeekBar;

//	private ActivityComponent mActivityComponent;
//	@Inject
//	UserModel mUserModel;

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		btn_list = (Button) findViewById(R.id.btn_list);

		btn_fragment = (Button) findViewById(R.id.btn_fragment);

		btn_tab = (Button) findViewById(R.id.btn_tab);

		btn_template = (Button) findViewById(R.id.btn_template);

		btn_list.setOnClickListener(this);
		btn_fragment.setOnClickListener(this);
		btn_tab.setOnClickListener(this);
		btn_template.setOnClickListener(this);
//		mActivityComponent = DaggerActivityComponent.builder()
//				.activityModule(new ActivityModule()).build();
//
//		mActivityComponent.inject(this);
//
//		((TextView) findViewById(R.id.tv_main)).
//				setText("ID:" + mUserModel.getId() + " Name: " + mUserModel.getName()
//						+ "  Gender: " + mUserModel.getGender());

		mSeekBar = (SeekBar) findViewById(R.id.seekbar);

		mSeekBar.setMax(255);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				BarUtils.setStatusBarAlpha(MainActivity.this, progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});


	}

	@Override
	protected void initData() {

	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()){
			case R.id.btn_list:
				intent.setClass(this, DemoListActivity.class);
				break;
			case R.id.btn_fragment:
				intent.setClass(this, DemoFragmentAtivity.class);
				break;
			case R.id.btn_tab:
				intent.setClass(this, DemoTabActivity.class);
				break;
			case R.id.btn_template:
				intent.setClass(this, DemoTemplateActivity.class);
		}
		startActivity(intent);
	}
}
