/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import managedBeans.entities.Freelancer;
import managedBeans.entities.Job;

/**
 * Bean to manage updating, loading and deletion of jobs.
 * request scoped
 * @author User
 */
@Named(value = "jobBean")
@RequestScoped
public class JobBean {

    // All jobs found (Could be searched on user, status, etc.)
    private ArrayList<Job> jobs = new ArrayList();
    // instance variables to allow creation of new job
    private int id;
    private String title;
    private String description;
    private Job.JobStatus status;
    private int tokens;
    private int provider;
    private int freelancer;
        
    // Needs to inject a FreelancerSession for applyig to jobs
    @ManagedProperty(value="#{freelancerSession}")
    @Inject
    private FreelancerSession freelancerSession;
    
    // Instantiate jobs to all jobs
    public JobBean() {
        try {
            this.jobs.addAll(Job.getAll());
        } catch (SQLException e) {
            // if no jobs are found then keep list empty
        }
    }
    
    /**
     * Get all jobs by their status i.e. OPEN, CLOSED, COMPLETED
     * @param status, JobStatus to search on
     * @return ArrayList of found jobs
     */
    public ArrayList<Job> getByStatus(Job.JobStatus status) {
        ArrayList<Job> filtered = new ArrayList();
        for (Job j : this.jobs) {
            if (j.getStatus().equals(status)) {
                filtered.add(j);
            }
        }
        return filtered;
    }
    
    /**
     * Get all jobs created by a provider
     * @param providerID Id of provider
     * @return ArrayList of found jobs
     */
    public ArrayList<Job> getByProvider(int providerID) {
        ArrayList<Job> filtered = new ArrayList();
        for (Job j : this.jobs) {
            if (j.getProvider() == providerID) {
                filtered.add(j);
            }
        }
        return filtered;
    }
    
    /**
     * Get all job bids by freelancers to do some job
     * @param j job to get bids for
     * @return ArrayLiist of freelancers bidding to do the job
     */
    public ArrayList<Freelancer> getBids(Job j) {
        try {
            return j.getApplications();
        } catch (SQLException e) {
            return new ArrayList();
        }
    }
    
    /**
     * Save a job created by a Provider
     * @param creatorID ID of provider who created the job
     * @return String web page to redirect user to
     */
    public String save(int creatorID) {
        try {
            new Job(
                    this.title,
                    this.description,
                    this.status,
                    this.tokens,
                    creatorID
            ).save();
            return "ProviderJobs";
        } catch (SQLException e){
            return "sqlexception"; // TODO add SQLException logging
        }
    }
    
    /**
     * Delete a job by its ID
     * @param id of the job to be deleted
     * @return String of web page to redirect user to
     */
    public String deleteJob(int id) {
        try {
            Job.delete(id);
            return null; //reeturn to the page this was claled on
        } catch (SQLException e) {
            return "sqlexception"; // TODO add SQLException logging
        }
    }
    
    /**
     * Apply to a job, using the injected freelancer session user
     * @param j job to apply to
     * @return String of web page to redirect user to
     */
    public String applyToJob(Job j) {
        try {
            j.apply(this.freelancerSession.getUser().getId());
            return "JobsPage";
        } catch (SQLException e) {
            return "sqlexception"; // TODO add SQLException logging
        }
    }
    
    /**
     * Revoke application for a job, uses injected freelancer session uuser
     * @param j job to un-apply to
     * @return String of web page to redirect user to
     */
    public String unApplyToJob(Job j) {
        try {
            j.unApply(this.freelancerSession.getUser().getId());
            return "JobsPage";
        } catch (SQLException e) {
            return "sqlexception"; // TODO add SQLException logging
        }
    }
    
    /**
     * Checks if the user in injected freelancer session has applied to do this job already
     * @param j job to check application for
     * @return boolean
     */
    public boolean hasApplied(Job j) {
        try {
            return j.hasApplied(this.freelancerSession.getUser().getId());          
        } catch (SQLException e) {
            return false; // If can't find out don't render
        }
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
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

    public Job.JobStatus getStatus() {
        return status;
    }
    
    public Job.JobStatus[] getStatuses() {
        return Job.JobStatus.values();
    }

    public void setStatus(Job.JobStatus status) {
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

    public FreelancerSession getFreelancerSession() {
        return freelancerSession;
    }

    public void setFreelancerSession(FreelancerSession freelancerSession) {
        this.freelancerSession = freelancerSession;
    }
    
}
