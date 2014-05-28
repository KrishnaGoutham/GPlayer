package com.gplayer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.PhoneNumber;

/**
 * An entity for Application User information.
 * 
 * Its associated endpoint, UserInfoEndpoint.java, was directly generated from
 * this class - the Google Plugin for Eclipse allows you to generate endpoints
 * directly from entities!
 * 
 * UserInfoEndpoint.java will be used to get and update the information of the 
 * user like status messages, last seen time and current availability.
 * 
 * For more information, see
 * http://developers.google.com/eclipse/docs/cloud_endpoints.
 * 
 * NOTE: This UserInfoEndpoint.java does not use any form of authorization or
 * authentication! If this app is deployed, anyone can access this endpoint! If
 * you'd like to add authentication, take a look at the documentation.
 */
@Entity
// UserInfoEndpoint has NO AUTHENTICATION - it is an OPEN ENDPOINT!
public class UserInfo
{
    /*
     * The phone number of the user that is used to uniquely 
     * identify the user.
     */
    @Id
    private PhoneNumber mPhoneNumber;
    
    /*
     * Time stamp of when this user was active last time. 
     */
    private Date mLastSeenTime;

    public PhoneNumber getPhoneNumber()
    {
        return mPhoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber)
    {
        this.mPhoneNumber = phoneNumber;
    }
    
    public Date getLastSeenTime()
    {
        return mLastSeenTime;
    }

    public void setLastSeenTime(Date lastSeenTime)
    {
        this.mLastSeenTime = lastSeenTime;
    }
}
