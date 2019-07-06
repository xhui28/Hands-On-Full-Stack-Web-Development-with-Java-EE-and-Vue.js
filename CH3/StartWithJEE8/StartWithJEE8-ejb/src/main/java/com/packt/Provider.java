/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author daniel
 */

@Stateless
@LocalBean
public class Provider {
    @PersistenceContext
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }
    
    //@Produces
    public UserDAO getUserDAO(EntityManager entityManager) {
        return new UserDAO(entityManager);
    }
    
    @Produces
    public NewSessionBean getNewSessionBean(EntityManager entityManager) {
        return new NewSessionBean(entityManager);
    }
}