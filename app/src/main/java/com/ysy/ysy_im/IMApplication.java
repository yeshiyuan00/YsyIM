package com.ysy.ysy_im;

import android.app.Application;
import android.content.Context;

/** 
 * @author Stay  
 * @version create time：Mar 8, 2015 6:36:08 PM 
 */
public class IMApplication extends Application {
	public static Context gContext;
	public static String selfId="yeshiyuan";
	@Override
	public void onCreate() {
		super.onCreate();
		gContext=this;
	}
}
