package com.laserkoala.htf.Repository;

import com.laserkoala.htf.Model.User;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.model.LatLng;

public class UsersRepo {
    // private static final String USER = "root";
    // private static final String PASS = "";
    // private static final String DB_URL = "jdbc:mysql://localhost/htf";

    public List<User> getAllUsers() {
        // try {
        //     Class.forName("com.mysql.jdbc.Driver");
        //     Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        //     String sql = "INSERT INTO users (username, password, admin) VALUES (?,?,?)";
        //     PreparedStatement stmt = conn.prepareStatement(sql);
        //     stmt.setString(1, "Dennis");
        //     stmt.setString(2, "Dennis");
        //     stmt.setBoolean(3, true);

        //     ResultSet results = stmt.executeQuery();

        //     List<User> users;
        //     while (results.next()) {
        //         String username = results.getString("username");
        //         String password = results.getString("password");
        //         boolean admin = results.getBoolean("admin");
        //     }
        // }
        // catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }
        // return null;
        return new ArrayList<User>();
    }

    public User getUserByUsername(String username) {
        return new User(username, "password", false);
    }

    public LatLng getCurrentLocationByUsername(String username) {
        return null;
    }

    public List<LatLng> getLocationHistoryByUsername(String username) {
        return new ArrayList<LatLng> ();
    }

    public boolean checkUser(String username, String password) {
        return false;
    }

    public void pushNewUser(User user) {
        // ...
    }
}