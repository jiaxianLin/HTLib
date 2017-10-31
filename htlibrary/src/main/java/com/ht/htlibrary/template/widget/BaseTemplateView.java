package com.ht.htlibrary.template.widget;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ht.htlibrary.R;
import com.ht.htlibrary.template.Template;
import com.ht.htlibrary.template.bean.BaseTemplate;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class BaseTemplateView<T extends BaseTemplate> extends RelativeLayout {

	private String value;

	private Map<String, String> valueMap;

	private T template;

	protected TextView tvLabel;
	protected TextView tvText;

	public BaseTemplateView(Context context) {
		super(context);
		setMinimumHeight(1);
	}

	public int getLayout(){
		return Template.getBaseTemplateLayout();
	}

	@Override
	public void setVisibility(int visibility) {
		// TODO Auto-generated method stub
		super.setVisibility(visibility);

		for (int i = 0; i < getChildCount(); i++) {
			getChildAt(i).setVisibility(visibility);
		}
	}

	public void initView(Map<String, String> valueMap, String value, T template){
		if(getChildCount() == 0){
			inflate(getContext(), getLayout(), this);
		}

		this.valueMap = valueMap;
		this.value = value;
		this.template = template;

		tvLabel = (TextView) findViewById(R.id.template_label);
		tvText = (TextView) findViewById(R.id.template_text);

		if(tvLabel != null){
			tvLabel.setText(template.label);
		}

		if(tvText != null){
			tvText.setText(template.getShowString(value));
		}
	}

	protected OnViewListener onViewListener;

	public void setListener(OnViewListener listener) {
		onViewListener = listener;
	}

	public interface OnViewListener {

		void onViewData(BaseTemplate template, String value);

	}


}
