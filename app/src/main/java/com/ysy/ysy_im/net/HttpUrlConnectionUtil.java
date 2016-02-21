package com.ysy.ysy_im.net;

import com.ysy.ysy_im.net.AppException.ExceptionStatus;
import com.ysy.ysy_im.utilities.TextUtil;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/** 
 * @author Stay  
 * @version create time：Sep 15, 2014 12:02:54 PM 
 */
public class HttpUrlConnectionUtil {
	private static final int CONNECT_TIME = 10*1000;
	private static final int READ_TIME = 10 * 1000;

	public static HttpURLConnection execute(Request request) throws AppException {
		switch (request.method) {
		case GET:
		case DELETE:
			return get(request);
		case POST:
		case PUT:
			return post(request);
		default:
			throw new AppException(ExceptionStatus.ParameterException, "the request method " + request.method.name() + " can't be supported");
		}
	}

	private static HttpURLConnection post(Request request) throws AppException {
		OutputStream out = null;
		boolean isClosed = false;
		try {
			URL url = new URL(request.url);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod(request.method.name());
			connection.setConnectTimeout(CONNECT_TIME);
			connection.setReadTimeout(READ_TIME);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			setHeader(connection, request.headers);
			out = connection.getOutputStream();
			if (TextUtil.isValidate(request.urlParameters)) {
				out.write(getParams(request.urlParameters).getBytes());
			}
			if (TextUtil.isValidate(request.postContent)) {
				out.write(request.postContent.getBytes());
			}
			if (request.callback != null) {
				isClosed = request.callback.onCustomOutput(out);
			}
			
			return connection;
		} catch (MalformedURLException e) {
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		}finally{
			if (out != null && !isClosed) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static HttpURLConnection get(Request request) throws AppException {
		try {
			URL url = new URL(request.url);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod(request.method.name());
			connection.setConnectTimeout(CONNECT_TIME);
			connection.setReadTimeout(READ_TIME);
			setHeader(connection, request.headers);
			return connection;
		} catch (MalformedURLException e) {
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		}
	}
	
	private static void setHeader(HttpURLConnection connection, Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> entry : headers.entrySet()) {
				connection.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
	}
	
	private static String getParams(ArrayList<NameValuePair> lists) {
		String params = "";
		NameValuePair pair = null;
		for (int j = 0; j < lists.size(); j++) {
			pair = lists.get(j);
			params += pair.getName() + "=" + pair.getValue();
			if (j != lists.size() - 1) {
				params += "&";
			}
		}
		return params;
	}
	
}
