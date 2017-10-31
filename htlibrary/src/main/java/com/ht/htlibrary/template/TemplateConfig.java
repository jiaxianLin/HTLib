package com.ht.htlibrary.template;

import com.ht.htlibrary.template.bean.BaseTemplate;
import com.ht.htlibrary.template.bean.InputTemplate;
import com.ht.htlibrary.template.bean.SectionTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rinkousen on 2017/9/4 0004.
 */

public class TemplateConfig {
	private static final Map<String, Class<? extends BaseTemplate>> TEMPLATE_MAP = new HashMap<>();

	private static final Map<String, Object> configs = new HashMap<>();


	static {
		putTemplate("section", SectionTemplate.class);
		putTemplate("input", InputTemplate.class);

		putSelectDivider("#");
		putSelectDividerShow("，");
	}

	public static Class<? extends BaseTemplate> getTemplate(String xmlTag){
		return TEMPLATE_MAP.get(xmlTag);
	}

	/**
	 * 添加template类型
	 * @param xmlTag
	 * @param cls
	 */
	public static void putTemplate(String xmlTag, Class<? extends BaseTemplate> cls){
		TEMPLATE_MAP.put(xmlTag, cls);
	}

	public static void putSelectDivider(String divider) {
		configs.put("select divider", divider);
	}

	public static String getSelectDivider() {
		return (String) configs.get("select divider");
	}

	public static void putSelectDividerShow(String divider) {
		configs.put("select divider show", divider);
	}

	public static String getSelectDividerShow() {
		return (String) configs.get("select divider show");
	}


}
