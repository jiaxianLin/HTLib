package com.ht.htlibrary.ui.activity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ht.htlibrary.base.RxBus;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public abstract class BaseActivity extends SupportActivity {

	private Toolbar mToolbar;

	private Toast mToast;

	protected Subscription subscription;

	protected ProgressDialog mProgressDialog;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		initParams(bundle);
		initRxBus();
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

	protected void initRxBus(){
		subscription = RxBus.toObserverable()
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Object>() {
					@Override
					public void call(Object o) {
						doOnNext(o);
					}
				});
	}

	protected abstract void doOnNext(Object o);

	//statusbar , toast,
	protected void setStatusBarColor(int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(color);
		}
	}

	/**
	 * 成功toast
	 * @param msg
	 */
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

	/**
	 * 显示进度条
	 * @param msg 进度条文字提示
	 */
	protected void showProgressDialog(String msg){
		if(mProgressDialog == null){
			mProgressDialog = new ProgressDialog(this);
		}
		mProgressDialog.setTitle(msg);
		mProgressDialog.show();
	}

	/**
	 * 隐藏进度条
	 */
	protected void dismissProgressDialog(){
		if(mProgressDialog != null){
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 页面销毁时取消订阅
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(subscription != null){
			subscription.unsubscribe();
		}
	}
}
