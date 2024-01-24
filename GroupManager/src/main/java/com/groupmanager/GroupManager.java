package com.groupmanager;/*
 * Esta es la clase que contiene el main de la aplicacion. 
   Unicamente el punto de inicio, redirige a la clase controlador que se encarga del resto de cosas
 */

import controller.Deletes;
import controller.Inserts;
import controller.*;
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
import view.*;

/**
 *
 * @author victermug
 */
public class GroupManager {

    public static void main(String[] args) {
        Group testGroup1 = new Group(1, "Ejemplo", "Ejemplo2");
        Group testGroup2 = new Group(2, "Ejemplo 3", "Ejemplo4");
        Group testGroup3 = new Group(3, "Ejemplo4", "Ejemplo5");
       
        
        Student estudiante1 = new Student("123456", "Juan", "Pérez", testGroup1);
        Student estudiante2 = new Student("789012", "María", "Gómez", testGroup2);
        Student estudiante3 = new Student("345678", "Carlos", "López", testGroup1);
        
        entity.Module modulo1 = new Module(1, "M01", 20);
        entity.Module modulo2 = new Module(2, "M02", 30);
        entity.Module modulo3 = new Module(3, "M03", 402);

        // Crear tres instancias de la clase Enrollment utilizando constructores
        Enrollment matricula1 = new Enrollment(1, "Matrícula Primer Semestre", estudiante1, modulo1);
        Enrollment matricula2 = new Enrollment(2, "Matrícula Primer Semestre", estudiante2, modulo2);
        Enrollment matricula3 = new Enrollment(3, "Matrícula Primer Semestre", estudiante3, modulo3);
        
        Project testProject1 = new Project ("1" , "Titulo1" , estudiante1);
        Project testProject2 = new Project ("2" , "Titulo2" , estudiante2);
        Project testProject3 = new Project ("3" , "Titulo3" , estudiante3);
       
        
       MainController.starter();
    }
    
    //Las variables de este metodo estan en español porque en realidad este metodo no es parte de el projecto, es solo para tener datos a la hora de probarlo si fueran necesarios
    public static void insertTestData(){
        Group testGroup1 = new Group(1, "Ejemplo", "Ejemplo2");
        Group testGroup2 = new Group(2, "Ejemplo 3", "Ejemplo4");
        Group testGroup3 = new Group(3, "Ejemplo4", "Ejemplo5");
       
        
        Student estudiante1 = new Student("123456", "Juan", "Pérez", testGroup1);
        Student estudiante2 = new Student("789012", "María", "Gómez", testGroup2);
        Student estudiante3 = new Student("345678", "Carlos", "López", testGroup1);
        
        entity.Module modulo1 = new Module(1, "M01", 20);
        entity.Module modulo2 = new Module(2, "M02", 30);
        entity.Module modulo3 = new Module(3, "M03", 402);

        // Crear tres instancias de la clase Enrollment utilizando constructores
        Enrollment matricula1 = new Enrollment(1, "Matrícula Primer Semestre", estudiante1, modulo1);
        Enrollment matricula2 = new Enrollment(2, "Matrícula Primer Semestre", estudiante2, modulo2);
        Enrollment matricula3 = new Enrollment(3, "Matrícula Primer Semestre", estudiante3, modulo3);
        
        Project testProject1 = new Project ("1" , "Titulo1" , estudiante1);
        Project testProject2 = new Project ("2" , "Titulo2" , estudiante2);
        Project testProject3 = new Project ("3" , "Titulo3" , estudiante3);
        
        Inserts.insertGroup(testGroup3);
        Inserts.insertGroup(testGroup2);
        Inserts.insertGroup(testGroup1);
        
        Inserts.insertStudent(estudiante3);
        Inserts.insertStudent(estudiante2);    
        Inserts.insertStudent(estudiante1);  
        
        Inserts.insertModule(modulo3);
        Inserts.insertModule(modulo2);
        Inserts.insertModule(modulo1);
        
        Inserts.insertProject(testProject3);
        Inserts.insertProject(testProject2);
        Inserts.insertProject(testProject1);
        
        Inserts.insertEnrollment(matricula1);
        Inserts.insertEnrollment(matricula2);
        Inserts.insertEnrollment(matricula3);
        
    }

    

}


