package com.gplayer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class DownloadServlet extends HttpServlet
{

    private static final long serialVersionUID = -2894801387541597534L;
    private BlobstoreService mblobStoreService = BlobstoreServiceFactory
            .getBlobstoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));

        try {
            resp.setHeader("status", "success");
            mblobStoreService.serve(blobKey, resp);
        } catch (IOException e) {
            Log.info(this.getServletName() + ":Invalid blob-key" + blobKey);
            resp.setHeader("status", "failed");
        }
    }
}
