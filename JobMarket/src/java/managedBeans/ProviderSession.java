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
@Named(value = "providerSession")
@SessionScoped
public class ProviderSession extends Login implements Serializable {
    
    Provider user;
    
    /**
     * Creates a new instance of Login
     */
    public ProviderSession() {
    }

    @Override
    public String login() {
        try {
            this.setUser(Provider.load(this.username, this.password));
            return "ProviderHomePage";
        } catch (UserNotFoundException e) {
            return "failedLogin";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }
    
    public ArrayList<Job> fetchJobs() {
        try {
            return this.getUser().fetchJobs();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            ArrayList<Job> t = new ArrayList();
            t.add(new Job(1, "Fake", "You fucked up", Job.JobStatus.OPEN, 420, -1));
            return t;
        }
    }

    public Provider getUser() {
        return user;
    }

    public void setUser(Provider user) {
        this.user = user;
    }
    
}
