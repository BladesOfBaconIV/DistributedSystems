/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.persistence.Query;
import model.entity.Admin;
import model.entity.User;


public class AdminDAOImpl extends UserDAOImpl<Admin> implements AdminDAO {
    
    
    //I don't know if any addition functions are needed here that are not in the 
    // UserDAO.

    @Override
    public List getAllAdmins() {
        Query q = getEntityManager().createNamedQuery("Admins.findAll");
        return (List) q.getResultList();
    }

    @Override
    public Admin findByID(int id) {
        Query q = getEntityManager().createNamedQuery("Admins.findByID");
        return (Admin) q.getSingleResult(); //Unique
    }

    @Override
    public List<Admin> findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findByFirstname(String firstname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findByLastname(String lastname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findByPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
