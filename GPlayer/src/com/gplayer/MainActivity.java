package com.gplayer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * The Main Activity.
 * 
 * This activity starts up the RegisterActivity immediately, which communicates
 * with your App Engine backend using Cloud Endpoints. It also receives push
 * notifications from backend via Google Cloud Messaging (GCM).
 * 
 * Check out RegisterActivity.java for more details.
 */
public class MainActivity extends Activity
{
	public static final String TAG = MainActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Register with GCM
		if (Register())
		{
			
		}

	}

	private boolean Register()
	{

		if (GCMIntentService.PROJECT_NUMBER == null
				|| GCMIntentService.PROJECT_NUMBER.length() == 0) {
			Log.e(TAG,
					"Unable to register for Google Cloud Messaging. "
							+ "Your application's PROJECT_NUMBER field is unset! You can change "
							+ "it in GCMIntentService.java");
		} else {

			try {
				GCMIntentService.register(getApplicationContext());
			} catch (Exception e) {
				Log.e(TAG,
						"Exception received when attempting to register for Google Cloud "
								+ "Messaging. Perhaps you need to set your virtual device's "
								+ " target to Google APIs? "
								+ "See https://developers.google.com/eclipse/docs/cloud_endpoints_android"
								+ " for more information.", e);
			}
		}
		return true;
	}

	private void UnRegister()
	{
		GCMIntentService.unregister(getApplicationContext());
	}

}
