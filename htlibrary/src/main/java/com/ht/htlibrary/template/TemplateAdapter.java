package com.ht.htlibrary.template;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ht.htlibrary.R;
import com.ht.htlibrary.template.bean.BaseTemplate;
import com.ht.htlibrary.template.bean.SectionTemplate;
import com.ht.htlibrary.template.bean.TemplateList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rinkousen on 2017/9/4 0004.
 */

public class TemplateAdapter extends BaseQuickAdapter<BaseTemplate, BaseViewHolder> {

	TemplateList templateList;

	public TemplateAdapter(@Nullable List<BaseTemplate> data) {
		super(Template.getInputLayout(), data);
		templateList = new TemplateList();

		templateList.addAll(data);

	}

	public TemplateList getTemplateList() {
		return templateList;
	}

	@Override
	protected void convert(BaseViewHolder helper, BaseTemplate item) {
		TextView tv_label = helper.getView(R.id.template_label);
		EditText et_value = helper.getView(R.id.template_value);

		tv_label.setText(item.label);
		et_value.setText(item.initVlaue);

		if(!TextUtils.isEmpty(item.initVlaue)){
			et_value.setText(item.initVlaue);
		}
		if(item instanceof SectionTemplate){
			et_value.setVisibility(View.GONE);
			helper.itemView.setVisibility(View.GONE);
		} else {
			et_value.setVisibility(View.VISIBLE);
			helper.itemView.setVisibility(View.VISIBLE);
		}

	}

	public Map<String, String> getValueMap(){
		Map<String, String> map = new HashMap<>();
		for (BaseTemplate template : getData()) {
			map.put(template.name, template.value);
		}
		return map;
	}
}
