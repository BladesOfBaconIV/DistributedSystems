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
 * Class representing a job
 * @author User
 */
public class Job {
    
    // SQLL Statements
    private static final String GET_BY_PROVIDER = "SELECT * FROM JOBS WHERE PROVIDER = ?";
    private static final String GET_ALL = "SELECT * FROM JOBS";
    private static final String GET_BIDS = "SELECT * FROM JOB_BIDS WHERE JOB_ID = ?";
    private static final String UPDATE_STATUS = "UPDATE JOBS SET STATUS = ? WHERE ID = ?";
    private static final String UPDATE_FREELANCER = "UPDATE JOBS SET FREELANCER = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM JOBS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO JOBS "
            + "(TITLE, DESCRIPTION, STATUS, TOKENS, PROVIDER, FREELANCER) VALUES (?, ?, ?, ?, ?, ?)";
   // private static final String INSERT_KEYWORDS = "INSERT INTO KEYWORDS (JOB_ID, KEYWORD)"
     //       + " VALUES (?, ?)"; // TODO add support for keywords
    private static final String APPLY = "INSERT INTO JOB_BIDS (JOB_ID, FREELANCER_ID)"
            + " VALUES (?, ?)";
    private static final String UNAPPLY = "DELETE FROM JOB_BIDS WHERE JOB_ID = ? "
            + "AND FREELANCER_ID = ?";
    private static final String HAS_APPLIED = "SELECT * FROM JOB_BIDS WHERE "
            + "JOB_ID = ? AND FREELANCER_ID = ?";
    private static final String REMOVE_APPLICATIONS = "DELETE FROM JOB_BIDS WHERE JOB_ID = ?";
    
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
    // private String[] keywords; // TODO

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
    
    /**
     * Save the job to the database
     * @throws SQLException 
     */
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
    
    /**
     * Update the status of a job
     * @param js the new JobStatus
     * @throws SQLException 
     */
    public void updateStatus(JobStatus js) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(UPDATE_STATUS);
        stmt.setString(1, js.name());
        stmt.setInt(2, this.id);
        stmt.executeUpdate();
    }
    
    /**
     * Delete a job from the database
     * @param id
     * @throws SQLException 
     */
    public static void delete(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    
    /**
     * Convert the current entry in a ResultSet to a job
     * @param rs Resultset currently pointing at entry to load
     * @return a new Job
     * @throws SQLException 
     */
    private static Job resultSetToJob(ResultSet rs) throws SQLException {
        Job temp = new Job(
                    rs.getInt("ID"),
                    rs.getString("TITLE"),
                    rs.getString("DESCRIPTION"),
                    JobStatus.valueOf(rs.getString("STATUS")),
                    rs.getInt("TOKENS"),
                    rs.getInt("PROVIDER")
            );
        // Check if the freelancer id was NULL in table, i.e. not assigned yet
        // and if not assigned set id to -1, (an impossible freelancer id)
        int freelancer = rs.getInt("FREELANCER");
        temp.setFreelancer(rs.wasNull() ? -1 : freelancer);
        return temp;
    }
    
    /**
     * Convert a ResultSet to an ArrayList
     * @param rs ResultSet currently not pointing to first entry
     * @return ArrayList of Jobs
     * @throws SQLException 
     */
    private static ArrayList<Job> resultSetToArrayList(ResultSet rs) throws SQLException {
        ArrayList<Job> found = new ArrayList();
        while (rs.next()) {
            found.add(resultSetToJob(rs));
        }
        return found;
    }
    
    /**
     * Get all jobs
     * @return ArrayList of all jobs
     * @throws SQLException 
     */
    public static ArrayList<Job> getAll() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(GET_ALL);
        return resultSetToArrayList(rs);
    }
    
    /**
     * Get jobs by provider id
     * @param providerID, id of provider
     * @return ArrayList of jobs
     * @throws SQLException 
     */
    public static ArrayList<Job> getByProvider(int providerID) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(GET_BY_PROVIDER);
        stmt.setInt(1, providerID);
        ResultSet rs = stmt.executeQuery();
        return resultSetToArrayList(rs);
    }
    
    /**
     * Get all the applications made to do a job
     * @return ArrayList of Freelancers who have applied
     * @throws SQLException 
     */
    public ArrayList<Freelancer> getApplications() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(GET_BIDS);
        stmt.setInt(1, this.id);
        ResultSet bids = stmt.executeQuery();
        ArrayList<Freelancer> applicants = new ArrayList();
        while (bids.next()) {
            try {
                applicants.add(Freelancer.load(bids.getInt("FREELANCER_ID")));
            } catch (UserNotFoundException e) {
                // TODO log failed attempt.
            }
        }
        return applicants;
    }
    
    /**
     * Accept a job bid
     * @param freelancerID, id of freelancer who's bid to accept
     * @throws SQLException 
     */
    public void acceptBid(int freelancerID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmtFree = conn.prepareStatement(UPDATE_FREELANCER);
        PreparedStatement stmtBids = conn.prepareStatement(REMOVE_APPLICATIONS);
        // Set job to closed
        this.updateStatus(JobStatus.CLOSED);
        // Update the freelancer associated with this job
        stmtFree.setInt(1, freelancerID);
        stmtFree.setInt(2, this.id);
        stmtFree.executeUpdate();
        // Remove all other applications for this job
        stmtBids.setInt(1, this.id);
        stmtBids.executeUpdate();
    }
    
    /**
     * Mark a job as being done. Will update the status in the database, and
     * pay the freelancer
     * @throws SQLException 
     */
    public void finished() throws SQLException {
        this.updateStatus(JobStatus.COMPLETED); 
        try {
            Freelancer.load(this.freelancer).updateTokens(this.tokens);
        } catch (UserNotFoundException e) {
            // if not freelancer found don't pay anyone
        }
    }
    
    /**
     * Make an for a freelancer application for the job
     * @param freelancerID, id of freelancer applying
     * @throws SQLException 
     */
    public void apply(int freelancerID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(APPLY);
        stmt.setInt(1, this.id);
        stmt.setInt(2, freelancerID);
        stmt.executeUpdate();
    }
    
    /**
     * Remove a freelancers application to do the job
     * @param freelancerID, id of freelancer whose application to remove
     * @throws SQLException 
     */
    public void unApply(int freelancerID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(UNAPPLY);
        stmt.setInt(1, this.id);
        stmt.setInt(2, freelancerID);
        stmt.executeUpdate();
    }
    
    /**
     * Check if a freelancer has applied for a job
     * @param freelancerID, id of freelancer to check
     * @return boolean 
     * @throws SQLException 
     */
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
