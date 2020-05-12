/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import model.entity.Provider;

/**
 *
 * @author darra
 */
@Stateless
public class ProviderDAOImpl extends UserDAOImpl<Provider> implements ProviderDAO {
    
    @Override
    public List getAllProviders() {
        Query q = getEntityManager().createNamedQuery("Providers.findAll");
        return (List) q.getResultList();
    }
    
    @Override
    public Provider findByID(int id) {
        Query q = getEntityManager().createNamedQuery("Admins.findByID");
        return (Provider) q.getSingleResult(); //Unique
    }

    @Override
    public List<Provider> findByUsername(String username) {
        Query q = getEntityManager().createNamedQuery("User.findByUsername")
                .setParameter("username", username);
        return (List<Provider>) q.getResultList();
    }

    @Override
    public List<Provider> findByFirstname(String firstname) {
        Query q = getEntityManager().createNamedQuery("User.findByFirstname")
                .setParameter("username", firstname);
        return (List<Provider>) q.getResultList();
    }

    @Override
    public List<Provider> findByLastname(String lastname) {
        Query q = getEntityManager().createNamedQuery("User.findByLastname")
                .setParameter("username", lastname);
        return (List<Provider>) q.getResultList();
    }

    @Override
    public List<Provider> findByPassword(String password) {
        Query q = getEntityManager().createNamedQuery("User.findByPassword")
                .setParameter("username", password);
        return (List<Provider>) q.getResultList();
    }

    @Override
    public List<Provider> findByEmail(String email) {
        Query q = getEntityManager().createNamedQuery("User.findByEmail")
                .setParameter("username", email);
        return (List<Provider>) q.getResultList();
    }

}
