package com.ysy.ysy_im.net.callback;

import com.google.gson.stream.JsonReader;
import com.ysy.ysy_im.net.AppException;
import com.ysy.ysy_im.net.BaseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.ysy.ysy_im.net.AppException.ExceptionStatus;

/**
 * @author Stay
 * @version create time：Sep 15, 2014 12:40:04 PM
 * @param <T>
 */
public abstract class JsonObjectCallback<T extends BaseEntity> extends AbstractCallback<T> {

	@Override
	public T bindData(String json) throws AppException {
		if (path == null) {
			throw new AppException(AppException.ExceptionStatus.ParameterException, "you should set path when you use JsonReaderCallback");
		}

		try {
			Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			FileReader in = new FileReader(new File(json));
			JsonReader reader = new JsonReader(in);
			T t = ((Class<T>) type).newInstance();
			t.readFromJson(reader);
			reader.close();
			return t;
		} catch (FileNotFoundException e) {
			throw new AppException(AppException.ExceptionStatus.ParseJsonException, e.getMessage());
		} catch (InstantiationException e) {
			throw new AppException(ExceptionStatus.ParseJsonException, e.getMessage());
		} catch (IllegalAccessException e) {
			throw new AppException(ExceptionStatus.ParseJsonException, e.getMessage());
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.ParseJsonException, e.getMessage());
		}

	}
}
