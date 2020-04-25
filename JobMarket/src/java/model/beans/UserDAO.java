/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.entity.User;

/**
 *
 * @author User
 */
@Local
public interface UserDAO{
    
    
    public void persist(User user) ;
    public void remove(User user) ;
    
    //public User getUser(int id) ; //could be the same thing
    public User findbyID(int id) ;
    
    public <T extends User> List<User> getAllUsers() ;
    

    
    //public void updateUser(User user);
    //public void deleteUser(User user);

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    void businessMethod();
    
}
