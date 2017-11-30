package com.laserkoala.htf.Repository;

import com.laserkoala.htf.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;


class UsersRepo {
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB_URL = "jdbc:mysql://localhost/htf";

    public List<User> getAllUsers() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO users (username, password, admin) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Dennis");
            stmt.setString(2, "Dennis");
            stmt.setBoolean(3, true);

            stmt.execute();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}