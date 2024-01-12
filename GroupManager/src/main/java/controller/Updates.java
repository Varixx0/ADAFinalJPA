/*
 * * Esta clase recopila todos los Updates de la aplicacion. 
*/
package controller;
import entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author victortercero
 */
public class Updates {
    /*
        updateGroup actualiza un item grupo en la tabla GRUPO
        @Param: Un objeto grupo que contiene los cambios a realizar. Por supuesto, el objeto grupo proporcionado debe tener la misma PK que el item que queremos modificar en la base de datos.
    */
    public static void updateGroup(Group group){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Group itemUpdated = em.find(Group.class, group.getGroupId());
        // Actualiza los atributos del registroActualizar
        itemUpdated.setClassroom(group.getClassroom());
        itemUpdated.setDescription(group.getDescription());
        tx.commit();

        em.close();
        emf.close();
    }
    /*
        updateStudent actualiza un item alumno en la tabla Alumno
        @Param: Un objeto Student que contiene los cambios a realizar. Por supuesto, el objeto grupo proporcionado debe tener la misma PK que el item que queremos modificar en la base de datos.
    */
    public static void updateStudent(Student student){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Student itemUpdated = em.find(Student.class, student.getNia());
        // Actualiza los atributos del registroActualizar
        itemUpdated.setGroup(student.getGroup());
        itemUpdated.setLastName(student.getLastName());
        itemUpdated.setName(student.getName());
        
        tx.commit();

        em.close();
        emf.close();
    }
    /*
        updateEnrollment actualiza un item matricula en la tabla MATRICULA
        @Param: Un objeto Enrollment que contiene los cambios a realizar. Por supuesto, el objeto grupo proporcionado debe tener la misma PK que el item que queremos modificar en la base de datos.
    */
    public static void updateEnrollment(Enrollment enrollment){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Enrollment itemUpdated = em.find(Enrollment.class, enrollment.getIdEnrollment());
        // Actualiza los atributos del itemUpdated
        itemUpdated.setDescription(enrollment.getDescription());
        
        tx.commit();

        em.close();
        emf.close();
    }
    /*
        updateModule actualiza un item modulo en la tabla MODULO
        @Param: Un objeto Module que contiene los cambios a realizar. Por supuesto, el objeto grupo proporcionado debe tener la misma PK que el item que queremos modificar en la base de datos.
    */
    public static void updateModule(entity.Module module){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        entity.Module itemUpdated = em.find(entity.Module.class, module.getModuleId());
        // Actualiza los atributos del registroActualizar
        itemUpdated.setNumHours(module.getNumHours());
        itemUpdated.setDescription(module.getDescription());
       
        
        tx.commit();

        em.close();
        emf.close();
    }
    /*
        updateProject actualiza un item proyecto en la tabla PROYECTO_CONVOCATORIA
        @Param: Un objeto project que contiene los cambios a realizar. Por supuesto, el objeto grupo proporcionado debe tener la misma PK que el item que queremos modificar en la base de datos.
    */
    public static void updateProject(Project project){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Project itemUpdated = em.find(Project.class, project.getProjectId());
        // Actualiza los atributos del registroActualizar
        itemUpdated.setStudent(project.getStudent());
        itemUpdated.setTitle(project.getTitle());
        
        tx.commit();

        em.close();
        emf.close();
    }
}
