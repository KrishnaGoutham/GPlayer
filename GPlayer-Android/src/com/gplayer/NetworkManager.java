package com.gplayer;

import java.util.Observable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager extends Observable
{

    enum ConnectionType
    {
        TYPE_WIFI, TYPE_MOBILE, TYPE_ETHERNET, TYPE_NONE
    }

    private static NetworkManager sInstance;
    private Context mAppContext;

    /**
     * Gets singleton instance of NetworkManager.
     * 
     * @param pContext
     *            Application Context.
     * @return
     * 
     * @Note: Passing non-application context can cause memory leak.
     */
    public static NetworkManager getInstance(Context pContext)
    {
        if (sInstance == null) {
            sInstance = new NetworkManager(pContext);
        }

        return sInstance;
    }

    private NetworkManager(Context pContext)
    {
        mAppContext = pContext;
    }

    public boolean isConnected()
    {

        ConnectivityManager cm = (ConnectivityManager) mAppContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) return true;
        else return false;
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

    class NetworkBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // TODO Auto-generated method stub
            
        }
    }

}
