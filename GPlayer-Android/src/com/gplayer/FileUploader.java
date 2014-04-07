package com.gplayer;

import java.io.File;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;
import edu.gvsu.cis.masl.channelAPI.XHR;


public class FileUploader
{
	private static final String TAG = FileUploader.class.getName();
	private HttpClient mClient;
	
	public FileUploader() 
	{
		mClient = new DefaultHttpClient();
	}
	
	private String getUploadUrl() 
	{
		String uploadUrl = null;
		HttpGet httpGet = new HttpGet(CloudEndpointUtils.LOCAL_APP_ENGINE_SERVER_URL_FOR_ANDROID + "/_ah/getuploadurl/");
		
		try {
			XHR xhr; 
			JSONObject jobj;
			String response;
			
			xhr =  new XHR(mClient.execute(httpGet));
			response = xhr.getResponseText();
			
			Log.i(TAG, "Response:" + response);
			
			jobj =  new JSONObject(response);
			
			if (jobj.getString("status").equalsIgnoreCase("success"))
			{
				uploadUrl = jobj.getString("url");
			}
			
		} catch (Exception e) {
			Log.e(TAG, "getUploadUrl():"+e.getMessage());
			if(BuildConfig.DEBUG)
				e.printStackTrace();
		} 
		
		return uploadUrl;
	}
	
	public boolean upload (String filePath) 
	{
		boolean ret = false;
		HttpPost httppost = null;
		MultipartEntity entity = null;
		File file = null;
		
		final String uploadUrl = getUploadUrl();
		
		if (uploadUrl == null)
			return false;
		
		httppost = new HttpPost(uploadUrl);
		entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		file = new File(filePath);
		
		entity.addPart("file", new FileBody(file, "application/zip"));
		httppost.setEntity(entity);
		
		try {
			String result = EntityUtils.toString (mClient.execute(httppost).getEntity(), "UTF-8");
			ret = true;
			
			Log.i(TAG, "upload():"+result);
			
		} catch (Exception e) {
			Log.e(TAG, "upload:"+e.getMessage());
			if(BuildConfig.DEBUG)
				e.printStackTrace();
		}
		
		return ret;
	}
}
