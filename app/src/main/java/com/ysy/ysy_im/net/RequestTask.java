package com.ysy.ysy_im.net;

import android.os.AsyncTask;
import com.ysy.ysy_im.net.AppException.ExceptionStatus;

import org.apache.http.HttpResponse;

import java.net.HttpURLConnection;

/** 
 * @author Stay  
 * @version create time：Sep 15, 2014 12:35:20 PM 
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {
	private Request request;
	
	public RequestTask(Request request){
		this.request = request;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Object doInBackground(Void... params) {
		int retryCount = 0;
		int retry = 0;
		if (request.callback != null) {
			retryCount = request.callback.retryCount();
		}
		return request(retry, retryCount);
	}
	
	
	private Object request(int retry, int retryCount){
		try {
			Object object = null;
			if (request.callback != null) {
				object = request.callback.preRequest();
				if (object != null) {
					return object;
				}
			}
			if (request.tool == Request.RequestTool.HTTPCLIENT) {
				HttpResponse response = HttpClientUtil.execute(request);
				if (request.callback != null) {
					if (request.listener != null) {
						object = request.callback.handle(response,new IRequestListener() {
							
							@Override
							public void onProgressUpdate(int curPos, int contentLength) {
								publishProgress(curPos,contentLength);
							}
						});
					}else {
						object = request.callback.handle(response);
					}
					return request.callback.postRequest(object);
				}else {
					return null;
				}
			}else {
				HttpURLConnection connection = HttpUrlConnectionUtil.execute(request);
				if (request.callback != null) {
					if (request.listener != null) {
						object = request.callback.handle(connection,new IRequestListener() {
							
							@Override
							public void onProgressUpdate(int curPos, int contentLength) {
								publishProgress(curPos,contentLength);
							}
						});
					}else {
						object = request.callback.handle(connection);
					}
					return request.callback.postRequest(object);
				}else {
					return null;
				}
			}
		} catch (AppException e) {
			if (e.getStatus() == ExceptionStatus.TimeoutException) {
				if (retry < retryCount) {
					return request(retry++, retryCount);
				}
			}
			return e;
		}
	}
	
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (request.callback.isForceCancelled()) {
			return;
		}
		if (request.callback != null) {
			if (result != null && result instanceof AppException) {
				request.callback.onFailure((AppException)result);
			}else {
				request.callback.onSuccess(result);
			}
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		request.listener.onProgressUpdate(values[0], values[1]);
	}

	

}
