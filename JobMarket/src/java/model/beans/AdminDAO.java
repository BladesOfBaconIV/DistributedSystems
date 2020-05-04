/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.ejb.Stateless;
import model.entity.Admin;

/**
 *
 * @author User
 */
@Stateless
public interface AdminDAO extends UserDAO<Admin> {
    
    //Add any additional custom methods unique to Admins here.

    //Don't know if this is needed since base DAO (UserDAO) has a getAllUsers() function
    public List getAllAdmins();
  
    //I don't know if I need seperate functions for Admins.findById queries
    //public User findbyID(int id);
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
