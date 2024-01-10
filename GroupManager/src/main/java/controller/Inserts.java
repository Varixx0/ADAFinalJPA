/*
 * Esta clase recopila todos los inserts en la base de datos. 
 */
package controller;

import entity.Enrollment;
import entity.Group;
import entity.Project;
import entity.Student;
import entity.Module;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author victortercero
 */
public class Inserts {
    /*
        insertGroup introduce un nuevo item Grupo en la tabla Grupo_VT04 en la base de datos
        @Param: Objeto grupo que vamos a introducir . 
    */
    public static void insertGroup(Group group){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
       
        
        
        em.persist(group);

        tx.commit();

        em.close();
        emf.close();
    }
    /*
        insertGroup introduce un nuevo item Grupo en la tabla MODULO_VT04 en la base de datos
        @Param: Objeto grupo que vamos a introducir . 
    */
    public static void insertModule(entity.Module module){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
       
        
        em.persist(module);

        tx.commit();

        em.close();
        emf.close();
    }
    /*
        insertGroup introduce un nuevo item Grupo en la tabla MATRICULA_VT04 en la base de datos
        @Param: Objeto grupo que vamos a introducir . 
    */
    public static void insertEnrollment(Enrollment enrollment){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
       
        
        
        em.persist(enrollment);

        tx.commit();

        em.close();
        emf.close();
    }
    /*
        insertGroup introduce un nuevo item Grupo en la tabla PROYECTO_VT04 en la base de datos
        @Param: Objeto Project que vamos a introducir . 
    */
    public static void insertProject(Project project){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
       
        
        em.persist(project);

        tx.commit();

        em.close();
        emf.close();
    }
    /*
        insertStudent inserta iun objeto Student en la tabla ALUMNO_VT04 
        @Param: Objeto estudent a insertar
    */
    public static void insertStudent(Student student){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
       
        
        em.persist(student);

        tx.commit();

        em.close();
        emf.close();
    }
    
}
