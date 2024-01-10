/*
 * Esta es la clase que contiene el main de la aplicacion. 
   Unicamente el punto de inicio, redirige a la clase controlador que se encarga del resto de cosas
 */
package com.groupmanager;

import Entity.Group;
import Entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author victermug
 */
public class GroupManager {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        
       Group nuevoRegistro= new Group("Descripcion de prueba", "Clase de prueba");
        
        // Configura los atributos del nuevo registro
        em.persist(nuevoRegistro);

        tx.commit();

        em.close();
        emf.close();
    }
}
