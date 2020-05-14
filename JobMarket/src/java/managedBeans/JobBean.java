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
import managedBeans.entities.Job;

/**
 *
 * @author User
 */
@Named(value = "jobBean")
@RequestScoped
public class JobBean {

    private ArrayList<Job> jobs = new ArrayList();
    private int id;
    private String title;
    private String description;
    private Job.JobStatus status;
    private int tokens;
    private int provider;
    private int freelancer;
        
    @ManagedProperty(value="#{adminSession}")
    @Inject
    private FreelancerSession freelancerSession;
    
    public JobBean() {
        try {
            this.jobs.addAll(Job.getAll());
        } catch (SQLException e) {
            // if no jobs are found then keep list empty
        }
    }
    
    public ArrayList<Job> getByStatus(Job.JobStatus status) {
        ArrayList<Job> filtered = new ArrayList();
        for (Job j : this.jobs) {
            if (j.getStatus().equals(status)) {
                filtered.add(j);
            }
        }
        return filtered;
    }
    
    public ArrayList<Job> getByProvider(int providerID) {
        ArrayList<Job> filtered = new ArrayList();
        for (Job j : this.jobs) {
            if (j.getProvider() == providerID) {
                filtered.add(j);
            }
        }
        return filtered;
    }
    
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
            return e.getMessage();
        }
    }
    
    public String deleteJob(int id) {
        try {
            Job.delete(id);
            return "ProviderJobs";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public String applyToJob(Job j) {
        try {
            j.apply(this.freelancerSession.getUser().getId());
            return "JobsPage";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public String unApplyToJob(Job j) {
        try {
            j.unApply(this.freelancerSession.getUser().getId());
            return "JobsPage";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
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
