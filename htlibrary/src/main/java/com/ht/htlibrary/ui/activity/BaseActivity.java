package com.ht.htlibrary.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public abstract class BaseActivity extends SupportActivity {

	private Toolbar mToolbar;

	private Toast mToast;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		initParams(bundle);
		if(getLayout() == 0){
			throw new RuntimeException("layoutResID == -1 have u create your layout?");
		}
		setContentView(getLayout());
		initView();
		initData();
	}

	protected abstract void initParams(Bundle bundle);

	protected abstract int getLayout();

	protected void initView(){

		mToolbar = (Toolbar) findViewById(getToolBarId());
		if(mToolbar != null){
			setSupportActionBar(mToolbar);
		}
	}

	protected abstract void initData();

	protected abstract int getToolBarId();

	//statusbar , toast,
	protected void setStatusBarColor(int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(color);
		}
	}

	protected void showSuccessToast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toasty.success(this, msg);
		mToast.show();
	}

	protected void showErrorToast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toasty.error(this, msg);
		mToast.show();
	}

	protected void showNormalToast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toasty.normal(this, msg);
		mToast.show();
	}

	/**
	 * 显示返回按钮
	 */
	public void showBackButton(Drawable drawable, @Nullable View.OnClickListener listener) {
		if (mToolbar != null) {
			mToolbar.setNavigationIcon(drawable);
			if (listener != null) {
				mToolbar.setNavigationOnClickListener(listener);
			} else {
				mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();

					}
				});
			}

		}
	}

	/**
	 * 添加菜单按钮
	 * @param menu
	 * @param itemId
	 * @param title
	 * @param iconRes
	 * @return
	 */
	protected MenuItem addMenu(Menu menu, int itemId, String title, int iconRes) {
		return menu.add(Menu.NONE, itemId, Menu.NONE, title).setIcon(iconRes)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
	}







}
