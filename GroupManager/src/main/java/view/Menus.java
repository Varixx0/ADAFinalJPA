/*
 *En esta clase se almacenaran los menus que utilizara la aplicacion, tambien se encargara de los posibles mensajes que se le tengan que mostrar al usuario
 */
package view;
import controller.*;
import entity.Enrollment;
import entity.Group;
import entity.Project;
import entity.Student;
import java.util.List;

/**
 * @author victermug
 */
public class Menus {
    //mainMenu se encarga de imprimir por pantalla el menu principal. 
    public static void mainMenu() {
        //OPCIONES A IMPLEMENTAR
        printItemCounterFormatted();
        System.out.println();
        System.out.println("Por favor elija una de las siguientes opciones: ");
        System.out.println("1. Vaciar toda la base de datos");
        System.out.println("2. Añadir un item a la base de datos");
        //Añadir items
        //Listado de elementos
        //Borrar elementos
        //Salir del programa
    }
    //Menu de Insertar Items
    public static void addItemMenu(){
        System.out.println("/////////////////////////////////");
        System.out.println("¿Que item deseas introducir?");
        System.out.println("1. Introducir un Grupo");
        System.out.println("2. Introducir un Alumno");
        System.out.println("3. Introducir una Matricula");
        System.out.println("4. Introducir un Modulo");
        System.out.println("5. Introducir un proyecto");
        System.out.println("6. Volver al menu principal");
    }
    
    
    //printItemsCounterFormatted recoge la cantidad de entidades que tiene la base de datos y las imprime con formato
    public static void printItemCounterFormatted(){
        //Conseguimos los registros de la base de datos
        int groupItemCount = MainController.returnGroupsItemCount();
        int studentItemCount= MainController.returnStudentsItemCount();
        int enrollmentItemCount= MainController.returnEnrollmentsItemCount();
        int moduleItemCount= MainController.returnModulesItemCount();
        int projectItemCount= MainController.returnProjectsItemCount();
        
        
        String formato = "| %-40s | %-3d |%n"; // Ajusta el formato según tus necesidades

        // Imprime la línea superior de la celda
        System.out.println("ITEMS EN LA BASE DE DATOS:");
        System.out.format("+------------------------------------------+-----+%n");

        // Imprime la string introductoria y la cantidad con formato
        System.out.format(formato, "Grupos en la BD:" , groupItemCount);
        System.out.format(formato, "Alumnos en la BD:" , studentItemCount);
        System.out.format(formato, "Matriculas en la BD:" , enrollmentItemCount);
        System.out.format(formato, "Modulos en la BD:" , moduleItemCount);
        System.out.format(formato, "Proyectos en la BD:" , projectItemCount);
       

        // Imprime la línea inferior de la celda
        System.out.format("+------------------------------------------+-----+%n");
    
    }
    //Aviso antes de borrar la base de datos entera. 
    public static void databaseDeleteWarning(){
        System.out.println("ATENCION [!]: La opcion que has seleccionado borrara todas las entradas de la base de datos");
        System.out.println("¿Estas seguro de que quieres continuar? Pulsa cualquier tecla para continuar. Pulsa X para abortar.");
    }
    
    //////////////////////////////////////////////////////////////  IMPRIMIR LOS DATOS CON FORMATO  //////////////////////////////////////////////////////////////  
    
    //Imprime los datos de la tabla Grupo
    public static void printGroupData(){
        List<Group> groups = Selects.selectAllGroups();
         
        String formatoCabecera = "| %-15s | %-20s | %-15s |%n";
        String formatoDatos = "| %-15d | %-20s | %-15s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+-----------------+----------------------+-----------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoCabecera, "ID Grupo", "Clase" , "Descripcion");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+-----------------+----------------------+-----------------+%n");

        for (Group group: groups) {
           System.out.format(formatoDatos, group.getGroupId(), group.getClassroom(), group.getDescription());   
        }

        // Imprime la línea inferior de la celda
        System.out.format("+-----------------+----------------------+-----------------+%n");
    }
    
    //Imprime los datos de la tabla Estudiante
    public static void printStudentData(){
        List<Student> students = Selects.selectAllStudents();
        
        String formatoDatos = "| %-10s | %-20s | %-20s | %-15s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoDatos, "NIA", "Nombre", "Apellidos", "Grupo");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

         for (Student student: students) {
           System.out.format(formatoDatos, student.getNia(), student.getName(), student.getLastName(), student.getGroup().getClassroom());   
        }

        // Imprime la línea inferior de la celda
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
    }
    public static void printStudentWithoutProjectData(){
        List<Student> students = Selects.getStudentsWithoutProject();
        
        String formatoDatos = "| %-10s | %-20s | %-20s | %-15s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoDatos, "NIA", "Nombre", "Apellidos", "Grupo");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

         for (Student student: students) {
           System.out.format(formatoDatos, student.getNia(), student.getName(), student.getLastName(), student.getGroup().getClassroom());   
        }

        // Imprime la línea inferior de la celda
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
    }
    
    //Imprime los datos de la tabla Matricula
    public static void printEnrollmentData(){
       List<Enrollment> enrollments = Selects.selectAllEnrollments();
        
        String formatoDatos = "| %-14s | %-25s | %-20s | %-20s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+----------------+---------------------------+----------------------+----------------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoDatos, "Id Matricula", "Descripcion", "Modulo", "Alumno");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+----------------+---------------------------+----------------------+----------------------+%n");

         for (Enrollment enrollment: enrollments) {
           System.out.format(formatoDatos, enrollment.getIdEnrollment(), enrollment.getDescription(), enrollment.getModule().getDescription(), enrollment.getStudent().getName() + " " + enrollment.getStudent().getLastName());   
        }

        // Imprime la línea inferior de la celda
        System.out.format("+----------------+---------------------------+----------------------+----------------------+%n");

    }
    
    //Imprime los datos de la tabla Modulo
    public static void printModuleData(){
        
        List<entity.Module> modules = Selects.selectAllModules();
        String formatoCabecera = "| %-15s | %-30s | %-22s |%n";
        String formatoDatos = "| %-15d | %-30s | %-22d |%n";

        // Imprime la línea superior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoCabecera, "Id Modulo", "Descripcion", "Numero de horas");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

       for (entity.Module module: modules) {
           System.out.format(formatoDatos, module.getModuleId() , module.getDescription(), module.getNumHours());   
        }

        // Imprime la línea inferior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");
    
    }
    
    //Imprime los datos de la tabla Projecto
    public static void printProjectData(){
        List<Project> projects = Selects.selectAllProjects(); 
        String formatoCabecera = "| %-15s | %-30s | %-22s |%n";
        String formatoDatos = "| %-15s | %-30s | %-22s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoCabecera, "Id Proyecto", "Titulo", "Alumno");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

       for (Project project: projects) {
           System.out.format(formatoDatos, project.getProjectId(), project.getTitle(), project.getStudent().getName() + " " + project.getStudent().getLastName());   
        }

        // Imprime la línea inferior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");
    }
}
