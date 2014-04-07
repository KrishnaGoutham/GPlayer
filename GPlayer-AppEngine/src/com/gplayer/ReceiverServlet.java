package com.gplayer;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

public class ReceiverServlet extends HttpServlet 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9191469684268965937L;
    private static final Logger log = Logger.getLogger(ReceiverServlet.class.getName());

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
				
		String channelKey = req.getParameter("channelKey");
	    String message = req.getParameter("message");
	    
	    log.info(message);
	    
	    //Send a message based on the 'channelKey' any channel with this key will receive the message
	    ChannelService channelService = ChannelServiceFactory.getChannelService();
	    channelService.sendMessage(new ChannelMessage(channelKey, message));
	}
	

}
