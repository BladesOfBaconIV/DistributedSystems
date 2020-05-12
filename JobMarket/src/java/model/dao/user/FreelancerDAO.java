/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import model.entity.Freelancer;

/**
 *
 * @author User
 */
public interface FreelancerDAO extends UserDAO<Freelancer> {
    
    public List getAllFreelancers();
    
    public Freelancer findByDescription(String description);
    
    //Add any additional custom methods unique to Admins here.
   
    
    
}
