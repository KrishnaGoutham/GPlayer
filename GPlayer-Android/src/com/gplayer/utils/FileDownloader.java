package com.gplayer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.api.client.util.IOUtils;
import com.gplayer.BuildConfig;

public class FileDownloader
{

    private static final String TAG = FileDownloader.class.getName();
    private static final String DOWNLOAD_PATH = "/_ah/download";
    private String mServerURL;
    
    public FileDownloader(String serverURL)
    {
        mServerURL = serverURL;
    }
    
    private HttpResponse sendRequest(String pKey) throws IOException
    {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(mServerURL + DOWNLOAD_PATH);
        HttpResponse response = null;

        try {
            post.getParams().setParameter("blob-key", pKey);
            response = client.execute(post);
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage());
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        return response;
    }

    private File getResponseFile(HttpResponse pResponse)
    {
        File tempFile = null;
        try {
            HttpEntity entity = pResponse.getEntity();

            if (entity.getContentLength() > 0) {
                tempFile = File.createTempFile("temp", ".mp3");
                InputStream in = entity.getContent();
                OutputStream out = new FileOutputStream(tempFile);

                IOUtils.copy(in, out);
                in.close();
                out.close();
            }

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        return tempFile;
    }

    /**
     * Download the file with pKey to temp and return the File object.
     * 
     * @param pKey
     *            Blob-key of the file to be dowmloaded.
     * @return Returns File if download was successful else null.
     * 
     * @throws IOException
     *             If network is not present or lost in between download.
     * @throws InvalidKeyException
     *             If pKey is not a valid blob-key.
     */
    public File download(String pKey) throws IOException, InvalidKeyException
    {
        File tempFile = null;

        HttpResponse response = sendRequest(pKey);
        String respStatus = response.getFirstHeader("status").getValue();

        if (response != null && respStatus.equalsIgnoreCase("success")) {
            tempFile = getResponseFile(response);
        } else if (!respStatus.equalsIgnoreCase("success")) { throw new InvalidKeyException(
                "Invalid blok-key " + pKey); }

        return tempFile;
    }

    class InvalidKeyException extends RuntimeException
    {
        private static final long serialVersionUID = 7163563142547192081L;

        public InvalidKeyException(String pMsg)
        {
            super(pMsg);
        }
    }

}
