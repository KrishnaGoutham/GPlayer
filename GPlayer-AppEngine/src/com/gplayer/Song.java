package com.gplayer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Song
{
    enum Type {
        AUDIO, VIDEO
    };

    /*
     * Autogenerated primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key mKey;
    
    private String  mArtist;
    private String  mTitle;
    private String  mAlbum;
    private boolean mInBlobStore;
    private String  mBlobKey;
    private Type    mSongType;
    private float   mLength;

    public Song(String title, String blobkey, Type songType, float lenght)
    {
        if (blobkey == null)
            mInBlobStore = false;

        mTitle = title; 
        mBlobKey = blobkey;
        mSongType = songType;
        mLength = lenght;
    }

    public String getArtist()
    {
        return mArtist;
    }

    public void setArtist(String mArtist)
    {
        this.mArtist = mArtist;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getAlbum()
    {
        return mAlbum;
    }

    public void setAlbum(String mAlbum)
    {
        this.mAlbum = mAlbum;
    }

    public String getBlobKey()
    {
        return mBlobKey;
    }

    public Type getSongType()
    {
        return mSongType;
    }

    public boolean isSongInBlobStore()
    {
        return mInBlobStore;
    }
    
    public float getLength()
    {
        return mLength;
    }

}