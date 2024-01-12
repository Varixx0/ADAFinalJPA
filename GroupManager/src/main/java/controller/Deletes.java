/*
 * Esta clase recopila todos los deletes en la base de datos. 
 */
package controller;

import entity.Enrollment;
import entity.Group;
import entity.Student;
import entity.Module;
import entity.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author victortercero
 */
/*
    Lista de constraints:
   -  No puedes borrar grupo si tiene alumnos
    - No puedes borrar alumnos si tiene proyectos 
    - No puedes borrar el Modulo si tiene a alguien matriculado.
*/
public class Deletes {
    /*
        delete group borra una instancia de tipo Grupo en la base de datos. No puede eliminar el grupo si hay alumnos registrados en el.
        @Param: El objeto Grupo que quieras borrar
    */
    public static void deleteGroup(Group group){
        //Añadir try catch o comprobar manualmente
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Group registroEliminar = em.find(Group.class, group.getGroupId());
        em.remove(registroEliminar);

        tx.commit();

        em.close();
        emf.close();
    }
    /*
        deleteStudent borra una instancia de tipo ESTUDIANTE en la base de datos, no pude eliminar un estudiante si tiene un proyecto asignado
        @Param: El objeto estudiante que queramos eliminar
    */
    public static void deleteStudent(Student student){
        //Añadir try catch o comprobar manualmente
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Student registroEliminar = em.find(Student.class, student.getNia());
        em.remove(registroEliminar);

        tx.commit();

        em.close();
        emf.close();
    }
    /* 
        deleteModule borra una instancia del tipo MODULO en la base de datos.
        @Param: el objeto modulo que queramos eliminar
    */
    public static void deleteModule(Module module){
        //Añadir try catch o comprobar manualmente
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        entity.Module registroEliminar = em.find(Module.class, module.getModuleId());
        em.remove(registroEliminar);

        tx.commit();

        em.close();
        emf.close();
    }
    /*
        deleteProject borra una instancia del tipo PROYECTO_CONVOCATORIA en la base de datos.
        @Param: el objeto project que queramos eliminar de la base de datos. 
    */
    public static void deleteProject(Project project){
        //Añadir try catch o comprobar manualmente
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Project registroEliminar = em.find(Project.class, project.getProjectId());
        em.remove(registroEliminar);

        tx.commit();

        em.close();
        emf.close();
    }
    public static void deleteEnrollment(Enrollment enrollment){
        //Añadir try catch o comprobarollm manualmente
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Enrollment registroEliminar = em.find(Enrollment.class, enrollment.getIdEnrollment());
        em.remove(registroEliminar);

        tx.commit();

        em.close();
        emf.close();
    }
    
   ////////////////////////////////////////(NUKES [!])////////////////////////////////////////
    
    //deleteAll Borra todas las entradas de la tabla especificada mediante JPQL
    
    public static void deleteAllGroups(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String jpql = "DELETE FROM Group";
        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        transaction.commit();
        
        em.close();
    }
     public static void deleteAllStudents(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String jpql = "DELETE FROM Student";
        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        transaction.commit();
        
        em.close();
    }
      public static void deleteAllEnrollments(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        String jpql = "DELETE FROM Enrollment";
        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        transaction.commit();
        
        em.close();
    }
       public static void deleteAllModules(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String jpql = "DELETE FROM Module";
        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        transaction.commit();
        
        em.close();
    }
        public static void deleteAllProjects(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        String jpql = "DELETE FROM Project";
        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        transaction.commit();
        
        em.close();
    }
}
