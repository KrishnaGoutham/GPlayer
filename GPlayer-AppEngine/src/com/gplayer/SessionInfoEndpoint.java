package com.gplayer;

import java.util.UUID;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(name = "sessioninfoendpoint", namespace = @ApiNamespace(ownerDomain = "gplayer.com", ownerName = "gplayer.com", packagePath = ""))
public class SessionInfoEndpoint
{
    @ApiMethod(name = "getClientSessionData")
    public ClientSessionData getClientSessionData(@Named("sessionId") String sessionID,@Named("clientId") String clientUUID) throws UnauthorizedException
    {
        SessionManager sessionManager = SessionManager.getInstance();
        ClientSessionData sessionData = null;
        if (sessionManager.isSessionValid(UUID.fromString(sessionID)))
        {
            sessionData = new ClientSessionData();
            SetClientSessiondata(sessionData, sessionID, clientUUID);
        }
        else {
            throw new UnauthorizedException("Invalid sessionid "+ sessionID);
        }
        
        return sessionData;
    }
    
    private void SetClientSessiondata(ClientSessionData clientSessionData, String sessionID, String clientUUID)
    {
        SessionManager sessionManager = SessionManager.getInstance();
        Session session = sessionManager.getSession(UUID.fromString(sessionID));
        
        if (session != null)
        {
            clientSessionData.setDiffList(session.getDiffPlayList(clientUUID));
            clientSessionData.setCurrentSong(session.getCurrentSong());
        } 
    }
}
