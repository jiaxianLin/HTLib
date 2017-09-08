package com.ht.htlib;

import com.ht.htlib.net.NetClient;
import com.ht.htlibrary.template.Template;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class App extends com.ht.htlibrary.base.App {

	@Override
	public void onCreate() {
		super.onCreate();
		Template.getInstance().setInputLayout(R.layout.template_input, R.id.template_label, R.id.template_value);
		NetClient.init(Constant.BASEURL);
	}
}
