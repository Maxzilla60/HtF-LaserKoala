package com.laserkoala.htf.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.laserkoala.htf.Model.UserLocation;
import com.laserkoala.htf.R;
import com.laserkoala.htf.Repository.UserLocationRepo;

import java.util.ArrayList;

public class AdminUserTrackActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        UserLocationRepo.UserLocationUpdateListener{

    private GoogleMap mMap;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private boolean canAccessLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_track);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        initLocationListener();

        mMap.setOnMarkerClickListener(this);

        startTracking();
    }

    private void startTracking() {
        final Handler handler = new Handler();
        final int delay = 5000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                placeUserMarkersOnMap();
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private final void placeUserMarkersOnMap(){
        UserLocationRepo.getAllUsersLastLocation(this, Volley.newRequestQueue(this));
    }

    private void initLocationListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        // Check if we have permission to get the device's location
        if (Build.VERSION.SDK_INT < 23) {
            onCanAccessLocation();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                onCanAccessLocation();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void onCanAccessLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);

            Location lastKnownLoc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLoc != null)
                centerMapOnLocation(lastKnownLoc);

            mMap.setMyLocationEnabled(true);

            this.canAccessLocation = true;
        }


    }

    public void centerMapOnLocation(Location location){
        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
        centerMapOnLocation(loc);
    }

    public void centerMapOnLocation(LatLng location){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onUserLocationUpdate(ArrayList<UserLocation> userLocations) {
        mMap.clear();
        for (UserLocation userLocation: userLocations) {
            LatLng latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());

            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(String.valueOf(userLocation.getUserId())));
            marker.setTag(userLocation.getUserId());
        }
    }
}