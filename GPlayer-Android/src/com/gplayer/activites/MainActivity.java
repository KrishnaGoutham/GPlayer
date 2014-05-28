package com.gplayer.activites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.EditText;
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


public class MainActivity extends BaseActivity implements
        ActionBar.TabListener,
        ContactsListFragment.OnContactsInteractionListener
{
    private static final String TAG = "MainActivity";
    
    private MessageEndpoint messageEndpoint = null;
    private TabSelectionPageAdapter mSelectionPageAdapter; 
    private ViewPager mViewPager;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSelectionPageAdapter = new TabSelectionPageAdapter(getSupportFragmentManager());
        
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        //actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSelectionPageAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSelectionPageAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSelectionPageAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        
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
    
    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1)
    {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction)
    {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition()); 
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1)
    {
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
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
                String messageView;
                
                messageView = "Last 5 Messages read from "
                        + messageEndpoint.getBaseUrl() + ":\n";
                for (MessageData message : messages.getItems()) {
                    messageView.concat(message.getMessage() + "\n");
                }
            }
        }
    }

    public static class TabSelectionPageAdapter extends FragmentPagerAdapter
    {

        static final int TAB_CONTACTS = 0;
        static final int TAB_SONGS = 1;
        static final int TAB_PLAYER = 2;
        static final int TAB_COUNT = 3; 
        
        private static final String mPageTitles[] = {
            "Contacts",
            "Songs",
            "Player"
        }; 
        
        public TabSelectionPageAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos)
        {
            Fragment fragment = null;
            fragment = new ContactsListFragment();
            /*switch(pos)
            {
                case TAB_CONTACTS:
                    fragment = new ContactlistFragment();
                    break;
                case TAB_SONGS:
                    fragment = new SongsFragment();
                    break;
                case TAB_PLAYER:
                    fragment = new PlayerFragment();
                    break;
                default:
                    break;
            }*/
            return fragment;
        }

        @Override
        public int getCount()
        {
            return TAB_COUNT;
        }
        
        @Override
        public CharSequence getPageTitle(int position)
        {
            return mPageTitles[position];
        }
        
    }

    @Override
    public void onContactSelected(Uri contactUri)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onSelectionCleared()
    {
        // TODO Auto-generated method stub
        
    }
    
}
