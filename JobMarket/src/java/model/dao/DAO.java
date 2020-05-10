/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;

/**
 *
 * @author User
 */
public interface DAO<T> {
    
    public void persist(T object);
    
    public void remove(T object);
    
    public List<T> getAll();
   
    public T findByID(int id);
    
}
