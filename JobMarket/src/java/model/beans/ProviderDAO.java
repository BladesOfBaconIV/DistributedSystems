/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public interface ProviderDAO extends UserDAO{ //implements changed to extends

    List findProvider();
    
}
