/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.Collection;
import java.util.List;
import model.entity.Freelancer;
import model.entity.Job;
import model.entity.Keyword;
import model.entity.Provider;

/**
 *
 * @author darra
 */
public interface JobDAO extends UserDAO<Job> {
    
    //Still not sure how well the inheritance of functions works,
    //add and remove may be useless as they are persist and remove from
    //superclass UserDAO.
    
    public Job add(int id, String description, Collection<Freelancer> freelancersCollection, Collection<Keyword> keywordsCollection, 
            Freelancer assignedTo, Provider creator);
    
    //public void remove(int job_id);
    
    public Job update();
    
    public Job findById(int id) ;
    
    public List getAllJobs();
    
    public List getOpenJobs();  
    
    public List getJobsOwnedByProvider(int provider_id);
    
    public Job findByDesc(String description) ;


    
}
