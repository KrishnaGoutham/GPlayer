package com.gplayer;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * An entity for Android device information.
 * 
 * Its associated endpoint, DeviceInfoEndpoint.java, was directly generated from
 * this class - the Google Plugin for Eclipse allows you to generate endpoints
 * directly from entities!
 * 
 * DeviceInfoEndpoint.java will be used for registering devices with this App
 * Engine application. Registered devices will receive messages broadcast by
 * this application over Google Cloud Messaging (GCM). If you'd like to take a
 * look at the broadcasting code, check out MessageEndpoint.java.
 * 
 * For more information, see
 * http://developers.google.com/eclipse/docs/cloud_endpoints.
 * 
 * NOTE: This DeviceInfoEndpoint.java does not use any form of authorization or
 * authentication! If this app is deployed, anyone can access this endpoint! If
 * you'd like to add authentication, take a look at the documentation.
 */
@Entity
// DeviceInfoEndpoint has NO AUTHENTICATION - it is an OPEN ENDPOINT!
public class DeviceInfo
{

    /*
     * The Google Cloud Messaging registration token for the device. This token
     * indicates that the device is able to receive messages sent via GCM.
     */
    @Id
    private String mDeviceRegistrationID;

    /*
     * Some identifying information about the device, such as its manufacturer
     * and product name.
     */
    private String mDeviceInformation;

    /*
     * Phone number of the current active sim in the phone.
     */
    private String mPhoneNumber;

    /*
     * Timestamp indicating when this device registered with the application.
     */
    private long mTimestamp;

    public String getDeviceRegistrationID()
    {
        return mDeviceRegistrationID;
    }

    public String getDeviceInformation()
    {
        return this.mDeviceInformation;
    }

    public void setDeviceRegistrationID(String deviceRegistrationID)
    {
        this.mDeviceRegistrationID = deviceRegistrationID;
    }

    public void setDeviceInformation(String deviceInformation)
    {
        this.mDeviceInformation = deviceInformation;
    }

    public long getTimestamp()
    {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.mTimestamp = timestamp;
    }

    public String getPhoneNumber()
    {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber)
    {
        this.mPhoneNumber = mPhoneNumber;
    }
}
