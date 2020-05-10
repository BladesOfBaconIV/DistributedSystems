/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.job;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.dao.BaseJPADao;
import model.entity.Freelancer;
import model.entity.Job;
import model.entity.Keyword;
import model.entity.Provider;

/**
 *
 * @author darra
 */
public class JobDAOImpl extends BaseJPADao implements JobDAO{

    //Default no-arg constructor
    public JobDAOImpl() {
   
    }

    @Override
    public void persist(Job job) {
        
    }
    
    @Override
    public void remove(Job job) {
        
    }
    
    @Override
    public Job add(int id, String description, Collection<Freelancer> freelancersCollection, Collection<Keyword> keywordsCollection, 
            Freelancer assignedTo, Provider creator) {
        
        Job job = new Job();
        
        job.setId(id);
        job.setDesciption(description);
        job.setFreelancersCollection(freelancersCollection);
        job.setKeywordsCollection(keywordsCollection);
        job.setAssignedTo(assignedTo);
        job.setCreator(creator);
        
        //job.setState("Open"); //Think we need a column in our data table for the Job
                              //being open, closed, complete?
        
        //Potential alternative method to set job - need a getMaxId method somewhere
        Long maxId = getMaxId("SELECT max(j.id) FROM Job j");
        job.setId((int) ((maxId == null)? 0 : maxId + 1));
        
        EntityTransaction j = getEntityManager().getTransaction();
        j.begin();
        getEntityManager().persist(job);
        j.commit();
        
        return job;
    }

    @Override
    public Job update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List getAll(){
        Query q = getEntityManager().createNamedQuery("Jobs.findAll");

        return (List) q.getResultList();
    }

    @Override
    public Job findByID(int id) {
        Query q = getEntityManager()
                .createNamedQuery("Jobs.findById")
                .setParameter("id", id);
        return (Job) q.getSingleResult();
    }

    
    @Override
    public Job findByDesc(String description){
        Query q = getEntityManager()
                .createNamedQuery("Jobs.findByDesciption") //there is a typo in the table and entity desciption not description
                .setParameter("desciption", description);
        return (Job) q.getSingleResult();
    }

    @Override
    public List<Job> getJobsOwnedByProvider(int provider_id) {
        Query q = getEntityManager()
                .createNamedQuery("Jobs.findByProvider")
                .setParameter("provider_id", provider_id);
        return (List) q.getResultList();
    }

    @Override
    public List<Job> getOpenJobs() {
        Query q = getEntityManager()
                .createQuery("SELECT j FROM Job j WHERE" +
                                "j.state = :newState OR j.state =: openState"); //need a Open/Closed/Complete job state column.

        q.setParameter("newState", "NEW");
        q.setParameter("openState", "OPEN");
        
        return (List) q.getResultList();
    }
    
    private long getMaxId(String maxQuery) {
        
        Query q = getEntityManager().createQuery(maxQuery);
        
        Long maxId = 1L;
        
        if( (q.getResultList() != null) && (q.getResultList().size() > 0 )){
            maxId = (Long) q.getResultList().get(0);
        }
        
        return maxId;
    }
    
}
