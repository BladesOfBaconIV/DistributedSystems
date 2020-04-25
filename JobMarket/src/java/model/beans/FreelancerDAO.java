/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public interface FreelancerDAO extends UserDAO {
    
    public List getAllFreelancer();
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
