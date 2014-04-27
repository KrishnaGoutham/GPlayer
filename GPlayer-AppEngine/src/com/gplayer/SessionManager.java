package com.gplayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager
{
    private static SessionManager sInstance;
    private Map<UUID, Session> mSessionMap;
    
    
    public static SessionManager getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new SessionManager();
        }
        
        return sInstance;
    }
    
    private SessionManager()
    {
        mSessionMap = new HashMap<UUID, Session>();
    }
    
    public Session getSession (UUID sessionId)
    {
        if (mSessionMap.containsKey(sessionId))
        {
            return mSessionMap.get(sessionId);
        } else {
            return null;
        }
    }
    
    public boolean isSessionValid (UUID sessionId)
    {
        if (!mSessionMap.containsKey(sessionId))
            return false;
        
        return (mSessionMap.get(sessionId).getStatus() != Session.Status.EXPIRED) ? true : false; 
    }
    
}
