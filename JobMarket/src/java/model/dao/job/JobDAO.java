/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.job;

import java.util.Collection;
import java.util.List;
import model.dao.DAO;
import model.entity.Freelancer;
import model.entity.Job;
import model.entity.Keyword;
import model.entity.Provider;

/**
 *
 * @author darra
 */
public interface JobDAO extends DAO<Job> {
    
    public Job add(int id, String description, Collection<Freelancer> freelancersCollection, Collection<Keyword> keywordsCollection, 
            Freelancer assignedTo, Provider creator);
    
    public List getOpenJobs();  
    
    public List getJobsOwnedByProvider(int provider_id);
    
    public Job findByDesc(String description) ;
  
}
