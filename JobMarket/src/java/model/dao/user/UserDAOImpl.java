/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.User;

/**
 *
 * @author darra
 * @param <T>
 */
public abstract class UserDAOImpl<T extends User> implements UserDAO<T>{
    
    protected Class<T> entityClass;
    
    @PersistenceContext
    protected EntityManager entityManager;
   
    public UserDAOImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }
    
    @Override
    public void persist(User user) {
        entityManager.persist(user); 
    }
    
    @Override
    public void remove(User user) {
        entityManager.remove(user); 
    }
    
    @Override
    public List getAll(){
        Query q = entityManager.createNamedQuery("Users.findAll");
        return (List) q.getResultList();
    }
    
    @Override
    public T findByID(int id) {
        Query q = entityManager
                .createNamedQuery("Users.findByID")
                .setParameter("id", id);
        return (T) q.getSingleResult(); //Unique
    }
    
    @Override
    public T findByUsername(String username) {
        Query q = entityManager.createNamedQuery("User.findByUsername");
        return (T) q.getSingleResult(); //Unique
    }
    
    @Override
    public List<User> findByFirstname(String firstname) {
        Query q = entityManager.createNamedQuery("Users.findByFirstname");
        return (List) q.getResultList(); //not unique
    }
    
    @Override
    public List findByLastname(char lastname) {
        Query q = entityManager.createNamedQuery("Users.findByLastname");
        return (List) q.getResultList(); //not unique
    }
    
    @Override
    public T findByPassword(char password) {
        Query q = entityManager.createNamedQuery("Users.findByPassword");
        return (T) q.getSingleResult(); //Assuming this must be unique
    }
    
    @Override
    public T findByEmail(char email){
        Query q = entityManager.createNamedQuery("Users.findByEmail");
        return (T) q.getSingleResult(); //Unique
    }
}
