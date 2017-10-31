package com.ht.htlibrary.template.widget.dialog;

import android.content.Context;

import com.ht.htlibrary.template.TemplateAdapter;
import com.ht.htlibrary.template.bean.BaseTemplate;

import java.util.Map;

/**
 * Created by rinkousen on 2017/9/14 0014.
 */

public abstract class BaseTemplateDialog <T extends BaseTemplate> {
	protected Context mContext;

	protected T template;

	protected Map<String, String> valueMap;

	protected TemplateAdapter mAdapter;

	public BaseTemplateDialog(Context context) {
		mContext = context;
	}

	public abstract void show();

	public static abstract class OnDialogListener {

	}
}
