package com.gplayer;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

public class DataChannelServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -483782284393789188L;
    private static final Logger log = Logger.getLogger(DataChannelServlet.class.getName());

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//super.doGet(req, resp);
		
		String channelKey = req.getParameter("c");
		
		log.info("in DataChannelServlet");
		
		//Create a Channel using the 'channelKey' we received from the client
	    ChannelService channelService = ChannelServiceFactory.getChannelService();
	    String token = channelService.createChannel(channelKey);
	    
	    //Send the client the 'token' + the 'channelKey' this way the client can start using the new channel
	    resp.setContentType("text/html");
	    StringBuffer sb = new StringBuffer();
	    sb.append("{ \"channelKey\":\"" + channelKey + "\",\"token\":\"" + token + "\"}");
	    
	    resp.getWriter().write(sb.toString());
		
	}

}
