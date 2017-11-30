package com.laserkoala.htf.Model;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;

/**
 * Created by dennis on 30.11.17.
 */

public class UserLocation {
    private int userId;
    private double latitude;
    private double longitude;
    private Date date;

    public UserLocation(int userId, double latitude, double longitude, Date date) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
