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
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class representing a Proovider type user
 * @author User
 */
public class Provider extends User{
    
    // SQL statements
    private static final String LOAD_ALL = "SELECT * FROM PROVIDERS";
    private static final String LOAD_BY_USERNAME = "SELECT * FROM PROVIDERS WHERE USERNAME = ? AND PASSWORD = ?";
    private static final String INSERT = "INSERT INTO PROVIDERS (USERNAME, PASSWORD)"
            + "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM PROVIDERS WHERE ID = ?";
    private static final String DELETE_JOBS = "DELETE FROM JOBS WHERE PROVIDER = ?";

    public Provider(int id, String username, String password) {
        super(id, username, password);
    }

    public Provider(String username, String password) {
        super(username, password);
    }
    
    /**
     * Convert the current entry in a ResultSet to a Provider
     * @param rs,  ResultSet currently looking at correct entry
     * @return a new Provider
     * @throws SQLException 
     */
    private static Provider resultSetToProvider(ResultSet rs) throws SQLException {
        return new Provider(
                    rs.getInt("ID"),
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD")
            );
    }
    
    /**
     * Load a provider using Username and password
     * @param username
     * @param password
     * @return the loaded provider
     * @throws SQLException
     * @throws UserNotFoundException 
     */
    public static Provider load(String username, String password) throws SQLException, UserNotFoundException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(LOAD_BY_USERNAME);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet loaded = stmt.executeQuery();
        if (loaded.next()) {
            return resultSetToProvider(loaded);
        }
        throw new UserNotFoundException("User: " + username + " not found!");
    }
    
    /**
     * Get all providers
     * @return ArrayList of all providers
     * @throws SQLException 
     */
    public static ArrayList<Provider> loadAll() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement stmt = conn.createStatement();
        
        ResultSet found = stmt.executeQuery(LOAD_ALL);
        ArrayList<Provider> loaded = new ArrayList();
        while (found.next()) {
            loaded.add(resultSetToProvider(found));
        }
        return loaded;
    }
    
    /**
     * Get the jobs associated with the provider
     * @return
     * @throws SQLException 
     */
    public ArrayList<Job> fetchJobs() throws SQLException {
        return Job.getByProvider(this.getId());
    }
    
    /**
     * Save this provicer to the database
     * @throws SQLException 
     */
    public void save() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(INSERT);
        
        stmt.setString(1, this.username);
        stmt.setString(2, this.password);
        
        stmt.executeUpdate();
    }
    
    /**
     * Delete a provider from the database
     * @param id of provider to delete
     * @throws SQLException 
     */
    public static void delete(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(DELETE);
        PreparedStatement stmtJob = conn.prepareStatement(DELETE_JOBS);
        // Delete jobs owned by this provider
        stmtJob.setInt(1, id);
        stmtJob.executeUpdate();
        // Delete provider
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}
