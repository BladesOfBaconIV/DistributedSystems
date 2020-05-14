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
    private static final String INSERT = "INSERT INTO JOBS "
            + "(TITLE, DESCRIPTION, STATUS, TOKENS, PROVIDER, FREELANCER) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String APPLY = "INSERT INTO JOB_BIDS (JOB_ID, FREELANCER_ID)"
            + " VALUES (?, ?)";
    private static final String UNAPPLY = "DELETE FROM JOB_BIDS WHERE JOB_ID = ? "
            + "AND FREELANCER_ID = ?";
    private static final String HAS_APPLIED = "SELECT * FROM JOB_BIDS WHERE "
            + "JOB_ID = ? AND FREELANCER_ID = ?";
    
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
    
    public Job(String title, String description, JobStatus status, int tokens, int provider) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.tokens = tokens;
        this.provider = provider;
    }
    
    public void save() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(INSERT);
        stmt.setString(1, this.title);
        stmt.setString(2, this.description);
        stmt.setString(3, this.status.name());
        stmt.setInt(4, this.tokens);
        stmt.setInt(5, this.provider);
        stmt.setObject(6, this.freelancer == 0 ? null : (Integer) this.freelancer);
        stmt.executeUpdate();
    }
    
    public void updateStatus() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(UPDATE_STATUS);
        stmt.setString(1, this.status.name());
        stmt.setInt(2, this.id);
        stmt.executeUpdate();
    }
    
    public static void delete(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
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
        temp.setFreelancer(rs.wasNull() ? -1 : freelancer);
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
    
    public void apply(int freelancerID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(APPLY);
        stmt.setInt(1, this.id);
        stmt.setInt(2, freelancerID);
        stmt.executeUpdate();
    }
    
    public void unApply(int freelancerID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(UNAPPLY);
        stmt.setInt(1, this.id);
        stmt.setInt(2, freelancerID);
        stmt.executeUpdate();
    }
    
    public boolean hasApplied(int freelancerID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(HAS_APPLIED);
        stmt.setInt(1, this.id);
        stmt.setInt(2, freelancerID);
        ResultSet applications = stmt.executeQuery();
        return applications.next();
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
