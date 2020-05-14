/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import managedBeans.entities.Admin;
import managedBeans.entities.Freelancer;
import managedBeans.entities.Provider;

/**
 * Request bean to help with user management by admins
 * @author User
 */
@Named(value = "manageUser")
@RequestScoped
public class ManageUser {
    
    // All non-admin users
    private ArrayList<Provider> providers = new ArrayList();
    private ArrayList<Freelancer> freelancers = new ArrayList();
    // instance variables used for creating new non-admin users
    private String username;
    private String password;
    private String description;
    
    // injected AdminSession used to ensure the current user is an admin
    @ManagedProperty(value="#{adminSession}")
    @Inject
    private AdminSession admin;

    /**
     * Creates a new instance of RegisterUser
     */
    public ManageUser() {
        try {
            this.providers = Provider.loadAll();
            this.freelancers = Freelancer.loadAll();
        } catch  (SQLException e) {
            // if problem happens just leave list empty
            // TODO Log SQLException
        }
    }
    
    /**
     * Register a new provider
     * @return String of web page to redirect user to
     */
    public String registerProvider() {
        try {
            // Check user is an admin
            if (!Admin.validAdminID(admin.getUser().getId())) {
                // if not an admin invalidate the session and return to login
                admin.logout(); 
                return "Login";
            }
            new Provider(
                    this.username,
                    this.password
            ).save();
            return "AdminHomePage";
        } catch (SQLException e) {
            return "sqlexception"; // TODO log SQLEXception
        }
    }
    
    /**
     * Remove a provider
     * @param id of provider to remove
     * @return String of web page to redirect user to
     */
    public String removeProvider(int id) {
        try {
            // Check user is an admin
            if (!Admin.validAdminID(admin.getUser().getId())) {
                // if not an admin invalidate the session and return to login
                admin.logout(); 
                return "Login";
            }
            Provider.delete(id);
            this.providers = Provider.loadAll();
            return null; // return to this page
        } catch (SQLException e) {
            return "sqlexception"; // TODO log SQLEXception
        }
    }
    
    /**
     * Register a new freelancer
     * @return String of web page to redirect user to
     */
    public String registerFreelancer() {
        try {
            // Check user is an admin
            if (!Admin.validAdminID(admin.getUser().getId())) {
                // if not an admin invalidate the session and return to login
                admin.logout(); 
                return "Login";
            }
            new Freelancer(
                    this.username,
                    this.password,
                    this.description
            ).save();
            return "AdminHomePage";
        } catch (SQLException e) {
            return "sqlexception"; // TODO log SQLEXception
        }
    }
    
    /**
     * Remove a freelancer
     * @param id of freelancer to remove
     * @return String of web page to redirect user to
     */
    public String removeFreelancer(int id) {
        try {
            // Check user is an admin
            if (!Admin.validAdminID(admin.getUser().getId())) {
                // if not an admin invalidate the session and return to login
                admin.logout(); 
                return "Login";
            }
            Freelancer.delete(id);
            this.freelancers = Freelancer.loadAll();
            return null; // return to this page
        } catch (SQLException e) {
            return "sqlexception"; // TODO log SQLEXception
        }
    }

    public ArrayList<Provider> getProviders() {
        return providers;
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    public ArrayList<Freelancer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(ArrayList<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdminSession getAdmin() {
        return admin;
    }

    public void setAdmin(AdminSession admin) {
        this.admin = admin;
    }
    
    
    
    
}
