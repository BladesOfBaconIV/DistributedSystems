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
    public List<Provider> findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> findByFirstname(String firstname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> findByLastname(String lastname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> findByPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
