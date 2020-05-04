/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import model.entity.Provider;

/**
 *
 * @author darra
 */
@Stateless
public abstract class ProviderDAOImpl extends UserDAOImpl<Provider> implements ProviderDAO {
    
    public List getAllFreelancers() {
        Query q = entityManager.createNamedQuery("Providers.findAll");
        return (List) q.getResultList();
    }
    
    //Add any additional custom methods unique to Admins here.
    
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public void businessMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
