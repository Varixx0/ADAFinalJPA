/*
 * Esta clase recopila todos los Selects de la aplicacion. 
 */
package controller;
import entity.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author victortercero
 */
public class Selects {
    /*
        selectGroupByID hace un select en la base de datos de  GRUPO
        @Param: int id, la Primary Key de la tabla GRUPO
        @Returns: El objeto Group si lo encuentra. Si no lo encuentra salta un error que posteriormente manejaremos
    */
    public static Group selectGroupByID(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        Group registroLeido = em.find(Group.class, id);
        
        emf.close();
        em.close();
        return registroLeido; 
    }
    /*
        selectStudentByID hace un select en la base de datos de  ALUMNO
        @Param: String id, haciendo referencia al nia, la Primary Key de la tabla GRUPO
        @Returns: El objeto Student si lo encuentra. Si no lo encuentra salta un error que posteriormente manejaremos
    */
    public static Student selectStudentByID(String id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        Student registroLeido = em.find(Student.class, id);
        
        emf.close();
        em.close();
        return registroLeido; 
    }
    /*
        selectEnrollmentByID hace un select en la base de datos de  MATRICULA
        @Param: int id, la Primary Key de la tabla MATRICULA
        @Returns: El objeto Enrollment si lo encuentra. Si no lo encuentra salta un error que posteriormente manejaremos
    */
    public static Enrollment selectEnrollmentByID(int id){
     
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        Enrollment registroLeido = em.find(Enrollment.class, id);
        
        emf.close();
        em.close();
        return registroLeido; 
    }
    /*
        selectModulesByID hace un select en la base de datos de  MODULO
        @Param: ind id, la Primary Key de la tabla MODULO
        @Returns: El objeto Module si lo encuentra. Si no lo encuentra salta un error que posteriormente manejaremos
    */
    public static entity.Module selectModuleByID(int id){
     
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        entity.Module registroLeido = em.find(entity.Module.class, id);
        
        emf.close();
        em.close();
        return registroLeido; 
    }
    /*
        selectProjectByID hace un select en la base de datos de  PROYECTO_CONVOCATORIA
        @Param: ind id, la Primary Key de la tabla PROYECTO_CONVOCATORIA
        @Returns: El objeto Project si lo encuentra. Si no lo encuentra salta un error que posteriormente manejaremos
    */
    public static Project selectProjectByID(int id){
     
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        Project registroLeido = em.find(Project.class, id);
        
        emf.close();
        em.close();
        return registroLeido; 
    }
    /*
        selectAllGroups hace un select all en la tabla GRUPO utilizando JPQL
        @Returns: Una lista de objetos Group
    */
    public static List<Group> selectAllGroups(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT g FROM Group g";
        Query query = em.createQuery(jpql);

        
        List<Group> groups = query.getResultList();
        
        
        em.close();
        
        return groups;
    }
    /*
        selectAllStudents hace un select all en la tabla ALUMNOS utilizando JPQL
        @Returns: Una lista de objetos Student
    */
    public static List<Student> selectAllStudents(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT s FROM Student s";
        Query query = em.createQuery(jpql);

        
        List<Student> students = query.getResultList();
        
        
        em.close();
        
        return students;
    }
    /*
        selectAllEnrollments hace un select all en la tabla MATRICULA utilizando JPQL
        @Returns: Una lista de objetos Enrollment
    */
    public static List<Enrollment> selectAllEnrollments(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT e FROM Enrollment e";
        Query query = em.createQuery(jpql);

        
        List<Enrollment> enrollments = query.getResultList();
        
        
        em.close();
        
        return enrollments;
    }
    /*
        selectAllModules hace un select all en la tabla MODULOS utilizando JPQL
        @Returns: Una lista de objetos Module
    */
    public static List<entity.Module> selectAllModules(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT m FROM Module m";
        Query query = em.createQuery(jpql);

        
        List<entity.Module> modules = query.getResultList();
        
        
        em.close();
        
        return modules;
    }
    /*
        selectAllProjects hace un select all en la tabla PROYECTOS_CONVOCATORIA utilizando JPQL
        @Returns: Una lista de objetos Project
    */
    public static List<Project> selectAllProjects(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT p FROM Project p";
        Query query = em.createQuery(jpql);

        
        List<Project> projects = query.getResultList();
        
        
        em.close();
        
        return projects;
    }
}
