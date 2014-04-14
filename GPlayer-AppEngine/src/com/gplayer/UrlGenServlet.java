package com.gplayer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UrlGenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7429557011841270753L;
	private BlobstoreService mblobStoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	private static final String SUCCESS_PATH = "/upload";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Create a URL for blobstore and return.
		String postUrl = mblobStoreService.createUploadUrl(SUCCESS_PATH);
		
		String response =  "{\"status\":\"success\",\"url\":\""+postUrl+"\"}";
		
		//Send the client the upload url.
	    resp.getWriter().write(response);	
	}

}
