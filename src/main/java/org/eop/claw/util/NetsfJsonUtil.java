package org.eop.claw.util;

import java.util.List;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;

import net.sf.json.JSONObject;
/**
 * lixinjie 2016-12-26
 */
public final class NetsfJsonUtil {

	private NetsfJsonUtil() {
		
	}
	
	public static <T> T getJsonValue(JSONObject json, String key) {
		if (TypeUtil.notNetsfJsonObject(json.get(key)) && TypeUtil.notNetsfJsonArray(json.get(key))) {
			return TypeUtil.asType(json.get(key));
		}
		return null;
	}
	
	public static JSONObject getJsonObject(JSONObject json, String key) {
		if (TypeUtil.isNetsfJsonObject(json.get(key))) {
			return json.getJSONObject(key);
		}
		return null;
	}
	
	public static <T> List<T> getJsonList(JSONObject json, String key) {
		if (TypeUtil.isNetsfJsonArray(json.get(key))) {
			return TypeUtil.asListType(json.getJSONArray(key));
		}
		return null;
	}
	
	public static <T> List<T> getJsonValueList(JSONObject json, String key) {
		List<T> list = getJsonList(json, key);
		if (EmptyUtil.isEmpty(list)) {
			return null;
		}
		if (TypeUtil.notNetsfJsonObject(ListUtil.getFirst(list))) {
			return list;
		}
		return null;
	}
	
	public static <T> List<JSONObject> getJsonObjectList(JSONObject json, String key) {
		List<T> list = getJsonList(json, key);
		if (EmptyUtil.isEmpty(list)) {
			return null;
		}
		if (TypeUtil.isNetsfJsonObject(ListUtil.getFirst(list))) {
			return TypeUtil.asListType(list);
		}
		return null;
	}
	
	
	
	public static <T> List<List<T>> getListList(JSONObject json, String key) {
		List<T> list = getJsonList(json, key);
		if (EmptyUtil.isEmpty(list)) {
			return null;
		}
		if (TypeUtil.isNetsfJsonArray(ListUtil.getFirst(list))) {
			return TypeUtil.asListListType(list);
		}
		return null;
	}
	
	public static <T> List<List<JSONObject>> getJsonObjectListList(JSONObject json, String key) {
		List<T> list = getJsonList(json, key);
		if (EmptyUtil.isEmpty(list)) {
			return null;
		}
		if (TypeUtil.notNetsfJsonArray(ListUtil.getFirst(list))) {
			return null;
		}
		List<T> first = TypeUtil.asListType(ListUtil.getFirst(list));
		if (TypeUtil.isNetsfJsonObject(ListUtil.getFirst(first))) {
			return TypeUtil.asListListType(list);
		}
		return null;
	}
	
	public static <T> List<List<List<T>>> getListListList(JSONObject json, String key) {
		List<T> list = getJsonList(json, key);
		if (EmptyUtil.isEmpty(list)) {
			return null;
		}
		if (TypeUtil.notNetsfJsonArray(ListUtil.getFirst(list))) {
			return null;
		}
		List<T> first = TypeUtil.asListType(ListUtil.getFirst(list));
		if (TypeUtil.isNetsfJsonArray(ListUtil.getFirst(first))) {
			return TypeUtil.asListListListType(list);
		}
		return null;
	}
	
	public static <T> List<List<T>> getElementListList(JSONObject json, String key) {
		List<T> list = getJsonList(json, key);
		if (EmptyUtil.isEmpty(list)) {
			return null;
		}
		if (TypeUtil.notNetsfJsonArray(ListUtil.getFirst(list))) {
			return null;
		}
		List<T> first = TypeUtil.asListType(ListUtil.getFirst(list));
		if (TypeUtil.isNetsfJsonObject(ListUtil.getFirst(first))) {
			return null;
		}
		if (TypeUtil.isNetsfJsonArray(ListUtil.getFirst(first))) {
			return null;
		}
		return TypeUtil.asListListType(list);
	}
	
	
	
	public static <T> List<T> getAllJsonValue(List<JSONObject> jsonList, String key) {
		List<Object> valueList = ListUtil.newList(jsonList.size());
		for (JSONObject json : jsonList) {
			valueList.add(getJsonValue(json, key));
		}
		return TypeUtil.asListType(valueList);
	}
	
	public static <T> List<JSONObject> getAllJsonObject(List<JSONObject> jsonList, String key) {
		List<JSONObject> valueList = ListUtil.newList(jsonList.size());
		for (JSONObject json : jsonList) {
			valueList.add(getJsonObject(json, key));
		}
		return valueList;
	}
	
	public static <T> List<List<T>> getAllJsonValueList(List<JSONObject> jsonList, String key) {
		List<List<Object>> valueListList = ListUtil.newList(jsonList.size());
		for (JSONObject json : jsonList) {
			valueListList.add(getJsonValueList(json, key));
		}
		return TypeUtil.asListListType(valueListList);
	}
	
	public static <T> List<List<JSONObject>> getAllJsonObjectList(List<JSONObject> jsonList, String key) {
		List<List<JSONObject>> valueListList = ListUtil.newList(jsonList.size());
		for (JSONObject json : jsonList) {
			valueListList.add(getJsonObjectList(json, key));
		}
		return valueListList;
	}
	
	public static <T> List<List<List<T>>> getAllListList(List<JSONObject> jsonList, String key) {
		List<List<List<Object>>> valueListList = ListUtil.newList(jsonList.size());
		for (JSONObject json : jsonList) {
			valueListList.add(getListList(json, key));
		}
		return TypeUtil.asListListListType(valueListList);
	}
}
