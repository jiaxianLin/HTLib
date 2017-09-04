package com.ht.htlibrary.template;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class ReflectUtil {

	public static final int BOOLEAN = 0;

	public static final int BYTE = 1;

	public static final int SHORT = 2;

	public static final int INT = 3;

	public static final int LONG = 4;

	public static final int FLOAT = 5;

	public static final int DOUBLE = 6;

	public static final int STRING = 7;

	public static final int DATE = 8;

	public static final int OBJECT = 9;


	public static int getClassType(Class<?> cls) {
		String type = cls.getName();
		if ("java.lang.Boolean".equals(type) || "boolean".equals(type)) {
			return BOOLEAN;
		} else if ("java.lang.Byte".equals(type) || "byte".equals(type)) {
			return BYTE;
		} else if ("java.lang.Short".equals(type) || "short".equals(type)) {
			return SHORT;
		} else if ("java.lang.Integer".equals(type) || "int".equals(type)) {
			return INT;
		} else if ("java.lang.Long".equals(type) || "long".equals(type)) {
			return LONG;
		} else if ("java.lang.Float".equals(type) || "float".equals(type)) {
			return FLOAT;
		} else if ("java.lang.Double".equals(type) || "double".equals(type)) {
			return DOUBLE;
		} else if ("java.lang.String".equals(type)) {
			return STRING;
		} else if ("java.util.Date".equals(type)) {
			return DATE;
		} else {
			return OBJECT;
		}
	}

	public static int getFieldType(Field field) {
		return getClassType(field.getType());
	}

	public static void setField(Object object, Field field, String value) {
		try {
			int type = getFieldType(field);
			switch (type) {
				case BOOLEAN:
					field.setBoolean(object, Boolean.valueOf(value));
					break;
				case BYTE:
					field.setByte(object, Byte.valueOf(value));
					break;
				case SHORT:
					field.setShort(object, Short.valueOf(value));
					break;
				case INT:
					field.setInt(object, Integer.valueOf(value));
					break;
				case LONG:
					field.setLong(object, Long.valueOf(value));
					break;
				case FLOAT:
					field.setFloat(object, Float.valueOf(value));
					break;
				case DOUBLE:
					field.setDouble(object, Double.valueOf(value));
					break;
				case STRING:
					field.set(object, value);
					break;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
