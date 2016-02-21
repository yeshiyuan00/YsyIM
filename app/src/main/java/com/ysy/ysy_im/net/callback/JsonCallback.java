package com.ysy.ysy_im.net.callback;

import com.ysy.ysy_im.utilities.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/** 
 * @author Stay  
 * @version create time：Sep 15, 2014 12:40:04 PM 
 * @param <T>
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {
//	private Class<T> mReturnClass;
//	private Type mReturnType;
	
	@Override
	public T bindData(String json) {
		Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return JsonParser.deserializeFromJson(json, type);
//		if (mReturnClass != null) {
//			return JsonParser.deserializeFromJson(json, mReturnClass);
//		}else if (mReturnType != null) {
//			return JsonParser.deserializeFromJson(json, mReturnType);
//		}
//		return null;
	}
	
//	public JsonCallback<T> setReturnClass(Class<T> clz){
//		this.mReturnClass = clz;
//		return this;
//	}
//	
//	public JsonCallback<T> setReturnType(Type type){
//		this.mReturnType = type;
//		return this;
//	}
}
