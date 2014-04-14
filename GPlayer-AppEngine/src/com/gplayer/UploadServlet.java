package com.gplayer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 2628389745105886686L;
	private BlobstoreService mblobStoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String response;
		List<BlobKey> blobs = mblobStoreService.getUploads(req).get("file");
		
		if (blobs.size() > 0) {
			BlobKey blobKey = blobs.get(0);
			response =  "{\"status\":\"success\",\"blob-key\":\""+blobKey.getKeyString()+"\"}";
		} else {
			response =  "{\"status\":\"fail\",\"error\":\"No parameter file in request.\"}";
		}
		
		resp.getWriter().write(response);
	}
}
