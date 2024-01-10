package com.groupmanager;/*
 * Esta es la clase que contiene el main de la aplicacion. 
   Unicamente el punto de inicio, redirige a la clase controlador que se encarga del resto de cosas
 */

import controller.Inserts;
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
 * @author victermug
 */
public class GroupManager {

    public static void main(String[] args) {
        Group groupTest = new Group(8, "Descripcion de prueba" , "DAM22");
        Student studentTest = new Student("Eje20" , "Ejemplo" ,"Ejemplo", groupTest);
        Project projectTest = new Project("Ej2", "Ejemplo", studentTest); 
        Module moduleTest= new Module(422, "Ejemplo" , 2);
        Enrollment enrollmentTest = new Enrollment(422, "Ejemplo", studentTest, moduleTest);
        
      Inserts.insertGroup(groupTest);
      Inserts.insertStudent(studentTest);
      Inserts.insertModule(moduleTest);
      Inserts.insertProject(projectTest);
      Inserts.insertEnrollment(enrollmentTest);
    }
}
