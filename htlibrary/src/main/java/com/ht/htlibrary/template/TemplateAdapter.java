package com.ht.htlibrary.template;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ht.htlibrary.template.bean.BaseTemplate;
import com.ht.htlibrary.template.bean.TemplateList;
import com.ht.htlibrary.template.widget.BaseTemplateView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rinkousen on 2017/9/4 0004.
 */

public class TemplateAdapter extends BaseQuickAdapter<BaseTemplate, BaseViewHolder> {

	TemplateList templateList;

	Map<String, String> valueMap;

	private SparseArray<BaseTemplateView> views;

	public TemplateAdapter(@Nullable TemplateList data) {
		super(Template.getInputLayout(), data);
//		templateList = new TemplateList();

		templateList = data;
		valueMap = new HashMap<>();

		views = new SparseArray<>();


	}

	@Override
	protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
		return super.getItemView(layoutResId, parent);
	}

	@Override
	public int getItemViewType(int position) {
		return position;
	}

	@Nullable
	@Override
	public BaseTemplate getItem(@IntRange(from = 0L) int position) {
		return templateList.get(position);
	}

	public TemplateList getTemplateList() {
		return templateList;
	}

	@Override
	protected void convert(final BaseViewHolder helper, final BaseTemplate item) {

//		if(item instanceof SectionTemplate){
//			et_value.setVisibility(View.GONE);
//			helper.itemView.setVisibility(View.GONE);
//		} else {
//			et_value.setVisibility(View.VISIBLE);
//			helper.itemView.setVisibility(View.VISIBLE);
//		}

		if(item.isShow(getValueMap())){
			helper.itemView.setVisibility(View.VISIBLE);
			helper.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
		} else {
			helper.itemView.setVisibility(View.GONE);
		}

		BaseTemplateView view = (BaseTemplateView) helper.itemView;
		views.put(helper.getAdapterPosition(), view);
		view.initView(valueMap, item.value, item);


//		et_value.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//			}
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//				item.value = s.toString();
//				valueMap.put(templateList.get(helper.getAdapterPosition()).name, s.toString());
//			}
//		});

	}

	public Map<String, String> getValueMap(){
		return valueMap;
	}
}
