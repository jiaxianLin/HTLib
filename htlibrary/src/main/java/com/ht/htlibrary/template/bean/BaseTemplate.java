package com.ht.htlibrary.template.bean;

import android.text.TextUtils;

import com.ht.htlibrary.template.ReflectUtil;
import com.ht.htlibrary.template.TemplateUtil;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by rinkousen on 2017/8/14 0014.
 * 描述：配置模板
 */

public class BaseTemplate {

	/**
	 * 配置项类型
	 * input radio switch select
	 */
	public String type;

	/**
	 * 配置项名称
	 */
	public String name;
	/**
	 * 配置项标签
	 */
	public String label;

	/**
	 * 配置项值
	 */
	public String value;

	/**
	 * 配置项初始值
	 */
	public String initVlaue;
	/**
	 * 显示条件
	 */
	public String show;

	/**
	 * 节点
	 */
	public SectionTemplate sectionTemplate;

	private String showFormat;

	public void initTemplate(Element e) {
		Element element = (Element) e.cloneNode(true);

		Field[] fields = getClass().getFields();
		for (Field field : fields) {
			Attr attr = element.getAttributeNode(field.getName());
			if (attr != null) {
				ReflectUtil.setField(this, field, attr.getValue());
			}
		}
	}

	public SectionTemplate getSectionTemplate() {
		return sectionTemplate;
	}

	public void setSectionTemplate(SectionTemplate sectionTemplate) {
		this.sectionTemplate = sectionTemplate;
	}

	public boolean isShow(Map<String, String> valueMap){
		return TemplateUtil.compute(show, valueMap, true);
	}

	public String getShowString(String value) {
		if (value == null) {
			return "";
		}

		if (TextUtils.isEmpty(showFormat)) {
			return value;
		} else {
			return String.format(showFormat, value);
		}
	}

}
