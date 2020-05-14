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
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class representing freelancer user
 * @author User
 */
public class Freelancer extends User {
    
    // SQL statements
    private static final String LOAD_BY_USERNAME = "SELECT * FROM FREELANCERS WHERE USERNAME = ? AND PASSWORD = ?";
    private static final String LOAD_BY_ID = "SELECT * FROM FREELANCERS WHERE ID = ?";
    private static final String LOAD_ALL = "SELECT * FROM FREELANCERS";
    private static final String DELETE = "DELETE FROM FREELANCERS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO FREELANCERS (USERNAME, PASSWORD, DESCRIPTION, TOKENS)"
            + " VALUES (?, ?, ?, ?)";
    private static final String UPDATE_TOKENS = "UPDATE FREELANCERS SET TOKENS = ? WHERE ID = ?";
    
    private String description;
    private int tokens = 0;

    public Freelancer(int id, String username, String password, String description, int tokens) {
        super(id, username, password);
        this.description = description;
        this.tokens = tokens;
    }
    
    public Freelancer(String username, String password, String description) {
        super(username, password);
        this.description = description;
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
    
    /**
     * convert a ResultSet entry into a new Freelancer
     * @param rs ResultSet pointing to entry to load
     * @return a new Freelancer
     * @throws SQLException 
     */
    private static Freelancer resultSetToFreelancer(ResultSet rs) throws SQLException {
        return new Freelancer(
                    rs.getInt("ID"),
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD"),
                    rs.getString("DESCRIPTION"),
                    rs.getInt("TOKENS")
            );
    }
    
    /**
     * Loads a freelancer using a username and password
     * @param username
     * @param password
     * @return Loaded Freelancer
     * @throws SQLException
     * @throws UserNotFoundException 
     */
    public static Freelancer load(String username, String password) throws SQLException, UserNotFoundException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(LOAD_BY_USERNAME);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet loaded = stmt.executeQuery();
        if (loaded.next()) {
            return resultSetToFreelancer(loaded);
        }
        throw new UserNotFoundException("User: " + username + " not found!");
    }
    
    /**
     * Loads a Freelancer using their ID
     * @param id
     * @return a new Freelancer
     * @throws SQLException
     * @throws UserNotFoundException 
     */
    public static Freelancer load(int id) throws SQLException, UserNotFoundException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(LOAD_BY_ID);

        stmt.setInt(1, id);
        ResultSet loaded = stmt.executeQuery();
        if (loaded.next()) {
            return resultSetToFreelancer(loaded);
        }
        throw new UserNotFoundException("User: " + id + " not found!");
    }
    
    /**
     * Loads all the Freelancers
     * @return ArrayList of all freelancer
     * @throws SQLException 
     */
    public static ArrayList<Freelancer> loadAll() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement stmt = conn.createStatement();
        
        ResultSet found = stmt.executeQuery(LOAD_ALL);
        ArrayList<Freelancer> loaded = new ArrayList();
        while (found.next()) {
            loaded.add(resultSetToFreelancer(found));
        }
        return loaded;
    }
    
    /**
     * Save a Freelancer to the database
     * @throws SQLException 
     */
    public void save() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(INSERT);
        
        stmt.setString(1, this.username);
        stmt.setString(2, this.password);
        stmt.setString(3, this.description);
        stmt.setInt(4, this.tokens);
        
        stmt.executeUpdate();
    }
    
    /**
     * Update a Freelancers tokens, by adding on more to them
     * @param tokens, new tokens to add to total
     * @throws SQLException 
     */
    public void updateTokens(int tokens) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(UPDATE_TOKENS);
        stmt.setInt(1, this.tokens + tokens);
        stmt.setInt(2, this.id);
        stmt.executeUpdate();
    }
    
    /**
     * Delete a Freelancer bby id
     * @param id of freelancer to delete
     * @throws SQLException 
     */
    public static void delete(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(DELETE);      
        stmt.setInt(1, id);       
        stmt.executeUpdate();
    }
    
}
