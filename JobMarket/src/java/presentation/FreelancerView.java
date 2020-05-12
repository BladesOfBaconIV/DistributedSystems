/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.dao.user.FreelancerDAOImpl;
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
            
    public FreelancerView() {
        this.freelancer = new Freelancer();
    }
    
    public Freelancer getFreelancer() {
        return this.freelancer;
    }
    
    
    
}
