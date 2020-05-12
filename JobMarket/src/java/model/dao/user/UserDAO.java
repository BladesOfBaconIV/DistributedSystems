/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import model.dao.DAO;
import model.entity.User;

/**
 *
 * @author User
 */
public abstract interface UserDAO<T extends User> extends DAO<User>{
    
    //User is abstract, cannnot be a user object. 
    //Generics is utilized so each method signature has a type User, which in the
    //implemented DAO classes, will be a concrete type per domain.
    
    public List<T> findByUsername(String username) ; 
    
    public List<T> findByFirstname(String firstname) ;
    
    public List<T> findByLastname(String lastname) ;
    
    public List<T> findByPassword(String password) ;
    
    public List<T> findByEmail(String email) ;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // void businessMethod();
    
}
