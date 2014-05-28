package com.gplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*default*/class Session
{
    public enum Status {
        VALID, PAUSED, EXPIRED
    }

    private UUID         mSessionId;
    private Client       mHost;
    private List<Client> mGuests;
    private List<Song>   mPlayList;
    private int          mCurrentSong;
    private Status       mStatus;
    private float        mCurrentPos;

    public Session(Client mHost)
    {
        mGuests = new ArrayList<Client>();
        mPlayList = new ArrayList<Song>();
        mSessionId = UUID.randomUUID();
    }

    public Status getStatus()
    {
        return mStatus;
    }

    public void setStatus(Status status)
    {
        this.mStatus = status;
    }
    
    public UUID getSessionId()
    {
        return mSessionId;
    }

    public Client getHost()
    {
        return mHost;
    }
    
    public void addGuest(Client guest)
    {
        if(guest == null)
            throw new NullPointerException("Guest cannot be null");
        
        mGuests.add(guest);
    }
    
    public List<Client> getGuests()
    {
        return mGuests;
    }
    
    public Client getGuest(String mClientPhoneNum)
    {
        Client guest = null;
        
        for (Client client : mGuests)
        {
            if(client.getClientPhoneNum().equals(mClientPhoneNum))
            {
                guest = client;
                break;
            }
        }
        
        return guest;
    }
    
    public float getCurrentPos()
    {
        return mCurrentPos;
    }

    public void setCurrentPos(float currentPos)
    {
        this.mCurrentPos = currentPos;
    }


    public int getCurrentSong()
    {
        return mCurrentSong;
    }

    public void setCurrentSong(int currentSong)
    {
        this.mCurrentSong = currentSong;
    }
    
    public List<Song> getPlayList()
    {
        return mPlayList;
    }
    
    public List<Song> getDiffPlayList(String clientUUID)
    {
        List<Song> diffSongs = new ArrayList<Song>();
        Client guest = getGuest(clientUUID);
        
        if (guest != null)
        {
            List<Song> subList = mPlayList.subList(guest.getLastSyncdPlaylistIndex(), mPlayList.size()); 
            if(subList != null)
            {
                diffSongs.addAll(subList);
            }
                
        }
        return diffSongs;
    }

    class Client
    {        
        private String mClientPhoneNum;
        private String mClientGCMID;
        private int    mLastSyncdPlaylistIndex;

        public String getClientPhoneNum()
        {
            return mClientPhoneNum;
        }

        public String getGCMID()
        {
            return mClientGCMID;
        }

        public int getLastSyncdPlaylistIndex()
        {
            return mLastSyncdPlaylistIndex;
        }

        public void setLastSyncdPlaylistIndex(int mLastSyncdPlaylistIndex)
        {
            this.mLastSyncdPlaylistIndex = mLastSyncdPlaylistIndex;
        }

    }

}
