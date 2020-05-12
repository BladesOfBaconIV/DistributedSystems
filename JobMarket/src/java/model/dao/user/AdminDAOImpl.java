/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import model.entity.Admin;

@Stateless
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
        Query q = getEntityManager().createNamedQuery("User.findByUsername")
                .setParameter("username", username);
        return (List<Admin>) q.getResultList();
    }

    @Override
    public List<Admin> findByFirstname(String firstname) {
        Query q = getEntityManager().createNamedQuery("User.findByFirstname")
                .setParameter("username", firstname);
        return (List<Admin>) q.getResultList();
    }

    @Override
    public List<Admin> findByLastname(String lastname) {
        Query q = getEntityManager().createNamedQuery("User.findByLastname")
                .setParameter("username", lastname);
        return (List<Admin>) q.getResultList();
    }

    @Override
    public List<Admin> findByPassword(String password) {
        Query q = getEntityManager().createNamedQuery("User.findByPassword")
                .setParameter("username", password);
        return (List<Admin>) q.getResultList();
    }

    @Override
    public List<Admin> findByEmail(String email) {
        Query q = getEntityManager().createNamedQuery("User.findByEmail")
                .setParameter("username", email);
        return (List<Admin>) q.getResultList();
    }

}
