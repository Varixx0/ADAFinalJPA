package com.groupmanager;/*
 * Esta es la clase que contiene el main de la aplicacion. 
   Unicamente el punto de inicio, redirige a la clase controlador que se encarga del resto de cosas
 */

import controller.Deletes;
import controller.Inserts;
import entity.Enrollment;
import entity.Group;
import entity.Project;
import entity.Student;
import entity.Module;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author victermug
 */
public class GroupManager {

    public static void main(String[] args) {
        Group testGroup1 = new Group(1, "Ejemplo", "Ejemplo2");
        Group testGroup2 = new Group(2, "Ejemplo 3", "Ejemplo4");
        Group testGroup3 = new Group(3, "Ejemplo4", "Ejemplo5");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT g FROM Group g";
        Query query = em.createQuery(jpql);

        // Obtener la lista de estudiantes
        List<Group> groups = query.getResultList();

        System.out.println("He llegado");
        for (int i = 0; i < groups.size(); i++) {
            String elemento = groups.get(i).getDescription();
            System.out.println(elemento);
        }
        System.out.println("He pasado");
        // Cierra el EntityManager cuando hayas terminado
        em.close();

    }
}
