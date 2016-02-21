package com.ysy.ysy_im.net;



import com.ysy.ysy_im.net.callback.ICallback;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Stay
 * @version create time：Sep 15, 2014 11:44:26 AM
 */
public class Request {
	public enum RequestMethod {
		GET, POST, DELETE, PUT
	}
	public enum RequestTool {
		HTTPCLIENT, HTTPURLCONNECTION
	}

	public RequestMethod method;
	public String url;
	public HttpEntity entity;
	public Map<String, String> headers;
	public ICallback callback;
	public IRequestListener listener;
	private RequestTask task;
	public RequestTool tool;
	public ArrayList<NameValuePair> urlParameters;
	public String postContent;
	
	public Request(String url, RequestMethod method) {
		this.url = url;
		this.method = method;
		this.tool = RequestTool.HTTPCLIENT;
	}
	
	public Request(String url, RequestMethod method, RequestTool tool) {
		this.url = url;
		this.method = method;
		this.tool = tool;
	}

	public Request(String url) {
		this.url = url;
		this.method = RequestMethod.GET;
		this.tool = RequestTool.HTTPCLIENT;
	}

	public void setCallback(ICallback callback) {
		this.callback = callback;
	}

	public void addHeader(String key, String value) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(key, value);
	}
	
	public void setRequestListener(IRequestListener listener){
		this.listener = listener;
	}

	public void execute() {
		task = new RequestTask(this);
		task.execute();
//		task.executeOnExecutor(exec, params);
	}

	public void cancel(boolean force) {
		if (force && task != null) {
			task.cancel(true);
		}
		if (callback != null) {
			callback.cancel(force);
		}
	}

}
