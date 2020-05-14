/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import database.exceptions.UserNotFoundException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import managedBeans.entities.Job;
import managedBeans.entities.Provider;

/**
 * Session bean for managing Provider user sessions
 * @author User
 */
@Named(value = "providerSession")
@SessionScoped
public class ProviderSession extends Login implements Serializable {
    
    // Logged in user
    Provider user;
    // Job to focus on if a specifiic job is requested
    Job focusJob;
    
    public ProviderSession() {
    }

    /**
     * Try to login the user using username and password
     * @return String of web page to redirect user to 
     */
    @Override
    public String login() {
        try {
            this.setUser(Provider.load(this.username, this.password));
            return "ProviderHomePage";
        } catch (UserNotFoundException e) {
            return "failedLogin";
        } catch (SQLException e) {
            return "sqlexception"; // TODO add SQLException logging
        }
    }
    
    /**
     * Get all jobs associated with the current user
     * @return 
     */
    public ArrayList<Job> fetchJobs() {
        try {
            return this.getUser().fetchJobs();
        } catch (SQLException e) {
            return new ArrayList(); // TODO Log SQLException
        }
    }
    
    /**
     * View a specific job
     * @param j job to view info of
     * @return String of web page to direct to
     */
    public String viewJob(Job j) {
        this.focusJob = j;
        return "FocusJob";
    }
    
    /**
     * Accept a bid on the currently viewed job
     * @param freelancerID id of freelancer making the bid
     * @return String of web page to redirect to
     */
    public String acceptBid(int freelancerID) {
       try {
           this.focusJob.acceptBid(freelancerID);
           return "FocusJob";
       } catch (SQLException e) {
           return "sqlexception"; // TODO Log SQLException
       }
    }
    
    /**
     * Mark the currently viewed job as completed
     * @return String of web page to redirect to
     */
    public String completeJob() {
        try {
            this.focusJob.finished();
            return "FocusJob";
        } catch (SQLException e) {
            return "sqlexception"; // TODO Log SQLException
        }
    }

    public Provider getUser() {
        return user;
    }

    public void setUser(Provider user) {
        this.user = user;
    }

    public Job getFocusJob() {
        return focusJob;
    }

    public void setFocusJob(Job focusJob) {
        this.focusJob = focusJob;
    }
    
}
