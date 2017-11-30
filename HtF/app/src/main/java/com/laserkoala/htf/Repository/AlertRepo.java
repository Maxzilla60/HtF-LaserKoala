package com.laserkoala.htf.Repository;

import com.laserkoala.htf.Model.Alert;

import java.util.List;
import com.google.android.gms.maps.model.LatLng;

public class AlertRepo {
    public List<Alert> getAllAlerts() {
        return new ArrayList<Alert> ();
    }
    public Alert getAlertByID(int id) {
        return null;
    }
    public void pushNewAlert(Alert alert) {
        // ...
    }
    
}