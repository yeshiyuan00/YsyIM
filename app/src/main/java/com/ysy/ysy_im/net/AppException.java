package com.ysy.ysy_im.net;


/** 
 * @author Stay  
 * @version create time：Sep 15, 2014 5:19:44 PM 
 */
public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ExceptionStatus{
		FileNotFoundException, IllegalStateException, ParseException, IOException, CancelException, ServerException, ParameterException, TimeoutException, ParseJsonException
	}

	private ExceptionStatus status;
	
	public AppException(ExceptionStatus status,String detailMessage) {
		super(detailMessage);
		this.status = status;
	}
	
	public ExceptionStatus getStatus(){
		return status;
	}
	
}
