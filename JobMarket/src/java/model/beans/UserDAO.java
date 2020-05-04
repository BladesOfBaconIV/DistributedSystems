/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface UserDAO<User>{
    
    //User is abstract, cannnot be a user object. 
    //Generics is utilized so each method signature has a type User, which in the
    //implemented DAO classes, will be a concrete type per domain.
    
    
    public void persist(User user) ;
    
    public void remove(User user) ;
    
    public <T extends User> List<User> getAllUsers() ;
   
    public User findbyID(int id) ; //Not sure about int being used here, maybe (Serializable id)
    
    public User findByUsername(char username) ; 
    
    public List findByFirstname(char firstname) ;
    
    public List findByLastname(char lastname) ;
    
    public User findByPassword(char password) ;
    
    public User findByEmail(char email) ;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    void businessMethod();
    
}
