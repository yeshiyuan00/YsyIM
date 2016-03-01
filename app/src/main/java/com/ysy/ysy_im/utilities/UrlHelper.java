package com.ysy.ysy_im.utilities;

/**
 * Helper class to generate escaped URIs
 * 
 */
public class UrlHelper {
	public static final String DEFAULT_ENCODING = "UTF-8";
	// public static final String DOMAIN = "http://test.coreteacher.cn/v1";
	public static String DOMAIN = "http://im.stay4it.com";
	private static final String ACTION_LOGIN = "/user/account/login";
	private static final String ACTION_BIND_BAIDU_PUSH = "/user/account/bindBaiduPushUserId";
	private static final String ACTION_GET_CONVERSATION = "/user/message/getConversationList";

	public static String getDomain() {
		return DOMAIN + "/v1";
	}

	public static String loadLogin() {
		return getDomain() + ACTION_LOGIN;
	}


	public static String loadConversation(){
		return getDomain() + ACTION_GET_CONVERSATION;
	}

	public static String loadBindBaidu(String userId) {
		return getDomain() + ACTION_BIND_BAIDU_PUSH + "?baiduPushUserId=" + userId;
	}

}
