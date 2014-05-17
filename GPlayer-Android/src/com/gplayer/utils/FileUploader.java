package com.gplayer.utils;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.gplayer.BuildConfig;

import edu.gvsu.cis.masl.channelAPI.XHR;

public class FileUploader
{

    private static final String TAG = FileUploader.class.getName();
    private HttpClient mClient;
    private String mServerURL;

    public FileUploader(String serverURL)
    {
        mClient = new DefaultHttpClient();
        mServerURL = serverURL; 
    }
   

    private String getUploadUrl()
    {
        String uploadUrl = null;
        HttpGet httpGet = new HttpGet(mServerURL + "/_ah/getuploadurl/");
        mClient = new DefaultHttpClient();

        try {
            XHR xhr = new XHR(mClient.execute(httpGet));
            String response = xhr.getResponseText();

            Log.i(TAG, "Response:" + response);

            JSONObject jobj = new JSONObject(response);

            if (jobj.getString("status").equalsIgnoreCase("success")) {
                uploadUrl = jobj.getString("url");

                // hack

                uploadUrl = uploadUrl.replace("localhost", "10.0.2.2");
            }

        } catch (Exception e) {
            Log.e(TAG, "getUploadUrl():" + e.getMessage());
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        return uploadUrl;
    }

    public String upload(File file) throws IOException
    {
        String blobKey = null;
        final String uploadUrl = getUploadUrl();

        if (uploadUrl == null) return null;

        HttpPost httppost = new HttpPost(uploadUrl);
        MultipartEntity entity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);

        entity.addPart("file", new FileBody(file, "application/zip"));
        httppost.setEntity(entity);

        mClient = new DefaultHttpClient();
        mClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
                HttpVersion.HTTP_1_1);

        try {
            XHR xhr = new XHR(mClient.execute(httppost));
            String response = xhr.getResponseText();

            Log.i(TAG, "Response:" + response);

            JSONObject jobj = new JSONObject(response);

            if (jobj.getString("status").equalsIgnoreCase("success")) {
                blobKey = jobj.getString("blob-key");
            }

        } catch (JSONException e) {
            Log.e(TAG, "upload:" + e.getMessage());
            if (BuildConfig.DEBUG) e.printStackTrace();
        } catch (ClientProtocolException e) {
            Log.e(TAG, "upload:" + e.getMessage());
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        return blobKey;
    }
}
