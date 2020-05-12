/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.dao.user.AdminDAOImpl;
import model.dao.user.FreelancerDAOImpl;
import model.entity.Admin;
import model.entity.Freelancer;

/**
 *
 * @author User
 */
@Named(value = "freelancerView")
@RequestScoped
public class FreelancerView {

    private Freelancer freelancer;
    
    @EJB
    private FreelancerDAOImpl freelancerDAO;

    /**
     * Creates a new instance of AdminView
     */
    public FreelancerView() {
        this.freelancer = new Freelancer();
    }
    
    public Freelancer findByID(int id) {
        return freelancerDAO.findByID(id);
    }
    
    public List<Freelancer> findByUsername(String username) {
        return freelancerDAO.findByUsername(username);
    }
    
    public List<Freelancer> findByEmail(String email) {
        return freelancerDAO.findByEmail(email);
    }
    
    public List<Freelancer> findByFirstname(String firstname) {
        return freelancerDAO.findByFirstname(firstname);
    }
    
    public List<Freelancer> findByLastname(String lastname) {
        return freelancerDAO.findByLastname(lastname);
    }
    
}
