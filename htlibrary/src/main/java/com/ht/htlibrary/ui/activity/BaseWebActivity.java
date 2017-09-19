package com.ht.htlibrary.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ht.htlibrary.R;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class BaseWebActivity extends BaseActivity {

	private WebView mWebView;
	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected int getLayout() {
		return R.layout.activity_base_web;
	}

	@Override
	protected void initView() {
		super.initView();
		mWebView = (WebView) findViewById(R.id.webview);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	@Override
	protected void doOnNext(Object o) {

	}

	private boolean isUrl(final String url) {
		boolean urlFlag = false;

		final String strTmp = url.substring(0, 4);
		if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(strTmp)) {
			if ("http".equals(strTmp) || "https".equals(strTmp)) {
				urlFlag = true;
			}
		}

		return urlFlag;
	}

	/**
	 * @author zhaojy
	 * @ClassName IWebProgress
	 * @date 2015-12-13
	 * @Description: web页加载状态
	 */
	public interface WebProgressListener {
		public void onProgressComplete();

		public void onProgressStart();
	}

	@SuppressLint("JavascriptInterface")
	protected WebView initWebView(String url, Object classObj, final WebProgressListener listener){
		WebSettings wv_settings = mWebView.getSettings();
		wv_settings.setJavaScriptEnabled(true);
		wv_settings.setDefaultTextEncodingName("UTF-8");
		wv_settings.setDisplayZoomControls(false);
		wv_settings.setBuiltInZoomControls(false);
		wv_settings.setSupportZoom(false);
		wv_settings.setDomStorageEnabled(true);

		// 加载的页面自适应手机屏幕
		wv_settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
//				LoadingDialogUtils.showLoadingDialog(BaseWebActivity.this);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
//				LoadingDialogUtils.cancelLoadingDialog();
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//去WebView打开
				view.loadUrl(url);
				return true;
			}

			//https:验证
			@Override
			public void onReceivedSslError(WebView view,
										   SslErrorHandler handler, SslError error) {
			}

		});

		if (null != listener) {
			mWebView.setWebChromeClient(new WebChromeClient() {
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					if (100 == newProgress) {
						if (null != listener) {
							listener.onProgressComplete();
						}
						// 关闭进度条
					} else {
						if (null != listener) {
							listener.onProgressStart();
						}
						// 加载中显示进度条
					}
				}
			});
		}

		if (null != classObj) {
			mWebView.addJavascriptInterface(classObj, "JsInteface");
		}

		if (!TextUtils.isEmpty(url)) {
			loadWebPage(mWebView, url);
		}

		return mWebView;
	}

	/**
	 * 加载网页
	 */
	private void loadWebPage(WebView webView, final String params) {
		if (this.isUrl(params)) { // url 地址 形式 加载
			//WebView加载web资源
//          final String urlString = WebPage.IP_PATH
//                  + this.webPageIdentify
//                  + this.webPageParams;
//			Log.e(TAG, "url：" + params);
			webView.loadUrl(params);
		} else {// 网页 源码 形式 加载
			webView.loadDataWithBaseURL(null, params, "text/html", "utf-8", null);
		}
	}
}
