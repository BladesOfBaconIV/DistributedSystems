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
import managedBeans.entities.Job;

/**
 *
 * @author User
 */
@Named(value = "jobBean")
@RequestScoped
public class JobBean {

    private ArrayList<Job> jobs = new ArrayList();
    
    public JobBean() {
        try {
            this.jobs.addAll(Job.getAll());
        } catch (SQLException e) {
            // if no jobs are found then no keep empty list
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

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }
    
    
    
}
