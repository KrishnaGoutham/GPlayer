package com.gplayer.utils;

import java.util.Observable;
import java.util.Observer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.gplayer.BaseActivity;

public class NetworkManager extends Observable
{

	enum ConnectionType
    {
        TYPE_WIFI, TYPE_MOBILE, TYPE_ETHERNET, TYPE_NONE
    }
	
	private static final String TAG = "NetworkManager";
    private static NetworkManager sInstance;
    private Context mAppContext;
    private boolean mIsRegestered;
    private NetworkBroadcastReceiver mReceiver;
    private String mPhoneNumber;

    /**
     * Gets singleton instance of NetworkManager.
     * 
     * @param pContext
     *            Application Context.
     * @return
     * 
     * @Note: Passing non-application context can cause memory leak.
     */
    public static boolean initInstance(Context context)
    {
    	if (context == null)
    		return false;
    	
        if (sInstance == null && context != null) {
            sInstance = new NetworkManager(context);
        } 
        
        return true;
    }
    
    public static NetworkManager getInstance() throws InitNotCalledException
    {
      if(sInstance == null) {
            throw new InitNotCalledException(
                    "initInstance should have been called successfully before using getInstanec");
      } else { 
    	  return sInstance;
      }
    }
   

	private NetworkManager(Context context)
    {
        mAppContext = context;
        mIsRegestered = false;
        mReceiver = new NetworkBroadcastReceiver();
        mPhoneNumber = retrievePhoneNumber();
    }

    public boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) mAppContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) 
            return true;
        else 
            return false;
    }

    public ConnectionType getConnectionType()
    {
        ConnectivityManager cm = (ConnectivityManager) mAppContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        ConnectionType connType = ConnectionType.TYPE_NONE;

        switch (activeNetwork.getType())
        {
            case ConnectivityManager.TYPE_WIFI:
                connType = ConnectionType.TYPE_WIFI;
                break;

            case ConnectivityManager.TYPE_MOBILE:
                connType = ConnectionType.TYPE_MOBILE;
                break;

            case ConnectivityManager.TYPE_ETHERNET:
                connType = ConnectionType.TYPE_ETHERNET;
                break;

            default:
                connType = ConnectionType.TYPE_NONE;
                break;
        }

        return connType;
    }
    
    @Override
    public void addObserver(Observer observer)
    {
        if(!mIsRegestered)
        {
            registerForNetworkChnage();
            mIsRegestered = true;
        }
        super.addObserver(observer);
    }
    
    @Override
    public synchronized void deleteObserver(Observer observer)
    {
        super.deleteObserver(observer);
        if (super.countObservers() == 0)
        {
            unregisterForNetworkChange();
            mIsRegestered = false;
        }
    }
    
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
        if (mIsRegestered)
        {
            unregisterForNetworkChange();
            mIsRegestered = false;
        }
    }
    
    public String getDeviceId()
    {
        TelephonyManager telephonyManager = (TelephonyManager) mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
    
    
    private void connectivityChanged()
    {
        Log.i (TAG, "connectivityChanged:Notifying Observers");
        notifyObservers(isConnected());
    }
    
    private void registerForNetworkChnage()
    {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mAppContext.registerReceiver(mReceiver, intentFilter);
    }
    
    private void unregisterForNetworkChange()
    {
        mAppContext.unregisterReceiver(mReceiver);
    }
    
    public String getPhoneNumber()
    {
        return mPhoneNumber;
    }
    
    private String retrievePhoneNumber()
    {
        /*
         * Check shared preferences for phone number. If not available try to get from
         * API or from the user and store in preferences
         */
        String phoneNumber = null;
        SharedPreferences netwokprefs = mAppContext.getSharedPreferences(
                BaseActivity.PREF_KEY, Context.MODE_PRIVATE);
        
        if(!netwokprefs.contains(BaseActivity.PREF_KEY_PHONE_NUMBER)) {
            TelephonyManager manager = (TelephonyManager) mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
            phoneNumber = manager.getLine1Number();
            
            //Add phone number to local storage
           SharedPreferences.Editor editor =  netwokprefs.edit();
           editor.putString(BaseActivity.PREF_KEY_PHONE_NUMBER, phoneNumber);
           editor.commit();
        }
        
        phoneNumber = netwokprefs.getString(BaseActivity.PREF_KEY_PHONE_NUMBER, null);
        return phoneNumber;
    }
    
    private class NetworkBroadcastReceiver extends BroadcastReceiver
    {
        private static final String TAG = "NetworkBroadcastReceiver";
        @Override
        public void onReceive(Context context, Intent intent)
        {
           if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                  Log.i (TAG, "Connection Changed");
                  NetworkManager.this.connectivityChanged();
           }
        }
    }
    
    static class InitNotCalledException extends RuntimeException 
    {
        private static final long serialVersionUID = -5256128902203313622L;
        public InitNotCalledException(String pMsg) 
        {
            super(pMsg);
        }
    }
        
}
