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
import managedBeans.entities.Admin;

/**
 * Session bean to manage logged in Admin users
 * @author User
 */
@Named(value = "adminSession")
@SessionScoped
public class AdminSession extends Login implements Serializable {

    // The loagged in user
    Admin user;
    
    public AdminSession() {
    }

    /**
     * Tries to log an Admin in using username and password
     * @return The page to redirect them too
     */
    @Override
    public String login() {
        try {
            this.user = Admin.load(this.username, this.password);
            return "AdminHomePage";
        } catch (UserNotFoundException e) {
            return "failedLogin";
        } catch (SQLException e) {
            return "sqlexception"; // TODO add SQLException logging
        }
    }

    public Admin getUser() {
        return user;
    }

    public void setUser(Admin user) {
        this.user = user;
    }
    
}
