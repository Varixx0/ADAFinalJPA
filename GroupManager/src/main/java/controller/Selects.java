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
import javax.persistence.TypedQuery;

/**
 *
 * @author victortercero
 */
public class Selects {

    /////////////////////////////////////SELECT ALL/////////////////////////////////////
    /*
        selectAllGroups hace un select all en la tabla GRUPO utilizando JPQL
        @Returns: Una lista de objetos Group
     */
    public static List<Group> selectAllGroups() {
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
    public static List<Student> selectAllStudents() {
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
    public static List<Enrollment> selectAllEnrollments() {
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
    public static List<entity.Module> selectAllModules() {
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
    public static List<Project> selectAllProjects() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT p FROM Project p";
        Query query = em.createQuery(jpql);

        List<Project> projects = query.getResultList();

        em.close();

        return projects;
    }

    public static List<Student> getStudentsWithoutProject() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar transacción (si es necesario)
            em.getTransaction().begin();

            // Construir la consulta JPQL corregida
            String jpql = "SELECT s FROM Student s "
                    + "WHERE NOT EXISTS ("
                    + "    SELECT p FROM Project p "
                    + "    WHERE p.student = s"
                    + ")";

            // Crear la consulta
            Query query = em.createQuery(jpql);

            // Obtener el resultado
            List<Student> students = query.getResultList();

            // Confirmar la transacción (si es necesario)
            em.getTransaction().commit();

            return students;
        } finally {
            // Cerrar el EntityManager
            em.close();

            // Cerrar el EntityManagerFactory
            emf.close();
        }
    }
    ///////////////////////SELECTS STUDENT///////////////////////////////

    // Método para obtener un estudiante por NIA
    public static Student findStudentByNia(String nia) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createNamedQuery("Student.findByNia", Student.class);
            query.setParameter("nia", nia);
            return query.getSingleResult();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener estudiantes por nombre
    public static List<Student> findStudentsByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createNamedQuery("Student.findByNia", Student.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener estudiantes por apellidos
    public List<Student> findStudentsByLastName(String lastName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createNamedQuery("Student.findByNia", Student.class);
            query.setParameter("lastname", lastName);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener estudiantes por grupo
    public static List<Student> findStudentsByGroup(Group group) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createNamedQuery("Student.findByNia", Student.class);
            query.setParameter("group", group);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    ////////////////////////////////SELECTS GROUPS/////////////////////////////
    // Método para obtener un grupo por ID
    public static Group findGroupById(int groupId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Group> query = em.createNamedQuery("Group.findById", Group.class);
            query.setParameter("groupId", groupId);
            return query.getSingleResult();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener grupos por descripción
    public static List<Group> findGroupsByDescription(String description) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Group> query = em.createNamedQuery("Group.findById", Group.class);
            query.setParameter("description", description);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener grupos por aula
    public static List<Group> findGroupsByClassroom(String classroom) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Group> query = em.createNamedQuery("Group.findById", Group.class);
            query.setParameter("classroom", classroom);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    ///////////////////////////////////////SELECTS MODULE//////////////////////////////////
    
    // Método para obtener un módulo por ID
    public static entity.Module findModuleById(int moduleId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<entity.Module> query = em.createNamedQuery("Module.findById", entity.Module.class);
            query.setParameter("moduleId", moduleId);
            return query.getSingleResult();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener módulos por descripción
    public static List<entity.Module> findModulesByDescription(String description) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<entity.Module> query = em.createNamedQuery("Module.findById", entity.Module.class);
            query.setParameter("description", description);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener módulos por número de horas
    public static List<entity.Module> findModulesByNumHours(int numHours) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<entity.Module> query = em.createNamedQuery("Module.findById", entity.Module.class);
            query.setParameter("numHours", numHours);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    ///////////////////////////////////// SELECTS PROJECT /////////////////////////////
    
    // Método para obtener un proyecto por ID
    public static Project findProjectById(String projectId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Project> query = em.createNamedQuery("Project.findById", Project.class);
            query.setParameter("projectId", projectId);
            return query.getSingleResult();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener proyectos por título
    public static List<Project> findProjectsByTitle(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Project> query = em.createNamedQuery("Project.findById", Project.class);
            query.setParameter("title", title);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener proyectos por estudiante
    public static List<Project> findProjectsByStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Project> query = em.createNamedQuery("Project.findById", Project.class);
            query.setParameter("student", student);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    //////////////////////////////////////////SELECTS ENROLLMENT /////////////////////////////////////////////
     public static Enrollment findEnrollmentById(int idEnrollment) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Enrollment> query = em.createNamedQuery("Enrollment.findById", Enrollment.class);
            query.setParameter("idEnrollment", idEnrollment);
            return query.getSingleResult();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener matrículas por descripción
    public static List<Enrollment> findEnrollmentsByDescription(String description) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Enrollment> query = em.createNamedQuery("Enrollment.findById", Enrollment.class);
            query.setParameter("description", description);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }

    }

    // Método para obtener matrículas por estudiante
    public static List<Enrollment> findEnrollmentsByStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Enrollment> query = em.createNamedQuery("Enrollment.findById", Enrollment.class);
            query.setParameter("student", student);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    // Método para obtener matrículas por módulo
    public static List<Enrollment> findEnrollmentsByModule(entity.Module module) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPersistence");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Enrollment> query = em.createNamedQuery("Enrollment.findById", Enrollment.class);
            query.setParameter("module", module);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    
}
