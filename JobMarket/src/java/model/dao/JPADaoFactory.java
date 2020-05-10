/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class JPADaoFactory {
    
    private static final String PU_NAME = "JobMarketPU";
    
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    
    private JPADaoFactory() {} // mark it as a singleton
    
    public static EntityManager createEntityManager() {
        if (entityManager == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
    
}
