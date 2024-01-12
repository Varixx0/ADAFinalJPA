/*
 * Clase matriz de la aplicacion. Desde aqui se controlan todos los procesos
 */
package controller;

import entity.*;
import java.util.List;
import java.util.Scanner;
import view.Menus;

/**
 *
 * @author victortercero
 */
public class mainController {

    //Scanners
    private static Scanner user = new Scanner(System.in);

    //Pide al usuario un Int por terminal y lo devuelve
    public static int scanInt() {
        int x = 0;
        try {
            x = user.nextInt();
        } catch (Exception e) {
            System.out.println("Por favor introduzca un numero valido");
            scanInt();
        }finally{
            //Vaciamos el Buffer
            user.next();
        }
        return x;
    }
    
    //Pide al usuario una String por terminal y la devuelve
    public static String scanString() {
        String string = user.nextLine();
        return string;
    }
    
    public static Boolean validateMainMenuSelecction(int selecction){
        String selectionToString= Integer.toString(selecction);
        //regex que empiece por un numero y acabe por un numero y ese numero este en el rango del uno al ocho
        String regex = "^[1-8]$";
        return selectionToString.matches(regex);
    }

    public static void mainMenuController(){
        Menus.mainMenu();
        int response = scanInt();
        if(!validateMainMenuSelecction(response)){
            System.out.println("Introduce una respuesta correcta, por favor");
            mainMenuController();
        }else{
            switch (response) {
            case 1:
                deleteAllDatabaseSelected();
                break;
            case 2:
                System.out.println("El número es dos.");
                break;
            case 3:
                System.out.println("El número es tres.");
                break;
            case 4:
                System.out.println("El número es cuatro.");
                break;
            case 5:
                System.out.println("El número es cinco.");
                break;
            
        }
        }
        
    }
    /////////////////////////////////////////// AÑADIR ITEMS ////////////////////////////////////////
    
    //Para crear items primero tenemos que hacer dos cosas, asegurarnos de que el usuario crea primero los objetos que tiene que crear y verificar todas las entradas
    
    public static void insertStudentController(){
        System.out.println("///////////////////////////////////////");
        System.out.println("Estas en el modo insertar ALUMNO, se insertaran alumnos hasta que introduzcas el NIA: 0");
        String nia = "";
        String name= "";
        String lastName="";
        Group group; 
        while(!nia.equals("0")){
            Boolean validNia=false;
            do{
            System.out.println("Por favor introduce el NIA (Formato una letra y 8 Numeros): ");
            nia = scanString();
            if(validateNiaStudent(nia)){
                validNia=true;
            }else{
                System.out.println("El nia es un apartado obligatorio. No puede estar en blanco ni tener un formato invalido");
                System.out.println("Por favor vuelve a introducirlo");
            }
            }while(!validNia);
            
            System.out.println("Introduce el nombre del Alumno: ");
            name = scanString();
            
            System.out.println("Introduce los apellidos del alumno");
            lastName= scanString();
            
            if(returnGroupsItemCount()!=0){
                System.out.println("Elige uno de los siguientes Grupos ya creados o crea uno nuevo metiendo un indice que aun no exista diferente de cero: ");
                Menus.printGroupData();
            }else{
                System.out.println("No hay ningun grupo en la base de datos. Para crear un alumno debes crear uno primero. ¿Quieres crearlo ahora S/N?");
                System.out.println("Si no quieres crear el grupo se abortará la creacion del Alumno");
                System.out.println("El alumno se asignará automaticamente el grupo que crees una vez lo acabes");
            }
        }
        
    }
    public static Boolean validateNiaStudent(String nia){
        String regex = "^[A-Za-z]\\\\d{8}$";
        return nia.matches(regex);
    }
    //returnItemCount son una serie de funciones senciallas en las que hace un select a la base de datos y devuelve la cantidad de entradas que tienen
    public static int returnGroupsItemCount() {
        List<Group> groups = Selects.selectAllGroups();
        return groups.size();
    }

    public static int returnStudentsItemCount() {
        List<Student> students = Selects.selectAllStudents();
        return students.size();
    }

    public static int returnEnrollmentsItemCount() {
        List<Enrollment> enrollments = Selects.selectAllEnrollments();
        return enrollments.size();
    }

    public static int returnModulesItemCount() {
        List<entity.Module> modules = Selects.selectAllModules();
        return modules.size();
    }

    public static int returnProjectsItemCount() {
        List<Project> projects = Selects.selectAllProjects();
        return projects.size();
    }
    
    
    // Vaciar la base de datos
    
       
    public static void deleteAllDatabaseSelected() {
        Menus.databaseDeleteWarning();
        String response = scanString();
        if(!response.equals("X")){
            deleteAllDatabaseOperation();
        }
    }

    public static void deleteAllDatabaseOperation() {
        Deletes.deleteAllStudents();
        Deletes.deleteAllEnrollments();
        Deletes.deleteAllModules();
        Deletes.deleteAllProjects();
        Deletes.deleteAllGroups();
        System.out.println("Base de datos eliminada con exito");
    }

}
