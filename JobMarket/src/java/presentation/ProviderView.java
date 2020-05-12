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
import model.dao.user.ProviderDAOImpl;
import model.entity.Provider;

/**
 *
 * @author User
 */
@Named(value = "providerView")
@RequestScoped
public class ProviderView {

    private Provider provider;
    
    @EJB
    private ProviderDAOImpl providerDAO;

    /**
     * Creates a new instance of AdminView
     */
    public ProviderView() {
        this.provider = new Provider();
    }
    
    public Provider findByID(int id) {
        return providerDAO.findByID(id);
    }
    
    public List<Provider> findByUsername(String username) {
        return providerDAO.findByUsername(username);
    }
    
    public List<Provider> findByEmail(String email) {
        return providerDAO.findByEmail(email);
    }
    
    public List<Provider> findByFirstname(String firstname) {
        return providerDAO.findByFirstname(firstname);
    }
    
    public List<Provider> findByLastname(String lastname) {
        return providerDAO.findByLastname(lastname);
    }
    
}
