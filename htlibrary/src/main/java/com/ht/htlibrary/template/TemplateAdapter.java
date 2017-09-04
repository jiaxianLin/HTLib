package com.ht.htlibrary.template;

import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ht.htlibrary.R;
import com.ht.htlibrary.template.bean.BaseTemplate;
import com.ht.htlibrary.template.bean.TemplateList;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class TemplateAdapter extends BaseQuickAdapter<BaseTemplate, BaseViewHolder> {

	TemplateList templateList;

	public TemplateAdapter(@Nullable List<BaseTemplate> data) {
		super(Template.getInputLayout(), data);
		templateList = new TemplateList();

		templateList.addAll(data);


	}


	@Override
	protected void convert(BaseViewHolder helper, BaseTemplate item) {
		TextView tv_label = helper.getView(R.id.template_label);
		EditText et_value = helper.getView(R.id.template_value);

		tv_label.setText(item.label);
		et_value.setText(item.initVlaue);
	}
}
