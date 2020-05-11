/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.persistence.Query;
import model.entity.Freelancer;


public class FreelancerDAOImpl extends UserDAOImpl<Freelancer> implements FreelancerDAO {

    @Override
    public List getAllFreelancers() {
        Query q = getEntityManager().createNamedQuery("Freelancers.findAll");
        return (List) q.getResultList();
    }

    @Override
    public Freelancer findByDescription(String description){ 
        Query q = getEntityManager().createNamedQuery("Freelancers.findByDescription");
        q.setParameter("description", description);
        return (Freelancer) q.getSingleResult(); //Unique - Actually not sure if this needs to be a list return instead of a single Freelancer
    }

    @Override
    public List<Freelancer> findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Freelancer> findByFirstname(String firstname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Freelancer> findByLastname(String lastname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Freelancer> findByPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Freelancer> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
