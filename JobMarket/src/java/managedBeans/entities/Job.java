/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.entities;

import static database.DBConstants.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Job {
    
    private static final String GET_BY_PROVIDER = "SELECT * FROM JOBS WHERE PROVIDER = ?";
    private static final String GET_ALL = "SELECT * FROM JOBS";
    private static final String UPDATE_STATUS = "UPDATE JOBS SET STATUS = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM JOBS WHERE ID = ?";
    
    public enum JobStatus {
        OPEN, CLOSED, COMPLETED
    }
    
    private int id;
    private String title;
    private String description;
    private JobStatus status;
    private int tokens;
    private int provider;
    private int freelancer;

    public Job(int id, String title, String description, JobStatus status, int tokens, int provider) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.tokens = tokens;
        this.provider = provider;
    }
    
    private static Job resultSetToJob(ResultSet rs) throws SQLException {
        Job temp = new Job(
                    rs.getInt("ID"),
                    rs.getString("TITLE"),
                    rs.getString("DESCRIPTION"),
                    JobStatus.valueOf(rs.getString("STATUS")),
                    rs.getInt("TOKENS"),
                    rs.getInt("PROVIDER")
            );
        int freelancer = rs.getInt("FREELANCER");
        temp.setFreelancer(rs.wasNull() ? null : freelancer);
        return temp;
    }
    
    private static ArrayList<Job> resultSetToArrayList(ResultSet rs) throws SQLException {
        ArrayList<Job> found = new ArrayList();
        while (rs.next()) {
            found.add(resultSetToJob(rs));
        }
        return found;
    }
    
    public static ArrayList<Job> getAll() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(GET_ALL);
        return resultSetToArrayList(rs);
    }
    
    public static ArrayList<Job> getByProvider(int providerID) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(GET_BY_PROVIDER);
        stmt.setInt(1, providerID);
        ResultSet rs = stmt.executeQuery();
        return resultSetToArrayList(rs);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(int freelancer) {
        this.freelancer = freelancer;
    }
    
    
}
