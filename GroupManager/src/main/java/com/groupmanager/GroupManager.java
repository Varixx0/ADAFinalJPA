package com.groupmanager;/*
 * Esta es la clase que contiene el main de la aplicacion. 
   Unicamente el punto de inicio, redirige a la clase controlador que se encarga del resto de cosas

    Un Apunte que tiene valor en todo el proyecto es que he traducido Modulos como Module, 
    sin embargo Module es ya una palabra reservada en Java por lo que cada vez que quiero 
    llamar a Module tengo que especificar que es entity.Module ya que de lo contrario trata 
    de usar el predefinido de java. Ese es el motivo de porque esta puesto de esa manera
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
       MainController.starter();
    }
    
    //Las variables de este metodo estan en español porque en realidad este metodo no es parte de el projecto, es solo para tener datos a la hora de probarlo si fueran necesarios
    public static void insertTestData(){
        Group testGroup1 = new Group(1, "Ejemplo", "2DAMC");
        Group testGroup2 = new Group(2, "Ejemplo", "2DAMR");
        Group testGroup3 = new Group(3, "Ejemplo", "2ASIR");
       
        
        Student estudiante1 = new Student("123456", "Cristian", "Popica", testGroup1);
        Student estudiante2 = new Student("789012", "Albert", "Lozano", testGroup2);
        Student estudiante3 = new Student("345678", "Antonio", "Molines", testGroup1);
        Student estudiante4 = new Student("1234567", "Victor", "Tercero", testGroup1);
        Student estudiante5 = new Student("7890127", "Daniel", "Ruiz", testGroup2);
        
        
        entity.Module modulo1 = new Module(1, "PMDM", 20);
        entity.Module modulo2 = new Module(2, "ADA", 30);
        entity.Module modulo3 = new Module(3, "DIN", 402);
        entity.Module modulo4 = new Module(3, "SGE", 402);
        entity.Module modulo5 = new Module(3, "PSP", 456);
        entity.Module modulo6= new Module(3, "EMP", 42);

        // Crear tres instancias de la clase Enrollment utilizando constructores
        Enrollment matricula1 = new Enrollment(1, "Matrícula Victor ADA", estudiante4, modulo2);
        Enrollment matricula2 = new Enrollment(2, "Matrícula Antonio ADA", estudiante3, modulo2);
        Enrollment matricula3 = new Enrollment(3, "Matrícula Antonio PSP", estudiante3, modulo5);
        
        Project testProject1 = new Project ("1234" , "App per a reptes personals" , estudiante3);
        
        
        Inserts.insertGroup(testGroup3);
        Inserts.insertGroup(testGroup2);
        Inserts.insertGroup(testGroup1);
        
        Inserts.insertStudent(estudiante3);
        Inserts.insertStudent(estudiante2);    
        Inserts.insertStudent(estudiante1);  
        Inserts.insertStudent(estudiante4);    
        Inserts.insertStudent(estudiante5);  
        
        Inserts.insertModule(modulo3);
        Inserts.insertModule(modulo2);
        Inserts.insertModule(modulo1);
        Inserts.insertModule(modulo4);
        Inserts.insertModule(modulo5);
        Inserts.insertModule(modulo6);
        
        
        Inserts.insertProject(testProject1);
        
        Inserts.insertEnrollment(matricula1);
        Inserts.insertEnrollment(matricula2);
        Inserts.insertEnrollment(matricula3);
        
    }

    

}


