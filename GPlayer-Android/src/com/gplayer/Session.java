package com.gplayer;

import java.util.HashMap;
import java.util.Map;

import android.net.Uri;

public class Session 
{
	private String mSessionId;
	private String mCurrent;
	private Map<String,Uri> mSongs;
	
	public Session() 
	{
		mSongs = new HashMap<String,Uri>();
		mCurrent = null;
	}
	
	/**
	 * Ask the server to create a session and save the session id.
	 * 
	 * @Note At any time there can be only one active session. If a session is
	 *       already present then old session is destroyed.
	 * @return true if session was created successfully else false.
	 */
	public boolean create()
	{
		return false;
	}
	
	/**
	 * Destroys the local session info and notifies the server about it.
	 * 
	 * @return true if session was created successfully else false.
	 */
	public boolean destroy()
	{
		return false;
	}
	
	/**
	 * Attaches the client to session in the server and updates session info in
	 * client.
	 * 
	 * @param sessionId
	 *            that client should attach to.
	 * @return true if client was succssful in connecting to server session else
	 *         false.
	 */
	public boolean attachToSession(String sessionId)
	{
		return false;
	}
	
	/**
	 * Refreshes the client session info by connecting to the server. 
	 * Should be called when server notifies data change.
	 * 
	 * @return true if successful else false. 
	 */
	public boolean refresh()
	{
		return false;
	}
	
}
