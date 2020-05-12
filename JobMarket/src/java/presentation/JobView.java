/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.dao.job.JobDAOImpl;
import model.entity.Freelancer;
import model.entity.Job;
import model.entity.Keyword;
import model.entity.Provider;

/**
 *
 * @author User
 */
@Named(value = "jobView")
@RequestScoped
public class JobView {
    
    private Job job;
    
    @EJB
    private JobDAOImpl jobDAO;

    /**
     * Creates a new instance of JobView
     */
    public JobView() {
        this.job = new Job();
    }
    
    public Job add(int id, String description, Collection<Freelancer> freelancersCollection, Collection<Keyword> keywordsCollection, 
            Freelancer assignedTo, Provider creator) {
        return jobDAO.add(id, description, freelancersCollection, keywordsCollection, assignedTo, creator);
    }
    
    public List getAll() {
        return jobDAO.getAll();
    }
    
    public Job findByID(int id) {
        return jobDAO.findByID(id);
    }
    
    public Job findByDesc(String desc) {
        return jobDAO.findByDesc(desc);
    }
    
    public List<Job> getJobsOwnedByProvider(int id) {
        return jobDAO.getJobsOwnedByProvider(id);
    }
    
    public List<Job> getOpenJobs() {
        return jobDAO.getOpenJobs();
    }
}
