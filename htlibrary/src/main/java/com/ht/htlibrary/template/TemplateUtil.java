package com.ht.htlibrary.template;

import android.content.Context;

import com.ht.htlibrary.template.bean.BaseTemplate;
import com.ht.htlibrary.template.bean.SectionTemplate;
import com.ht.htlibrary.template.bean.TemplateList;
import com.ht.htlibrary.util.XmlUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rinkousen on 2017/8/14 0014.
 */

public class TemplateUtil {
	/**
	 * 解析xml文件
	 *
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static TemplateList parseAssetsFile(Context context, String fileName) {
		try {
			InputStream inputStream = context.getAssets().open(fileName);
			return parseStream(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static TemplateList parseStream(InputStream inputStream) {

		return parseElement(XmlUtil.getDocumentElement(inputStream));
	}

	/**
	 * 从根节点开始解析文件
	 *
	 * @param rootElement
	 * @return
	 */
	public static TemplateList parseElement(Element rootElement) {
		return parseElement(rootElement, null);
	}

	/**
	 * 从section节点开始解析文件，获得templatelist，如果section节点为null，则表示根节点
	 *
	 * @param rootElement
	 * @param sectionTemplate
	 * @return
	 */
	public static TemplateList parseElement(Element rootElement, SectionTemplate sectionTemplate) {
		TemplateList templates = new TemplateList();

		if (rootElement == null) {
			return templates;
		}
		try {
			NodeList nodeList = rootElement.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (!(node instanceof Element)) {
					continue;
				}

				Element element = (Element) node;

				String tagName = element.getTagName();
				Class<? extends BaseTemplate> templateCls = TemplateConfig.getTemplate(tagName);

				if (templateCls != null) {

					BaseTemplate template = templateCls.newInstance();
					template.initTemplate(element);
					if(sectionTemplate != null){
						template.setSectionTemplate(sectionTemplate);
					}
					templates.add(template);
					if("section".equals(tagName)){
						templates.addAll(parseElement(element, (SectionTemplate) template));
					}
				}
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return templates;
	}
}
