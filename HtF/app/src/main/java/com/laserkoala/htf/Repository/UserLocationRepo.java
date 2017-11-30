package com.laserkoala.htf.Repository;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laserkoala.htf.Model.UserLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dennis on 30.11.17.
 */

public class UserLocationRepo {
    public interface UserLocationUpdateListener {
        void onUserLocationUpdate(ArrayList<UserLocation> userLocations);
    }

    public static void getAllUsersLastLocation(final UserLocationUpdateListener listener, RequestQueue queue) {
        String url = "http://" + /* TODO IP */ "/latestlocations";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray arr = new JSONArray(response);
                            ArrayList<UserLocation> userLocationList= new ArrayList<>();
                            for(int i = 0; i<arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                int user_id = obj.getInt("user_id");
                                double lat = obj.getDouble("latitude");
                                double lon = obj.getDouble("longitude");
                                Date date = Date.valueOf(obj.getString("date"));
                                userLocationList.add(new UserLocation(user_id, lat, lon, date));
                            }
                            listener.onUserLocationUpdate(userLocationList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    public static void addUserLocation(RequestQueue queue, UserLocation userLocation){
        String url = "http://" + /* TODO IP */ "/userlocation";

        final JSONObject userLocationJson = new JSONObject();
        try{
            userLocationJson.put("user_id", userLocation.getUserId());
            userLocationJson.put("latitude", userLocation.getLatitude());
            userLocationJson.put("longitude", userLocation.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i("test", "ErrorPost: " + error);
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return userLocationJson.toString().getBytes();
            }
        };
        queue.add(stringRequest);
    }

}
