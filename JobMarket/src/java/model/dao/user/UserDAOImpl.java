/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.persistence.Query;
import model.dao.BaseJPADao;
import model.entity.User;

/**
 *
 * @author darra
 */
public abstract class UserDAOImpl<T extends User> extends BaseJPADao implements UserDAO<T>{
   
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

}
