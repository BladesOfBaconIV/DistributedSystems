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
import managedBeans.entities.Freelancer;

/**
 *
 * @author User
 */
@Named(value = "freelancerLogin")
@SessionScoped
public class FreelancerLogin extends Login implements Serializable {

    Freelancer user;
    
    /**
     * Creates a new instance of Login
     */
    public FreelancerLogin() {
    }

    @Override
    public String login() {
        try {
            this.user = Freelancer.load(this.username, this.password);
            return "FreelancerHomePage";
        } catch (UserNotFoundException e) {
            return "failedLogin";
        } catch (SQLException e) {
            return "sqlexception";
        }
    }    
    
}
