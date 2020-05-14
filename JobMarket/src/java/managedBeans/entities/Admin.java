/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.entities;

import static database.DBConstants.DB_PASS;
import static database.DBConstants.DB_URL;
import static database.DBConstants.DB_USER;
import database.exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent admin user
 * @author User
 */
public class Admin extends User{
    
    // SQL statments
    private static final String LOAD_BY_USERNAME = "SELECT * FROM ADMINS WHERE USERNAME = ? AND PASSWORD = ?";
    private static final String CHECK_ID = "SELECT * FROM ADMINS WHERE ID = ?";

    public Admin(int id, String username, String password) {
        super(id, username, password);
    }
    
    /**
     * Loads an admin from the database using username and password
     * @param username
     * @param password
     * @return Admin user
     * @throws SQLException
     * @throws UserNotFoundException 
     */
    public static Admin load(String username, String password) throws SQLException, UserNotFoundException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(LOAD_BY_USERNAME);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet loaded = stmt.executeQuery();
        if (loaded.next()) {
            return new Admin(
                    loaded.getInt("ID"),
                    loaded.getString("USERNAME"),
                    loaded.getString("PASSWORD")
            );
        }
        throw new UserNotFoundException("User: " + username + " not found!");
    }
    
    /**
     * Checks that an ID is that of a valid admin
     * @param id
     * @return boolean
     * @throws SQLException 
     */
    public static boolean validAdminID(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(CHECK_ID);
        
        stmt.setInt(1, id);      
        ResultSet rs = stmt.executeQuery();
        
        return rs.next();
    }
 
}
