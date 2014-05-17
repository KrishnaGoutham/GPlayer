package com.gplayer.activites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.IOUtils;
import com.gplayer.BaseActivity;
import com.gplayer.R;
import com.gplayer.backend.CloudEndpointUtils;
import com.gplayer.messageEndpoint.MessageEndpoint;
import com.gplayer.messageEndpoint.model.CollectionResponseMessageData;
import com.gplayer.messageEndpoint.model.MessageData;
import com.gplayer.utils.FileDownloader;
import com.gplayer.utils.FileUploader;

/**
 * An activity that communicates with your App Engine backend via Cloud
 * Endpoints.
 * 
 * When the user hits the "Register" button, a message is sent to the backend
 * (over endpoints) indicating that the device would like to receive broadcast
 * messages from it. Clicking "Register" also has the effect of registering this
 * device for Google Cloud Messaging (GCM). Whenever the backend wants to
 * broadcast a message, it does it via GCM, so that the device does not need to
 * keep polling the backend for messages.
 * 
 * If you've generated an App Engine backend for an existing Android project,
 * this activity will not be hooked in to your main activity as yet. You can
 * easily do so by adding the following lines to your main activity:
 * 
 * Intent intent = new Intent(this, RegisterActivity.class);
 * startActivity(intent);
 * 
 * To make the sample run, you need to set your PROJECT_NUMBER in
 * GCMIntentService.java. If you're going to be running a local version of the
 * App Engine backend (using the DevAppServer), you'll need to toggle the
 * LOCAL_ANDROID_RUN flag in CloudEndpointUtils.java. See the javadoc in these
 * classes for more details.
 * 
 * For a comprehensive walkthrough, check out the documentation at
 * http://developers.google.com/eclipse/docs/cloud_endpoints
 */
public class MainActivity extends BaseActivity
{

    enum State
    {
        REGISTERED, REGISTERING, UNREGISTERED, UNREGISTERING
    }
    
    static final String PREF_KEY =  "main_prefs";
    static final String PREF_KEY_PHONE_NUMBER = "key_phone_number";
    static final String PREF_KEY_GCM_REGISTRATION_ID = "key_gcm_registration_id";
    
    private static final String TAG = "MainActivity";
    
    private MessageEndpoint messageEndpoint = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); 
        
        /*
         * build the messaging endpoint so we can access old messages via an
         * endpoint call
         */
        MessageEndpoint.Builder endpointBuilder = new MessageEndpoint.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest httpRequest)
                    {
                    }
                });

        messageEndpoint = CloudEndpointUtils.updateBuilder(endpointBuilder)
                .build();
    }
    
    private String getPhoneNumberFromUser()
    {
        final EditText text = new EditText(this);
        AlertDialog dialogue = new AlertDialog.Builder(this).create();
        dialogue.setView(text);
        dialogue.show();
           
        return null;
    }
    
    private class UploadDataTask extends AsyncTask<Void, String, String>
    {

        public UploadDataTask(Activity activity)
        {

        }

        @Override
        protected String doInBackground(Void... params)
        {
            FileUploader uploader = new FileUploader(CloudEndpointUtils.LOCAL_APP_ENGINE_SERVER_URL_FOR_ANDROID);
            String blobkey = null;
            String filePath = null;

            // File tempFile = new File("file:///android_asset/file.mp3");
            try {
                File tempFile = File.createTempFile("fileupload", ".mp3");
                FileInputStream in = getAssets().openFd("file.mp3")
                        .createInputStream();
                FileOutputStream out = new FileOutputStream(tempFile);

                IOUtils.copy(in, out);
                in.close();
                out.close();

                blobkey = uploader.upload(tempFile);
            } catch (IOException e) {

            }

            if (blobkey != null) {
                FileDownloader downloader = new FileDownloader(CloudEndpointUtils.LOCAL_APP_ENGINE_SERVER_URL_FOR_ANDROID);

                try {
                    File f = downloader.download(blobkey);
                    filePath = f.getAbsolutePath();
                    // play the file.
                    MediaPlayer p = new MediaPlayer();
                    try {
                        FileInputStream in = new FileInputStream(f);

                        p.setDataSource(in.getFD());
                        p.prepare();
                        p.start();
                        
                        in.close();

                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (IOException e) {

                } 

            }

            return filePath;
        }

        protected void onPostExecute(String result)
        {
            if (result == null) {
                Toast.makeText(getBaseContext(), "Check Network.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG)
                        .show();
            }

        }
    }

    /*
     * Need to run this in background so we don't hold up the UI thread, this
     * task will ask the App Engine backend for the last 5 messages sent to it
     */
    private class QueryMessagesTask extends
            AsyncTask<Void, Void, CollectionResponseMessageData>
    {
        Exception exceptionThrown = null;
        MessageEndpoint messageEndpoint;

        public QueryMessagesTask(Activity activity,
                MessageEndpoint messageEndpoint)
        {
            this.messageEndpoint = messageEndpoint;
        }

        @Override
        protected CollectionResponseMessageData doInBackground(Void... params)
        {
            try {
                CollectionResponseMessageData messages = messageEndpoint
                        .listMessages().setLimit(5).execute();
                return messages;
            } catch (IOException e) {
                exceptionThrown = e;
                return null;
                // Handle exception in PostExecute
            }
        }

        protected void onPostExecute(CollectionResponseMessageData messages)
        {
            // Check if exception was thrown
            if (exceptionThrown != null) {
                Log.e(MainActivity.class.getName(),
                        "Exception when listing Messages", exceptionThrown);
                showDialog("Failed to retrieve the last 5 messages from "
                        + "the endpoint at " + messageEndpoint.getBaseUrl()
                        + ", check log for details");
            } else {
                TextView messageView = (TextView) findViewById(R.id.msgView);
                messageView.setText("Last 5 Messages read from "
                        + messageEndpoint.getBaseUrl() + ":\n");
                for (MessageData message : messages.getItems()) {
                    messageView.append(message.getMessage() + "\n");
                }
            }
        }
    }

    
}
