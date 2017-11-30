package com.laserkoala.htf.Model;

import android.location.Location;
import android.media.Image;

import java.time.LocalDateTime;

public class Alert {
    private int id;
    private String username;
    private String description;
    private Location location;
    private Image photo;
    private LocalDateTime dateTime;

    public Alert(int id, String username, String description, Location location, Image photo, LocalDateTime dateTime) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}