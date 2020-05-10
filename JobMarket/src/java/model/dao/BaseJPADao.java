/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author User
 */
public class BaseJPADao {
    
    public EntityManager getEntityManager() {
        return JPADaoFactory.createEntityManager();
    }
    
}
