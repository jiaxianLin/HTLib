package com.ht.htlibrary.template;

import com.ht.htlibrary.R;

/**
 * Created by rinkousen on 2017/9/2 0002.
 * 用于配置Template的样式，没有配置则为默认样式。
 * 可选择性的设置自己所需要用到的
 */

public class Template {

	private static int INPUT_LAYOUT = R.layout.template_input;
	private static int SECTION_LAYOUT;

	private static Template instance = new Template();

	private Template() {
	}

	public static Template getInstance(){
		return instance;
	}

	/**
	 * 设置inputTemplate的布局样式。
	 * @param resId 布局id
	 * @param tvId 标签资源id
	 * @param etId 输入框资源id
	 * @return
	 */
	public Template setInputLayout(int resId, int tvId, int etId){
		this.INPUT_LAYOUT = resId;
		return instance;
	}

	public static int getInputLayout() {
		return INPUT_LAYOUT;
	}

}
