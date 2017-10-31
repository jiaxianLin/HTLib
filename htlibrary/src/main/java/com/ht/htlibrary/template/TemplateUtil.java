package com.ht.htlibrary.template;

import android.content.Context;
import android.text.TextUtils;

import com.ht.htlibrary.template.bean.BaseTemplate;
import com.ht.htlibrary.template.bean.SectionTemplate;
import com.ht.htlibrary.template.bean.TemplateList;
import com.ht.htlibrary.util.StringUtils;
import com.ht.htlibrary.util.XmlUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

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
					if (sectionTemplate != null) {
						template.setSectionTemplate(sectionTemplate);
					}
					templates.add(template);
					if ("section".equals(tagName)) {
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

	private static class Item {
		private static final int OPERATOR = 0;
		private static final int OPERATOR_SINGLE = 1;
		private static final int VALUE = 2;
		private static final int BRACKET = 3;

		private static final Map<String, Integer> OPERATOR_MAP = new HashMap<>();

		static {
			OPERATOR_MAP.put("!", 2);
			OPERATOR_MAP.put(">", 6);
			OPERATOR_MAP.put(">=", 6);
			OPERATOR_MAP.put("<", 6);
			OPERATOR_MAP.put("<=", 6);
			OPERATOR_MAP.put("==", 7);
			OPERATOR_MAP.put("!=", 7);
			OPERATOR_MAP.put("contains", 7);
			OPERATOR_MAP.put("&&", 11);
			OPERATOR_MAP.put("||", 12);
		}

		private int type;

		public String operator;

		public float value;

		public boolean isShow;

		public String valueString;

		private boolean isBuild;

		public Item(String key, Map<String, String> valueMap) {
			if ("(".equals(key) || ")".equals(key)) {
				type = BRACKET;
				operator = key;
			} else if (">".equals(key)
					|| ">=".equals(key)
					|| "<".equals(key)
					|| "<=".equals(key)
					|| "==".equals(key)
					|| "!=".equals(key)
					|| "&&".equals(key)
					|| "||".equals(key)
					|| "contains".equals(key)) {
				type = OPERATOR;
				operator = key;
			} else if ("!".equals(key)) {
				type = OPERATOR_SINGLE;
				operator = key;
			} else {
				type = VALUE;
				valueString = key;
				if (StringUtils.isBoolean(key)) {
					isShow = Boolean.parseBoolean(key);
				} else if (StringUtils.isFloat(key)) {
					value = Float.parseFloat(key);
				} else {
					buildValue(valueMap);
				}
			}
		}

		private void buildValue(Map<String, String> valueMap) {
			if (!isBuild) {
				isBuild = true;

				valueString = valueMap.get(valueString);
				if (TextUtils.isEmpty(valueString)) {
					value = Float.NaN;
				} else {
					if (StringUtils.isFloat(valueString)) {
						value = Float.parseFloat(valueString);
					}
				}
			}
		}

		public Item(Boolean isShow) {
			type = VALUE;
			this.isShow = isShow;
		}

		public boolean isValue() {
			return type == VALUE;
		}

		public boolean isOperator() {
			return type == OPERATOR || type == OPERATOR_SINGLE;
		}

		public boolean isSingleOperator() {
			return type == OPERATOR_SINGLE;
		}

		public boolean isLeftBracket() {
			return operator.equals("(");
		}

		public boolean isRightBracket() {
			return operator.equals(")");
		}

		public boolean compareOperator(Item item) {
			return OPERATOR_MAP.get(operator) < OPERATOR_MAP.get(item.operator);
		}

	}

	public static boolean compute(String expression, Map<String, String> valueMap, boolean defaultValue) {
		if (TextUtils.isEmpty(expression)) {
			return defaultValue;
		}

		try {
			return compute(toSuffixExpression(toInfixExpression(expression, valueMap)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	private static List<Item> toInfixExpression(String expression, Map<String, String> valueMap) throws Exception {
		expression = expression.replaceAll(Pattern.quote("("), " ( ");
		expression = expression.replaceAll(Pattern.quote(")"), " ) ");
		expression = expression.replaceAll(Pattern.quote("!="), "_notequal");
		expression = expression.replaceAll(Pattern.quote("!"), " ! ");
		expression = expression.replaceAll(Pattern.quote("_notequal"), "!=");

		List<Item> items = new ArrayList<>();
		String[] strings = expression.split("\\s+");

		for (String string : strings) {
			if (!TextUtils.isEmpty(string)) {
				Item item = new Item(string, valueMap);
				items.add(item);
			}
		}

		for (int i = 1; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.isOperator()
					&& (item.operator.equals(">")
					|| item.operator.equals(">=")
					|| item.operator.equals("<")
					|| item.operator.equals("<=")
					|| item.operator.equals("==")
					|| item.operator.equals("!=")
					|| item.operator.equals("contains"))) {
				Item lastItem = items.get(i - 1);
				lastItem.buildValue(valueMap);
			}
		}

		return items;
	}

	private static Stack<Item> toSuffixExpression(List<Item> items) {
		Stack<Item> s1 = new Stack<>();
		Stack<Item> s2 = new Stack<>();

		for (Item item : items) {
			if (item.isValue()) {
				s2.push(item);
			} else if (item.isOperator()) {
				boolean itemPushed = false;
				while (!itemPushed) {
					if (s1.isEmpty() || "(".equals(s1.peek().operator)) {
						s1.push(item);
						itemPushed = true;
					} else if (item.compareOperator(s1.peek())) {
						s1.push(item);
						itemPushed = true;
					} else {
						s2.push(s1.pop());
					}
				}
			} else {
				if (item.isLeftBracket()) {
					s1.push(item);
				} else if (item.isRightBracket()) {
					while (!s1.peek().isLeftBracket()) {
						s2.push(s1.pop());
					}
					s1.pop();
				}
			}
		}
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}

		return reverse(s2);
	}

	private static Stack<Item> reverse(Stack<Item> s) {
		Stack<Item> result = new Stack<Item>();
		while(!s.isEmpty()) {
			result.push(s.pop());
		}
		return result;
	}

	private static boolean compute(Stack<Item> s) {
		Stack<Item> result = new Stack<>();
		while (!s.isEmpty()) {
			Item item = s.pop();
			if (item.isValue()) {
				result.push(item);
			} else if (item.isOperator()) {
				String operator = item.operator;
				Item right = result.pop();
				boolean isShow = true;
				if (item.isSingleOperator()) {
					if (operator.equals("!")) {
						isShow = !right.isShow;
					}
				} else {
					Item left = result.pop();

					if (operator.equals("==")) {
						isShow = left.value == right.value;
					} else if (operator.equals("!=")) {
						isShow = left.value != right.value;
					} else if (operator.equals(">=")) {
						isShow = left.value >= right.value;
					} else if (operator.equals("<=")) {
						isShow = left.value <= right.value;
					} else if (operator.equals("<")) {
						isShow = left.value < right.value;
					} else if (operator.equals(">")) {
						isShow = left.value > right.value;
					} else if (operator.equals("&&")) {
						isShow = left.isShow && right.isShow;
					} else if (operator.equals("||")) {
						isShow = left.isShow || right.isShow;
					} else if (operator.equals("contains")) {
						if (TextUtils.isEmpty(left.valueString)) {
							isShow = false;
						} else {
							isShow = Arrays.asList(left.valueString.split(TemplateConfig.getSelectDivider()))
									.contains(right.valueString);
						}
					}
				}
				Item itemResult = new Item(isShow);
				result.push(itemResult);
			}
		}
		return result.pop().isShow;
	}

}
