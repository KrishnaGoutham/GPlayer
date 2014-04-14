package com.gplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.api.client.util.IOUtils;

import android.util.Log;

public class FileDownloader {

	private static final String TAG = FileDownloader.class.getName();
	private static final String DOWNLOAD_URL = CloudEndpointUtils.LOCAL_APP_ENGINE_SERVER_URL_FOR_ANDROID
												+ "/_ah/download";
	
	private HttpResponse sendRequest (String pKey) throws IOException {
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(DOWNLOAD_URL);
		HttpResponse response = null;
		
		try {
			post.getParams().setParameter("blob-key", pKey);
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.getMessage());
			if (BuildConfig.DEBUG)
				e.printStackTrace();
		}
		
		return response;
	}
	
	private File getResponseFile(HttpResponse pResponse)
	{
		File tempFile = null;
		try {
			HttpEntity entity = pResponse.getEntity();
			
			if (entity.getContentLength()> 0)	{
				tempFile = File.createTempFile("temp", ".mp3");
				InputStream in = entity.getContent();
				OutputStream out = new FileOutputStream(tempFile);
				
				IOUtils.copy(in, out);
				in.close();
				out.close();
			}
			
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			if (BuildConfig.DEBUG)
				e.printStackTrace();
		}
		
		return tempFile;
	}
	
	public File download(String pKey) throws IOException {
		File tempFile = null;
		
		HttpResponse response =  sendRequest(pKey);
			
		if(response != null)
		{
			tempFile = getResponseFile(response);
		}
		
		return tempFile;
	}
	
}
