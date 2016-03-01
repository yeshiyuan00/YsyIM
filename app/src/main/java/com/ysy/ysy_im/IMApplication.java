package com.ysy.ysy_im;

import android.app.Application;
import android.content.Context;

import com.ysy.ysy_im.entities.Profile;

/** 
 * @author Stay  
 * @version create time：Mar 8, 2015 6:36:08 PM 
 */
public class IMApplication extends Application {
	public static IMApplication gContext;
	private static Profile profile;
	public static String selfId = null;

	@Override
	public void onCreate() {
		super.onCreate();
		gContext=this;
	}

	public static void setProfile(Profile tmp) {
		profile = tmp;
		selfId = profile.getUserId();
	}
	public static Profile getProfile(){
		return profile;
	}

	public static String getToken(){
		if (profile == null) {
			return null;
		}
		return profile.getAccess_token();
	}
}
