/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.User;

/**
 *
 * @author darra
 */
public abstract class UserDAOImpl implements UserDAO{
    protected Class userClass;
    
    @PersistenceContext
    protected EntityManager userManager;
    
    public UserDAOImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.userClass = (Class) genericSuperclass.getActualTypeArguments()[1];
    }
    
    public void persists(User user) {
        userManager.persist(user); 
    }
    
    public void remove(User user) {
        userManager.remove(user); 
    }
    
    public List getAll(){
        Query q = userManager.createQuery(
            "SELECT a FROM " + userClass.getName()) ;
        return (List) q.getResultList();
    }
    
    //public User findByID(int id) { 
    //    return (User) userManager.find(userClass, id); 
    //}
    
    @Override
    public User findbyID(int id) {
        Query q = userManager.createQuery(
            "SELECT u FROM Users u WHERE u.id = :id");
        return (User) q.getSingleResult(); //Unique
    }
    
    public User findByUsername(char username) {
        Query q = userManager.createQuery(
            "SELECT u FROM Users u WHERE u.id = :id");
        return (User) q.getSingleResult(); //Unique
    }
    
    public List findByFirstname(char firstname) {
        Query q = userManager.createQuery(
            "SELECT u FROM Users u WHERE u.firstname = :firstname");
        return (List) q.getResultList(); //not unique
    }
    
    public List findByLastname(char lastname) {
        Query q = userManager.createQuery(
            "SELECT u FROM Users u WHERE u.lastname = :lastname");
        return (List) q.getResultList(); //not unique
    }
    
    public User findByPassword(char password) {
        Query q = userManager.createQuery(
            "SELECT u FROM Users u WHERE u.password = :password");
        return (User) q.getSingleResult(); //Assuming this must be unique
    }
    
    public User findByEmail(char email){
        Query q = userManager.createQuery(
            "SELECT u FROM Users u WHERE u.email = :email");
        return (User) q.getSingleResult(); //Unique
    }
}
