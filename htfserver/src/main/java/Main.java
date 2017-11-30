import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

import static spark.Spark.*;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/htf";

    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        get("/dennis", (req, res) -> {
            try {
                Class.forName(JDBC_DRIVER);
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                String sql = "INSERT INTO users (username, password, admin) VALUES (?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "Dennis");
                stmt.setString(2, "Dennis");
                stmt.setBoolean(3, true);

                stmt.executeUpdate();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return "Dennis toegevoegd?";
        });

        get("/users", (req, res) -> getAllUsers(req, res));

        put("/userlocation", (req, res) -> putUserLocation(req, res));

        get("/latestlocation", (req, res) -> getLatestLocations(req, res));
    }

    private static Object getAllUsers(Request req, Response res) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT username, password, admin FROM users";
            Statement stmt = conn.createStatement();

            ResultSet results = stmt.executeQuery(sql);

            JSONArray jsonArray = new JSONArray();
            while (results.next()) {
                String username = results.getString("username");
                String password = results.getString("password");
                boolean admin = results.getBoolean("admin");

                jsonArray.put(new JSONObject()
                        .put("username", username)
                        .put("password", password)
                        .put("admin", admin));
            }
            res.type("application/json");
            return jsonArray.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Oeps";
        }
    }

    private static Object putUserLocation(Request req, Response res) {
        if (req.body() == null || req.body() == "") {
            res.status(400);
            return "No request body";
        }
        JSONObject body = new JSONObject(req.body());

        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO user_location (user_id, longitude, latitude) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, body.getInt("user_id"));
            stmt.setDouble(2, body.getDouble("longitude"));
            stmt.setDouble(3, body.getDouble("latitude"));

            stmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "User-Location toegevoegd?";
    }

    private static Object getLatestLocations(Request req, Response res) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String ids_sql = "SELECT id FROM users";
            String location_sql = "SELECT longitude, latitude FROM user_location WHERE user_id = ? ORDER BY timestamp DESC LIMIT 1";
            Statement ids_stmt = conn.createStatement();

            ResultSet ids_results = ids_stmt.executeQuery(ids_sql);

            JSONArray jsonArray = new JSONArray();
            while (ids_results.next()) {
                int id = ids_results.getInt("id");

                PreparedStatement location_stmt = conn.prepareStatement(location_sql);
                location_stmt.setInt(1, id);
                ResultSet location_results = location_stmt.executeQuery();
                if (location_results.next()) {
                    jsonArray.put(new JSONObject()
                            .put("user_id", id)
                            .put("longitude", location_results.getDouble("longitude"))
                            .put("latitude", location_results.getDouble("latitude"))
                    );
                }
            }
            res.type("application/json");
            return jsonArray.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Oeps";
        }
    }
}