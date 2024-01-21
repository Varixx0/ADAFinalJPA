/*
 *En esta clase se almacenaran los menus que utilizara la aplicacion, tambien se encargara de los posibles mensajes que se le tengan que mostrar al usuario
 */
package view;

import controller.*;
import entity.Enrollment;
import entity.Group;
import entity.Project;
import entity.Student;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        System.out.println("3. Ver elementos en la base de datos");
        //Borrar elementos
        //Salir del programa
    }

    public static void printGreetings() {
        System.out.println(asciiArt);
        System.out.println(randomGreeting());
        MainController.waitingKey();
    }

    //Arte que se imprime al principio, no se lee bien el formato, pero se imprime correctamente.
    public static String asciiArt = " _    ______________________  ____              \n"
            + "| |  / /  _/ ____/_  __/ __ \\/ __ \\             \n"
            + "| | / // // /     / / / / / / /_/ /             \n"
            + "| |/ // // /___  / / / /_/ / _, _/              \n"
            + "|___/___/\\____/_/_/  \\____/_/_|_|____  _____  __\n"
            + "   /   | / ____/   |  / __ \\/ ____/  |/  /\\ \\/ /\n"
            + "  / /| |/ /   / /| | / / / / __/ / /|_/ /  \\  / \n"
            + " / ___ / /___/ ___ |/ /_/ / /___/ /  / /   / /  \n"
            + "/_/  |_\\____/_/  |_/_____/_____/_/  /_/   /_/   \n"
            + "                                                ";

    //Mensajes de Bienvenida con fecha y hora que van cambiando
    public static ArrayList<String> greetingMessages = new ArrayList<String>() {
        {
            add("¡Bienvenido al sistema de gestión académica! Hoy es " + obtenerFechaYHora() + ". ¿Listo para administrar los registros de alumnos por grupo y módulo?");
            add("¡Saludos a los encargados de la gestión educativa! En este " + obtenerDiaDeLaSemana() + ", a las " + obtenerHoraActual() + ", iniciamos la administración de registros.");
            add("¡Bienvenidos al panel de control académico! Son las " + obtenerHoraActual() + " en este día dedicado a la gestión educativa. ¿Preparados para supervisar los registros?");
            add("¡Saludos, gestores del conocimiento! Hoy es " + obtenerFechaYHora() + ". Vamos a comenzar la gestión de registros de alumnos por grupo y módulo.");
            add("¡Hola coordinadores académicos! Estamos en " + obtenerDiaDeLaSemana() + " y son las " + obtenerHoraActual() + ". ¿Listos para organizar la información de los alumnos?");
            add("¡Bienvenidos al sistema de administración educativa! En este " + obtenerDiaDeLaSemana() + ", a las " + obtenerHoraActual() + ", se inicia la gestión de registros.");
            add("¡Ey, responsables académicos! Son las " + obtenerHoraActual() + " en este día lleno de organización. ¿Listos para mantener actualizados los registros?");
            add("¡Saludos, coordinadores del conocimiento! Hoy es " + obtenerFechaYHora() + ". Vamos a sumergirnos en la administración de registros académicos.");
            add("¡Bienvenidos al centro de gestión académica! En este " + obtenerDiaDeLaSemana() + ", a las " + obtenerHoraActual() + ", comienza la supervisión de registros.");
            add("¡Hola, encargados de la gestión! Son las " + obtenerHoraActual() + ". ¿Listos para mantener ordenados y actualizados los registros de alumnos?");
            add("¡Bienvenidos al sistema de control educativo! Hoy es " + obtenerFechaYHora() + ". Prepárense para administrar de manera eficiente los registros académicos.");
            add("¡Saludos, administradores del conocimiento! En este " + obtenerDiaDeLaSemana() + ", a las " + obtenerHoraActual() + ", iniciamos la gestión de registros.");
            add("¡Hola, líderes de la organización académica! Son las " + obtenerHoraActual() + " en este día dedicado a la gestión. ¿Listos para mantener actualizados los registros de alumnos?");
            add("¡Bienvenidos al sistema de seguimiento educativo! Hoy es " + obtenerFechaYHora() + ". Vamos a explorar juntos la gestión de registros académicos.");
            add("¡Saludos, gestores comprometidos! En este " + obtenerDiaDeLaSemana() + ", a las " + obtenerHoraActual() + ", comienza la administración de registros.");
            add("¡Hola, planificadores académicos! Son las " + obtenerHoraActual() + ". ¿Listos para supervisar y organizar eficientemente los registros?");
            add("¡Bienvenidos al programa de gestión académica! Hoy es " + obtenerFechaYHora() + ". Prepárense para gestionar los registros de manera efectiva.");
            add("¡Saludos, coordinadores educativos! En este " + obtenerDiaDeLaSemana() + ", a las " + obtenerHoraActual() + ", arranca la gestión de registros.");
            add("¡Hola, líderes de la planificación académica! Son las " + obtenerHoraActual() + " en este día lleno de responsabilidades. ¿Listos para gestionar los registros de alumnos?");
            add("¡Bienvenidos al sistema de organización académica! Hoy es " + obtenerFechaYHora() + ". Vamos a comenzar la gestión de registros académicos.");
        }
    };

    public static String randomGreeting() {
        Random rnd = new Random();
        return greetingMessages.get(rnd.nextInt(0, greetingMessages.size() - 1));
    }

    //Creamos los metodos para obtener la fecha y la hora local del PC. Utilizamos la funcion de Java LocalDateTime y le damos formato para que aparezca como nosotros queramos.
    private static String obtenerFechaYHora() {
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatoFechaHora);

    }

    private static String obtenerDiaDeLaSemana() {
        DateTimeFormatter formatoDiaSemana = DateTimeFormatter.ofPattern("EEEE");
        return LocalDateTime.now().format(formatoDiaSemana);
    }

    private static String obtenerHoraActual() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.now().format(formatoHora);
    }

    //Menu de Insertar Items
    public static void addItemMenu() {
        System.out.println("/////////////////////////////////");
        System.out.println("¿Que item deseas introducir?");
        System.out.println("1. Introducir un Grupo");
        System.out.println("2. Introducir un Alumno");
        System.out.println("3. Introducir una Matricula");
        System.out.println("4. Introducir un Modulo");
        System.out.println("5. Introducir un proyecto");
        System.out.println("6. Volver al menu principal");
    }

    /////////////////////////////////////////////////// SELECTS///////////////////////////////////////////////////
    public static void selectsMenu() {
        System.out.println("/////////////////////////////////");
        System.out.println("Introduce el tipo de item que deseas consultar en la base de datos");
        System.out.println("1. Grupos");
        System.out.println("2. Alumnos");
        System.out.println("3. Modulos");
        System.out.println("4. Matriculas");
        System.out.println("5. proyectos");
        System.out.println("6. Volver al menu principal");
    }

    public static void selectsMenuGroup() {
        System.out.println("/////////////////////////////////");
        System.out.println("Has seleccionado la opcion de consultar Grupos");
        System.out.println("Introduce el tipo de consulta que deseas hacer");
        System.out.println("1. Imprimir todos los grupos de la base de datos");
        System.out.println("2. Buscar un grupo por id");
        System.out.println("3. Buscar grupos por nombre");
        System.out.println("4. Buscar grupos por descripcion");
        System.out.println("5. Volver a la seleccion de consultas");
        System.out.println("6. Volver al menu principal");
    }

    public static void selectsMenuStudent() {
        System.out.println("/////////////////////////////////");
        System.out.println("Has seleccionado la opcion de consultar Alumnos");
        System.out.println("1. Imprimir todos los Alumnos de la base de datos");
        System.out.println("2. Buscar Alumno por NIA");
        System.out.println("3. Buscar Alumnos por nombre");
        System.out.println("4. Buscar Alumnos por apellido");
        System.out.println("5. Buscar Alumnos por grupo");
        System.out.println("6. Volver a la seleccion de consultas");
        System.out.println("7. Volver al menu principal");
    }

    public static void selectsMenuModule() {
        System.out.println("/////////////////////////////////");
        System.out.println("Has seleccionado la opcion de consultar Modulos");
        System.out.println("1. Imprimir todos los Modulos de la base de datos");
        System.out.println("2. Buscar Modulo por ID");
        System.out.println("3. Buscar Modulos por descripcion");
        System.out.println("4. Buscar Modulos por numero de horas");
        System.out.println("5. Volver a la seleccion de consultas");
        System.out.println("6. Volver al menu principal");
    }
    
    public static void selectsMenuEnrollment(){
        System.out.println("/////////////////////////////////");
        System.out.println("Has seleccionado la opcion de consultar Matricula");
        System.out.println("1. Imprimir todas las Matriculas de la base de datos");
        System.out.println("2. Buscar Matriculas por ID");
        System.out.println("3. Buscar Matriculas por descripcion");
        System.out.println("4. Buscar Matriculas por Modulo");
        System.out.println("5. Buscar Matriculas por Alumno");
        System.out.println("6. Volver a la seleccion de consultas");
        System.out.println("7. Volver al menu principal");
    }
    
    public static void selectsMenuProject(){
        System.out.println("/////////////////////////////////");
        System.out.println("Has seleccionado la opcion de consultar Proyecto");
        System.out.println("1. Imprimir todos los proyectos de la base de datos");
        System.out.println("2. Buscar proyecto por ID");
        System.out.println("3. Buscar proyectos por titulo");
        System.out.println("4. Buscar proyectos por Alumno");
    }

    //printItemsCounterFormatted recoge la cantidad de entidades que tiene la base de datos y las imprime con formato
    public static void printItemCounterFormatted() {
        //Conseguimos los registros de la base de datos
        int groupItemCount = MainController.returnGroupsItemCount();
        int studentItemCount = MainController.returnStudentsItemCount();
        int enrollmentItemCount = MainController.returnEnrollmentsItemCount();
        int moduleItemCount = MainController.returnModulesItemCount();
        int projectItemCount = MainController.returnProjectsItemCount();

        String formato = "| %-40s | %-3d |%n"; // Ajusta el formato según tus necesidades

        // Imprime la línea superior de la celda
        System.out.println("ITEMS EN LA BASE DE DATOS:");
        System.out.format("+------------------------------------------+-----+%n");

        // Imprime la string introductoria y la cantidad con formato
        System.out.format(formato, "Grupos en la BD:", groupItemCount);
        System.out.format(formato, "Alumnos en la BD:", studentItemCount);
        System.out.format(formato, "Matriculas en la BD:", enrollmentItemCount);
        System.out.format(formato, "Modulos en la BD:", moduleItemCount);
        System.out.format(formato, "Proyectos en la BD:", projectItemCount);

        // Imprime la línea inferior de la celda
        System.out.format("+------------------------------------------+-----+%n");

    }

    //Aviso antes de borrar la base de datos entera. 
    public static void databaseDeleteWarning() {
        System.out.println("ATENCION [!]: La opcion que has seleccionado borrara todas las entradas de la base de datos");
        System.out.println("¿Estas seguro de que quieres continuar? Pulsa cualquier tecla para continuar. Pulsa X para abortar.");
    }

    //////////////////////////////////////////////////////////////  IMPRIMIR LOS DATOS CON FORMATO  //////////////////////////////////////////////////////////////  
    //Imprime los datos de la tabla Grupo
    public static void printGroupData() {
        List<Group> groups = Selects.selectAllGroups();
        if (groups.size() != 0) {
            String formatoCabecera = "| %-15s | %-20s | %-15s |%n";
            String formatoDatos = "| %-15d | %-20s | %-15s |%n";

            // Imprime la línea superior de la celda
            System.out.format("+-----------------+----------------------+-----------------+%n");

            // Imprime la cabecera con los nombres de las variables
            System.out.format(formatoCabecera, "ID Grupo", "Clase", "Descripcion");

            // Imprime la línea de separación entre la cabecera y los datos
            System.out.format("+-----------------+----------------------+-----------------+%n");

            for (Group group : groups) {
                System.out.format(formatoDatos, group.getGroupId(), group.getClassroom(), group.getDescription());
            }

            // Imprime la línea inferior de la celda
            System.out.format("+-----------------+----------------------+-----------------+%n");
        } else {
            System.out.println("La base de datos no tiene ningun grupo guardado");
        }
    }

    //Imprime los datos de la tabla Estudiante
    public static void printStudentData() {
        List<Student> students = Selects.selectAllStudents();
        if (students.size() != 0) {
            String formatoDatos = "| %-10s | %-20s | %-20s | %-15s |%n";

            // Imprime la línea superior de la celda
            System.out.format("+------------+----------------------+----------------------+-----------------+%n");

            // Imprime la cabecera con los nombres de las variables
            System.out.format(formatoDatos, "NIA", "Nombre", "Apellidos", "Grupo");

            // Imprime la línea de separación entre la cabecera y los datos
            System.out.format("+------------+----------------------+----------------------+-----------------+%n");

            for (Student student : students) {
                System.out.format(formatoDatos, student.getNia(), student.getName(), student.getLastName(), student.getGroup().getClassroom());
            }

            // Imprime la línea inferior de la celda
            System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        } else {
            System.out.println("No hay ningun alumo guardado en la base de datos");
        }
    }

    public static void printStudentWithoutProjectData() {
        List<Student> students = Selects.getStudentsWithoutProject();

        String formatoDatos = "| %-10s | %-20s | %-20s | %-15s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoDatos, "NIA", "Nombre", "Apellidos", "Grupo");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        for (Student student : students) {
            System.out.format(formatoDatos, student.getNia(), student.getName(), student.getLastName(), student.getGroup().getClassroom());
        }

        // Imprime la línea inferior de la celda
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
    }

    //Imprime los datos de la tabla Matricula
    public static void printEnrollmentData() {
        List<Enrollment> enrollments = Selects.selectAllEnrollments();

        String formatoDatos = "| %-14s | %-25s | %-20s | %-20s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+----------------+---------------------------+----------------------+----------------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoDatos, "Id Matricula", "Descripcion", "Modulo", "Alumno");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+----------------+---------------------------+----------------------+----------------------+%n");

        for (Enrollment enrollment : enrollments) {
            System.out.format(formatoDatos, enrollment.getIdEnrollment(), enrollment.getDescription(), enrollment.getModule().getDescription(), enrollment.getStudent().getName() + " " + enrollment.getStudent().getLastName());
        }

        // Imprime la línea inferior de la celda
        System.out.format("+----------------+---------------------------+----------------------+----------------------+%n");

    }

    //Imprime los datos de la tabla Modulo
    public static void printModuleData() {

        List<entity.Module> modules = Selects.selectAllModules();
        String formatoCabecera = "| %-15s | %-30s | %-22s |%n";
        String formatoDatos = "| %-15d | %-30s | %-22d |%n";

        // Imprime la línea superior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoCabecera, "Id Modulo", "Descripcion", "Numero de horas");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

        for (entity.Module module : modules) {
            System.out.format(formatoDatos, module.getModuleId(), module.getDescription(), module.getNumHours());
        }

        // Imprime la línea inferior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

    }

    //Imprime los datos de la tabla Projecto
    public static void printProjectData() {
        List<Project> projects = Selects.selectAllProjects();
        String formatoCabecera = "| %-15s | %-30s | %-22s |%n";
        String formatoDatos = "| %-15s | %-30s | %-22s |%n";

        // Imprime la línea superior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

        // Imprime la cabecera con los nombres de las variables
        System.out.format(formatoCabecera, "Id Proyecto", "Titulo", "Alumno");

        // Imprime la línea de separación entre la cabecera y los datos
        System.out.format("+-----------------+--------------------------------+------------------------+%n");

        for (Project project : projects) {
            System.out.format(formatoDatos, project.getProjectId(), project.getTitle(), project.getStudent().getName() + " " + project.getStudent().getLastName());
        }

        // Imprime la línea inferior de la celda
        System.out.format("+-----------------+--------------------------------+------------------------+%n");
    }
}
