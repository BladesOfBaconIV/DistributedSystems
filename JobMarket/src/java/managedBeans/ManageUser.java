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
 *
 * @author User
 */
@Named(value = "manageUser")
@RequestScoped
public class ManageUser {
    
    private ArrayList<Provider> providers = new ArrayList();
    private ArrayList<Freelancer> freelancers = new ArrayList();
    private String username;
    private String password;
    private String description;
    
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
        }
    }
    
    public String registerProvider() {
        try {
            if (!Admin.validAdminID(admin.getUser().getId())) {
                return "Login";
            }
            new Provider(
                    this.username,
                    this.password
            ).save();
            return "AdminHomePage";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public String removeProvider(int id) {
        try {
            if (!Admin.validAdminID(admin.getUser().getId())) {
                return "Login";
            }
            Provider.delete(id);
            this.providers = Provider.loadAll();
            return null; // return to this page
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public String registerFreelancer() {
        try {
            if (!Admin.validAdminID(admin.getUser().getId())) {
                return "Login";
            }
            new Freelancer(
                    this.username,
                    this.password,
                    this.description
            ).save();
            return "AdminHomePage";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public String removeFreelancer(int id) {
        try {
            if (!Admin.validAdminID(admin.getUser().getId())) {
                return "Login";
            }
            Freelancer.delete(id);
            this.freelancers = Freelancer.loadAll();
            return null; // return to this page
        } catch (SQLException e) {
            return "sqlexception";
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
