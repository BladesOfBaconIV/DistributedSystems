/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import database.exceptions.UserNotFoundException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import managedBeans.entities.Job;
import managedBeans.entities.Provider;

/**
 *
 * @author User
 */
@Named(value = "providerLogin")
@SessionScoped
public class ProviderLogin extends Login implements Serializable {
    
    Provider user;
    
    /**
     * Creates a new instance of Login
     */
    public ProviderLogin() {
    }

    @Override
    public String login() {
        try {
            this.user = Provider.load(this.username, this.password);
            return "ProvidererHomePage";
        } catch (UserNotFoundException e) {
            return "failedLogin";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public ArrayList<Job> getJobs() {
        try {
            return this.user.getJobs();
        } catch (Exception e) {
            return new ArrayList();
        }
    }
    
}
