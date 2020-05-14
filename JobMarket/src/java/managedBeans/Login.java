/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.context.FacesContext;

/**
 * Parent class for login in beans. Login beans are SessionScoped, and store the
 * logged in user for for the session, i.e until they logout.
 * 
 * @author User
 */
public abstract class Login {
    
    protected String username;
    protected String password;
       
    /**
     * Tries to login to user class, using username and password
     * @return Name of page to redirect to.
     */
    public abstract String login();
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        return "LoginPage";
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
    
}
