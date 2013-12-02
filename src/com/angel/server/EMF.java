package com.angel.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EMF {

        private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("temp");

    public static EntityManagerFactory get() {
        return emfInstance;
    }

    private EMF() {
      // nothing 
    }
}