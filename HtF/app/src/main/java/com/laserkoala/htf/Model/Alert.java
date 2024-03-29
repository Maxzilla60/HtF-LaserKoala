package com.laserkoala.htf.Model;

import android.location.Location;
import android.media.Image;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;
import java.time.LocalDateTime;

public class Alert {
    private int id;
    private String username;
    private String description;
    private LatLng location;
    private Image photo;
    private Date dateTime;

    public Alert(int id, String username, String description, LatLng location, Image photo, Date dateTime) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.location = location;
        this.photo = photo;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}