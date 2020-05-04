/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.persistence.Query;
import model.entity.Admin;


public abstract class AdminDAOImpl extends UserDAOImpl<Admin> implements AdminDAO {
    
    
    //I don't know if any addition functions are needed here that are not in the 
    //Base DAO (UserDAO).

    public List getAllAdmins() { //May not be needed as getAllUsers is super function in base
        Query q = entityManager.createNamedQuery("Admins.findAll");
        return (List) q.getResultList();
    }

    //@Override
    //public User findbyID(int id) {
    //Query q = entityManager.createNamedQuery("Admins.findByID");
    //return (User) q.getSingleResult(); //Unique
    //}

}
