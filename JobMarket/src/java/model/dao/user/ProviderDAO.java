/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.user;

import java.util.List;
import javax.ejb.Stateless;
import model.entity.Provider;

/**
 *
 * @author User
 */
@Stateless
public interface ProviderDAO extends UserDAO<Provider>{ //implements changed to extends

    public List getAllProviders();
    
}
