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
import model.entity.Admin;

/**
 *
 * @author User
 */
@Named(value = "adminView")
@RequestScoped
public class AdminView {
    
    private Admin admin;
    
    @EJB
    private AdminDAOImpl adminDAO;

    /**
     * Creates a new instance of AdminView
     */
    public AdminView() {
        this.admin = new Admin();
    }
    
    public Admin findByID(int id) {
        return adminDAO.findByID(id);
    }
    
    public List<Admin> findByUsername(String username) {
        return adminDAO.findByUsername(username);
    }
    
    public List<Admin> findByEmail(String email) {
        return adminDAO.findByEmail(email);
    }
    
    public List<Admin> findByFirstname(String firstname) {
        return adminDAO.findByFirstname(firstname);
    }
    
    public List<Admin> findByLastname(String lastname) {
        return adminDAO.findByLastname(lastname);
    }
    
}
