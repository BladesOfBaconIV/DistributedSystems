/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import model.entity.Freelancer;


@Stateless
public class FreelancerDAOImpl extends UserDAOImpl<Freelancer> implements FreelancerDAO {

    @Override
    public List getAllFreelancers() {
        Query q = getEntityManager().createNamedQuery("Freelancers.findAll");
        return (List) q.getResultList();
    }
    
    @Override
    public Freelancer findByID(int id) {
        Query q = getEntityManager().createNamedQuery("Admins.findByID");
        return (Freelancer) q.getSingleResult(); //Unique
    }

    @Override
    public Freelancer findByDescription(String description){ 
        Query q = getEntityManager().createNamedQuery("Freelancers.findByDescription");
        q.setParameter("description", description);
        return (Freelancer) q.getSingleResult(); //Unique - Actually not sure if this needs to be a list return instead of a single Freelancer
    }

    @Override
    public List<Freelancer> findByUsername(String username) {
        Query q = getEntityManager().createNamedQuery("User.findByUsername")
                .setParameter("username", username);
        return (List<Freelancer>) q.getResultList();
    }

    @Override
    public List<Freelancer> findByFirstname(String firstname) {
        Query q = getEntityManager().createNamedQuery("User.findByFirstname")
                .setParameter("username", firstname);
        return (List<Freelancer>) q.getResultList();
    }

    @Override
    public List<Freelancer> findByLastname(String lastname) {
        Query q = getEntityManager().createNamedQuery("User.findByLastname")
                .setParameter("username", lastname);
        return (List<Freelancer>) q.getResultList();
    }

    @Override
    public List<Freelancer> findByPassword(String password) {
        Query q = getEntityManager().createNamedQuery("User.findByPassword")
                .setParameter("username", password);
        return (List<Freelancer>) q.getResultList();
    }

    @Override
    public List<Freelancer> findByEmail(String email) {
        Query q = getEntityManager().createNamedQuery("User.findByEmail")
                .setParameter("username", email);
        return (List<Freelancer>) q.getResultList();
    }
    
}
