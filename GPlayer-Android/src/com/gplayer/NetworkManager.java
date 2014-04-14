package com.gplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;

public class NetworkManager extends Observable {
	
	private static NetworkManager sInstance;
	private Context mAppContext;
	
	
	/**
	 * Gets singleton instance of NetworkManager.
	 * @param pContext Application Context. 
	 * @return
	 * 
	 * @Note: Passing non-application context can cause memory leak.
	 */
	public static NetworkManager getInstance (Context pContext) {
		if (sInstance == null){
			sInstance =  new NetworkManager(pContext);
		}
		
		return sInstance;
	}
	
	private NetworkManager(Context pContext) {
		mAppContext = pContext;
	}
	
	public boolean isConnected() {
		return false;
	}
	
}
