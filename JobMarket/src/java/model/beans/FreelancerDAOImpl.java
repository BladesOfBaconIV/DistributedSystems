/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.persistence.Query;
import model.entity.Freelancer;


public abstract class FreelancerDAOImpl extends UserDAOImpl<Freelancer> implements FreelancerDAO {

    public List getAllFreelancers() {
        Query q = entityManager.createNamedQuery("Freelancers.findAll");
        return (List) q.getResultList();
    }

    public Freelancer findByDescription(String description){ 
        Query q = entityManager.createNamedQuery("Freelancers.findByDescription");
        return (Freelancer) q.getSingleResult(); //Unique - Actually not sure if this needs to be a list return instead of a single Freelancer
    }

    public List findByTokens(double tokens){  //What type are tokens?
        Query q = entityManager.createNamedQuery("Freelancers.findByTokens");
        return (List) q.getResultList();
    }
    
    //Add any additional custom methods unique to Admins here.
    
    
    
    
    

    @Override
    public void businessMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
