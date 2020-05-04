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

/**
 *
 * @author darra
 * @param <User>
 */
public abstract class UserDAOImpl<User> implements UserDAO<User>{
    
    protected Class<User> entityClass;
    
    @PersistenceContext
    protected EntityManager entityManager;
   
    public UserDAOImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<User>) genericSuperclass.getActualTypeArguments()[1];
    }
    
    public void persists(User user) {
        entityManager.persist(user); 
    }
    
    @Override
    public void remove(User user) {
        entityManager.remove(user); 
    }
    
    @Override
    public List getAllUsers(){
        Query q = entityManager.createNamedQuery("Users.findAll");
        return (List) q.getResultList();
    }
    
    @Override
    public User findbyID(int id) {
        Query q = entityManager.createNamedQuery("Users.findByID");
        return (User) q.getSingleResult(); //Unique
    }
    
    @Override
    public User findByUsername(char username) {
        Query q = entityManager.createNamedQuery("User.findByUsername");
        return (User) q.getSingleResult(); //Unique
    }
    
    @Override
    public List findByFirstname(char firstname) {
        Query q = entityManager.createNamedQuery("Users.findByFirstname");
        return (List) q.getResultList(); //not unique
    }
    
    @Override
    public List findByLastname(char lastname) {
        Query q = entityManager.createNamedQuery("Users.findByLastname");
        return (List) q.getResultList(); //not unique
    }
    
    @Override
    public User findByPassword(char password) {
        Query q = entityManager.createNamedQuery("Users.findByPassword");
        return (User) q.getSingleResult(); //Assuming this must be unique
    }
    
    @Override
    public User findByEmail(char email){
        Query q = entityManager.createNamedQuery("Users.findByEmail");
        return (User) q.getSingleResult(); //Unique
    }
}
