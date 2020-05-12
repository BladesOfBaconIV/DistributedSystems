/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import model.dao.BaseJPADao;
import model.entity.User;

/**
 * Methods to interact with all Users, in general just calls 
 * the Freelancer, Provider and Admin versions of methods together
 * @author darra
 */
@Stateless
public class UserDAOImpl<T extends User> extends BaseJPADao implements UserDAO<T>{
   
    public UserDAOImpl(){
//        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
//        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }
    
    @Override
    public void persist(User user) {
        getEntityManager().persist(user); 
    }
    
    @Override
    public void remove(User user) {
        getEntityManager().remove(user); 
    }
    
    @Override
    public List getAll(){
        List<User>  all = new FreelancerDAOImpl().getAllFreelancers();
        all.addAll(new ProviderDAOImpl().getAllProviders());
        all.addAll(new AdminDAOImpl().getAllAdmins());
        return all;
    }
    
    // TODO, probably needs to be the same as above
    @Override
    public User findByID(int id) {
        Query q = getEntityManager()
                .createNamedQuery("Users.findByID")
                .setParameter("id", id);
        return (User) q.getSingleResult(); //Unique
    }

    @Override
    public List<T> findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> findByFirstname(String firstname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> findByLastname(String lastname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> findByPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
