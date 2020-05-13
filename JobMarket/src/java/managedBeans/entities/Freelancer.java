/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.entities;

import static database.DBConstants.*;
import database.exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Freelancer extends User {
    
    private static final String LOAD_BY_USERNAME = "SELECT * FROM FREELANCERS WHERE USERNAME = ? AND PASSWORD = ?";
    
    private String description;
    private int tokens;

    public Freelancer(int id, String username, String password, String description, int tokens) {
        super(id, username, password);
        this.description = description;
        this.tokens = tokens;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
    
    public static Freelancer load(String username, String password) throws SQLException, UserNotFoundException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(LOAD_BY_USERNAME);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet loaded = stmt.executeQuery();
        if (loaded.next()) {
            return new Freelancer(
                    loaded.getInt("ID"),
                    loaded.getString("USERNAME"),
                    loaded.getString("PASSWORD"),
                    loaded.getString("DESCRIPTION"),
                    loaded.getInt("TOKENS")
            );
        }
        throw new UserNotFoundException("User: " + username + " not found!");
    }
    
}
