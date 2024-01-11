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
        Group groupTest = new Group(1, "Ejemplo" , "DamT"); 
        Student studentTest= new Student("NiaEj", "Whatever" ,"Michael" , groupTest); 
        Module moduleTest= new Module(1, "Blablabla" , 3); 
        Project projectTest = new Project("Test" , "Ejemplo" , studentTest);
        Enrollment enrollmentTest=new Enrollment(1, "Desc" , studentTest, moduleTest);
        
        
        
        System.out.println("Que comience la purga");
        Deletes.deleteEnrollment(enrollmentTest);
        Deletes.deleteModule(moduleTest);
        Deletes.deleteStudent(studentTest);
        Deletes.deleteGroup(groupTest);
    }
}
