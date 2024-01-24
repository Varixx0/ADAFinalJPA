/*
 * Clase matriz de la aplicacion. Desde aqui se controlan todos los procesos
 */
package controller;

import entity.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import view.Menus;

/**
 *
 * @author victortercero
 */
public class MainController {

    //Scanners
    private static Scanner user = new Scanner(System.in);

    //Pide al usuario un Int por terminal y lo devuelve
    public static int scanInt() {
        Integer x = null;
        boolean inputValido = false;

        do {
            try {
                String input = user.nextLine().trim();

                if (input.isEmpty()) {
                    // Si la entrada está vacía, devolvemos null
                    return 0;
                }

                x = Integer.parseInt(input);
                inputValido = true; // Si llega aquí, la entrada es válida y salimos del bucle
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido");
            }
        } while (!inputValido);

        return x;
    }

    

    //Pide al usuario una String por terminal y la devuelve
    public static String scanString() {
        String string = user.nextLine();
        return string;
    }

    public static Boolean validateMainMenuSelecction(int selecction) {
        String selectionToString = Integer.toString(selecction);
        //regex que empiece por un numero y acabe por un numero y ese numero este en el rango del uno al ocho
        String regex = "^[1-6]$";
        return selectionToString.matches(regex);
    }

    public static void starter() {
        Menus.printGreetings();
        mainMenuController();
    }

    public static void mainMenuController() {
        Menus.mainMenu();
        int response = scanInt();
        if (!validateMainMenuSelecction(response)) {
            System.out.println("Introduce una respuesta correcta, por favor");
            mainMenuController();
        } else {
            switch (response) {
                case 1:
                    deleteAllDatabaseSelected();
                    waitingKey();
                    mainMenuController();
                    break;
                case 2:
                    insertController();
                    break;
                case 3:
                    selectsController();
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
    //Esta seccion recopila todo el proceso de crear e insertar objetos asi como los submetodos necesarios para verificar que se crean correctamente
    public static void insertController() {
        Menus.addItemMenu();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                insertGroupController();
                mainMenuController();
                break;
            case 2:
                insertStudentController();
                mainMenuController();
                break;
            case 3:
                insertModuleController();
                mainMenuController();
                break;
            case 4:
                insertEnrollmentController();
                mainMenuController();
                break;
            case 5:
                insertProjectController();
                mainMenuController();
                break;
            case 6:
                mainMenuController();
                break;
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }

    //Inserta grupos en la base de datos hasta que le pongas id 0
    public static void insertGroupController() {
        System.out.println("///////////////////////////////////////");
        System.out.println("Estas en el modo insertar Grupo, se insertaran grupos hasta que introduzcas el ID:0");
        //Inicializamos las variables por si acaso, pero si todo va bien deberian reasignarse todos los valores. 
        int id = -1;
        String description = "";
        String classroom = "";
        Group group = new Group();
        while (id != 0) {
            System.out.println("Introduce el ID del grupo (Debe ser un numero entero): ");
            id = scanInt();
            //Si pones ID:0 acaba el modo insercion
            if (id != 0) {
                if (Selects.findGroupById(id)== null) {//Si es nulo lo puede crear porque significaque el ID que intentas introducir no lo esta gastando ningun grupo en la base de datos
                    //No tienen ninguna comprobacion porque se acepta todo tipo de respuestas, incluido la string vacia. 
                    System.out.println("Introduce la Descripcion del Grupo:");
                    description = scanString();
                    System.out.println("Ahora introduce la clase: ");
                    classroom = scanString();

                    //Creamos el objeto grupo
                    group = new Group(id, description, classroom);
                    Inserts.insertGroup(group);
                } else {
                    System.out.println(" [!]El id de grupo que has introducido ya existe en la base de datos y por tanto no puede ser utilizado");
                    System.out.println("Por favor introduce uno diferente. El id de grupo es Obligatorio y debe ser unico");
                    break;
                }
            } else {//Rompemos el ciclo porque ya no hace falta continuar insertando datos
                break;
            }

        }
    }

    public static void insertStudentController() {
        System.out.println("///////////////////////////////////////");
        System.out.println("Estas en el modo insertar ALUMNO, se insertaran alumnos hasta que introduzcas el NIA: 0");
        String nia = "";
        String name = "";
        String lastName = "";
        Student newStudent = new Student();
        Group group;
        while (!nia.equals("0")) {
            Boolean validNia = false;
            do {//Modificar esta parte si queremos quitar la restriccion del NIA
                System.out.println("Por favor introduce el NIA (Formato una letra y 8 Numeros): ");
                nia = scanString();
                if (!nia.equals("0")) {
                    if (validateNiaStudent(nia)) {//Esta parte es realmente desesperante asi que si en las pruebas da muchos problemas tal vez habria que quitarlo.
                        if (Selects.findStudentByNia(nia) == null) {
                            if(!nia.equals("")){
                            validNia = true;
                            }else{
                                System.out.println("El nia no puede estar vacio");
                            }
                        } else {
                            System.out.println("El nia que has introducido ya lo tiene otro alumno en la base de datos, por favor introduzca uno nuevo");
                        }
                    } else {
                        System.out.println("El nia es un apartado obligatorio. No puede estar en blanco ni tener un formato invalido");
                        System.out.println("Por favor vuelve a introducirlo");
                    }
                } else {//El id es un nia valido por lo que lo especificamos para que lo detecte como correcto
                    validNia = true;
                }
            } while (!validNia);
            if (!nia.equals("0")) {
                //No hacemos comprobaciones en nombre o Apellidos porque pueden poner lo que quieran o no poner nada directamente. 
                System.out.println("Introduce el nombre del Alumno: ");
                name = scanString();

                System.out.println("Introduce los apellidos del alumno");
                lastName = scanString();

                //Damos varias opciones al usuario para crear un grupo o abortar la operacion porque puede ser que el usuario haya intentado insertar el alumno antes que el grupo
                group = insertStudentGroupSelecction();

                newStudent = new Student(nia, name, lastName, group);
                Inserts.insertStudent(newStudent);
            }
        }
    }

    /*
        Hace la seleccion de grupo para Alumno. Le da todas las opciones posibles. 
        @Returns: Un objeto Grupo para que se utilice en la insercion de Estudiante.
     */
    private static Group insertStudentGroupSelecction() {
        Group group = new Group();
        if (returnGroupsItemCount() != 0) {
            System.out.println("Elige uno de los siguientes Grupos ya creados o crea uno nuevo metiendo un indice que aun no exista diferente de cero: ");
            Menus.printGroupData(Selects.selectAllGroups());
            System.out.println("Introduce el numero de identificacion que quiera utilizar: ");
            int selecction = scanInt();
            if (Selects.findGroupById(selecction) != null) {//Si es nulo lo puede crear porque significaque el ID que intentas introducir no lo esta gastando ningun grupo en la base de datos
                //La consulta se vuelve a hacer, un poco redundante quizas pero no queria tener una variable mas en este metodo.
                Group groupSelected = Selects.findGroupById(selecction);
                return groupSelected;
            } else {//Si no es nulo significa que ya hay un objeto con ese ID en la base de datos y por tanto no puede utilizarlo
                System.out.println("Ha seleccionado un id que no esta en la base de datos,  el grupo es necesario para insertar a un alumo en la base de datos. Tienes tres opciones");
                System.out.println("Por favor seleccione una de las siguientes opciones: ");
                System.out.println("1. Volver a elegir un grupo ya existente");
                System.out.println("2. Crear un nuevo grupo e insertar en el al Alumno");
                System.out.println("3. Abortar creacion de Alumno");
                int optionSelected = scanInt();
                switch (optionSelected) {
                    //Volver a elegir un Grupo
                    case 1:
                        group = insertStudentGroupSelecction();
                        return group;
                    //Redirige a la creacion del modulo
                    case 2:
                        System.out.println("Pasaras al modo de creacion de grupos, crea los grupos que necesites y cuando acabes volveras al selector.");
                        insertGroupController();
                        group = insertStudentGroupSelecction();
                        return group;
                    case 3:
                        mainMenuController();
                        break;
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, se aborta la creacion de alumno");
                        mainMenuController();
                }
            }

        } else {//Este else se ejecuta si cuando creas el alumno no hay ningun grupo en la base de datos. 
            System.out.println("No hay ningun grupo en la base de datos. Para crear un alumno debes crear uno primero. ¿Quieres crearlo ahora S/N?");
            System.out.println("Si no quieres crear el grupo se abortará la creacion del Alumno");
            System.out.println("El alumno se asignará automaticamente el grupo que crees una vez lo acabes");
            String response = scanString();
            if (response.equals("S")) {
                System.out.println("Pasaras al modo de creacion de grupos, crea los grupos que necesites y cuando acabes volveras al selector.");
                insertGroupController();
                if (returnGroupsItemCount() != 0) {//Comprobamos que el usuario ha introducudo un grupo en la base de datos, si no lo ha hecho (Porque puede no hacerlo) le salta un mensaje y vuelve a pedirle que lo haga
                    group = insertStudentGroupSelecction();
                } else {
                    System.out.println("[!] No has creado nigun grupo en el menu de creacion de grupos o no se ha creado correctamente");
                    group = insertStudentGroupSelecction();
                }
                return group;
            } else {//Aborta la creacion de grupo
                mainMenuController();
            }
        }
        //Si no hay ningun problema este return nunca deberei ejecutarse, solo está puesto para que el compilador no se queje
        return group;
    }

    //Valida el nia, aunque se puede eliminar para las pruebas porque puede ser molesto. [!] Actualmente esta puenteado pero se puede activar en cualquier Momento
    public static Boolean validateNiaStudent(String nia) {
        //String regex = "^[A-Za-z]\\\\d{8}$";
        //return nia.matches(regex);
        //He hecho un bypass al regex porque da problemas
        return true;
    }

    public static void insertModuleController() {
        System.out.println("Estas en el modo Insertar Modulos. Se insertaran Modulos hasta que especifiques el ID:0");
        int idModule = -1;
        String description = "";
        int numHours = -1;
        while (idModule != 0) {
            System.out.println("Introduce el ID que le quieras asignar al Modulo");
            idModule = scanInt();
            //si el modulo es distinto de continua creando el Modulo
            if (idModule != 0) {
                if (Selects.findEnrollmentById(idModule) == null) {//Busca el Modulo en la base de datos, si lo encuentra salta al else, sino lo crea.
                    System.out.println("Por favor introduce la descripción del modulo");
                    description = scanString();
                    System.out.println("Introduce el numero de horas del modulo");
                    numHours = scanInt();
                    entity.Module newModule = new entity.Module(idModule, description, numHours);
                    Inserts.insertModule(newModule);
                    System.out.println("Modulo insertado con exito");
                    //Ponemos tecla de espera para el usuario
                    waitingKey();
                } else {//Ha encontrado redundancia en el ID y no te deja continuar, rompe el bucle
                    System.out.println("El ID que has introducido ya está asignado a una entrada en la base de datos, por favor selecciona uno diferente");
                    System.out.println("El ID es obligatorio y debe ser unico");
                    break;
                }
            } else {//Rompemos el ciclo porque ya no hace falta continuar insertando datos
                break;
            }
        }
    }

    //Esta es la funcion mas complicada de implementar porque tiene 3 PK 2 de las cuale son objetos. So hay que moverse con cuidado para asegurarme de que todo esta correcto
    public static void insertEnrollmentController() {
        System.out.println("Has entrado en el modo de insercion de Matriculas. Se insertaran matriculas hasta que introduzcas el ID: 0");
        int idEnrollment = -1;
        String description = "";
        Student student = new Student();
        entity.Module module = new entity.Module();
        while (idEnrollment != 0) {
            System.out.println("Introduce un ID para el modulo");
            System.out.println("El ID es obligatorio y debe ser unico:");
            idEnrollment = scanInt();
            if (idEnrollment != 0) {
                //Busca la matricula en la base de datos para ver si hay alguna con el ID que queremos introducir. 
                if (Selects.findEnrollmentById(idEnrollment) == null) {
                    System.out.println("Introduce la descripcion de la matricula");
                    description = scanString();

                    //Este metodo se asegura de que se devuelva un estudiante, ya que es necesario para la creacion de la matricula
                    student = insertEnrollmentStudentSelecction();
                    // Este metodo se asegura de que se le asigna un modulo.
                    module = insertEnrollmentModuleSelecction();

                    Enrollment newEnrollment = new Enrollment(idEnrollment, description, student, module);
                    Inserts.insertEnrollment(newEnrollment);
                    System.out.println("Elemento insertado con exito");
                    //Le pedimos al usuario que le de al enter para continuar
                    waitingKey();
                } else {
                    System.out.println("[!]El ID que has seleccionado ya esta asignado a un Modulo en la base de datos");
                    System.out.println("El modulo no se puede crear si el modulo esta repetido, es obligatorio y unico");
                    System.out.println("Pora favor introduce uno ID valido");
                }
            } else {
                break;
            }
        }
    }

    private static Student insertEnrollmentStudentSelecction() {
        Student student = new Student();
        System.out.println("Para crear una Matricula debes seleccionar un estudiante al que matricular");
        if (Selects.selectAllStudents() != null) {
            System.out.println("Hemos encontrado los siguientes alumnos en la base de datos");
            Menus.printStudentData(Selects.selectAllStudents());
            System.out.println("Escribe el NIA del alumno que quieras seleccionar");
            String nia = scanString();
            if (Selects.findStudentByNia(nia) != null) {
                //El select se hace dos veces pero eso me parecia mejor que crear una variable mas para esta clase que solo se iba a utilizar una vez. Si necesitaramos optimizar las entradas a la base de datos podriamos cambiarlo. 
                student = Selects.findStudentByNia(nia);
                return student;
            } else {//Si no encuentra el alumno en la base de datos significa que el nia seleccionado no existe. Si fuera necesario optimizarlo mas podriamos ahorrarnos esta consulta simplemente guardandonos la lista de estudiantes en la request original, sin embargo considere que asi era mejor porque podia aislar la representacion de los estudiantes en la clase de menus
                System.out.println("[!]El nia que has seleccionado no existe en la base de datos. ");
                System.out.println("Seleccionar un estudiante es obligatorio al crear una Matricula");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un alumno de la lista");
                System.out.println("2. Crear mas alumnos y seleccionar uno de los nuevos");
                System.out.println("3. Abortar la creacion de Matricula");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        student = insertEnrollmentStudentSelecction();
                        return student;
                    //Redirige a la creacion de Alumno
                    case 2:
                        System.out.println("Pasaras al modo de creacion de Alumnos, crea los Alumnos que necesites y cuando acabes volveras al selector.");
                        insertStudentController();
                        student = insertEnrollmentStudentSelecction();
                        return student;
                    //Vuelve al menu principal por lo que aborta la creacion de Matricula
                    case 3:
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, se aborta la creacion de Matricula");
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun estudiante que seleccionar en la base de datos
            System.out.println("No hay ningun estudiante en la base de datos. Pudes crear estudiantes nuevos y asignarle uno a la matricula o puedes abortar la creacion de la matricula");
            System.out.println("¿Deseas crear alumnos nuevos? S/N");
            String response = scanString();
            if (response.equals("S")) {//El usuario ha decidido crear un alumno por lo que se mete en el modo de creacion de alumnos y luego vuelve a elegir
                System.out.println("Entraras en el modo de creacion de Alumno, puedes crear tantos alumnos como desees. Cuando acabes volveras a esta parte para poder seleccionar uno que asignar a tu matricula");
                //Inserta los alumnos que desee insertar
                insertStudentController();
                //Una vez ha acbado de insertar alumnos vuelve aqui, pero como la base de datos ya tiene entradas no llegará a esta parte. A no ser que no haya introducido ninguno en cuyo caso volvera de nuevo a esta parte del if. 
                insertEnrollmentStudentSelecction();
            } else {//Has rechazado la creacion de alumno y vuelve al menu principal
                System.out.println("Saliendo de la creacion de Modulo, puedes volver cuando quieras.");
                mainMenuController();
            }
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return student;
    }

    private static entity.Module insertEnrollmentModuleSelecction() {
        entity.Module module = new entity.Module();
        System.out.println("Para crear una Matricula debes seleccionar un modulo al que asignar el alumno");
        if (Selects.selectAllModules() != null) {//comprubea si hay algun modulo en la base de datos
            System.out.println("Hemos encontrado los siguientes modulos en la base de datos");
            Menus.printModuleData(Selects.selectAllModules());
            System.out.println("Escribe el id del modulo que quieras seleccionar");
            int idModule = scanInt();
            if (Selects.findModuleById(idModule) != null) {//Comprueba si el id seleccionado existe en la base de datos
                module = Selects.findModuleById(idModule);
                return module;
            } else {//Si no existe da las siguientes opciones
                System.out.println("[!]El id de modulo  que has seleccionado no existe en la base de datos. ");
                System.out.println("Seleccionar un modulo es obligatorio al crear una Matricula");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un modulo de la lista");
                System.out.println("2. Crear mas modulos y seleccionar uno de los nuevos");
                System.out.println("3. Abortar la creacion de Matricula");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        module = insertEnrollmentModuleSelecction();
                        return module;
                    //Redirige a la creacion de Alumno
                    case 2:
                        System.out.println("Pasaras al modo de creacion de Modulos, crea los Modulos que necesites y cuando acabes volveras al selector.");
                        insertModuleController();
                        module = insertEnrollmentModuleSelecction();
                        return module;
                    //Vuelve al menu principal por lo que aborta la creacion de Matricula
                    case 3:
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, se aborta la creacion de matricula");
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun modulo que seleccionar en la base de datos
            System.out.println("No hay ningun Modulos en la base de datos. Pudes crear modulos nuevos y asignarle uno a la matricula o puedes abortar la creacion de la matricula");
            System.out.println("¿Deseas crear modulos nuevos? S/N");
            String response = scanString();
            if (response.equals("S")) {//El usuario ha decidido crear un alumno por lo que se mete en el modo de creacion de alumnos y luego vuelve a elegir
                System.out.println("Entraras en el modo de creacion de Modulos, puedes crear tantos modulos como desees. Cuando acabes volveras a esta parte para poder seleccionar uno que asignar a tu matricula");
                //Inserta los alumnos que desee insertar
                insertModuleController();
                //Una vez ha acbado de insertar alumnos vuelve aqui, pero como la base de datos ya tiene entradas no llegará a esta parte. A no ser que no haya introducido ninguno en cuyo caso volvera de nuevo a esta parte del if. 
                insertEnrollmentModuleSelecction();
            } else {//Has rechazado la creacion de alumno y vuelve al menu principal
                System.out.println("Saliendo de la creacion de Matricula, puedes volver cuando quieras.");
                mainMenuController();
            }
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return module;

    }

    //InsertProjectController
    public static void insertProjectController() {
        System.out.println("Estas en el modo de insercion de projectos, se insertaran proyectos hasta que especifiques que el ID del proyecto es 0 ");
        String idProject = "";
        String title = "";
        Student student;
        //El bucle se ejecuta hasta que introduzcan 0
        while (!idProject.equals("0")) {
            System.out.println("Introduce el ID del proyecto:");
            idProject = scanString();
            if (!idProject.equals("0")) {
                //Busca el proyecto en la base de datos, si lo encuentra crea el objeto. Sino lo encuentra sera null y por tanto significa que puedes utilizar ese ID
                if (Selects.findProjectById(idProject) == null) {
                    //No tiene ninguna comprobacion ya que se puede dejar en blanco
                    System.out.println("Introduce el titulo del proyecto: ");
                    title = scanString();

                    //Este metodo se encarga de asegurarse de que vuelve un estudiante ya que es un FK obligatoria
                    student = insertProjectStudentSelecction();
                    Project newProject = new Project(idProject, title, student);
                    Inserts.insertProject(newProject);
                } else {
                    System.out.println("El ID que has seleccionado ya lo tiene asignado otro proyecto");
                }
                //El else se ejecuta si se ha introducido 0 en el id en cuyo caso se salta el resto de pasos
            } else {
                break;
            }

        }
    }

    private static Student insertProjectStudentSelecction() {
        Student student = new Student();
        System.out.println("Para crear un proyecto debes seleccionar un estudiante al que asignarlo, cada alumno solo puede tener un proyecto asignado asi que solo te mostraremos los que aun no tengan ninguno. ");
        if (returnStudentsItemCount() != 0) {
            System.out.println("Hemos encontrado los siguientes alumnos en la base de datos");
            //Imprime los alumnos que aun no tienen ningun proyecto asignado. 
            Menus.printStudentWithoutProjectData();
            System.out.println("Escribe el NIA del alumno que quieras seleccionar");
            String nia = scanString();
            //Busca el NIA en la base de datos
            if (Selects.findStudentByNia(nia)!= null) {
                student = Selects.findStudentByNia(nia);
                return student;
            } else {
                System.out.println("[!]El nia que has seleccionado no existe en la base de datos. ");
                System.out.println("Seleccionar un estudiante es obligatorio al crear un proyecto");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un alumno de la lista");
                System.out.println("2. Crear mas alumnos y seleccionar uno de los nuevos");
                System.out.println("3. Abortar la creacion de Proyecto");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        student = insertEnrollmentStudentSelecction();
                        return student;
                    //Redirige a la creacion de Alumno
                    case 2:
                        System.out.println("Pasaras al modo de creacion de Alumnos, crea los Alumnos que necesites y cuando acabes volveras al selector.");
                        insertStudentController();
                        student = insertEnrollmentStudentSelecction();
                        return student;
                    //Vuelve al menu principal por lo que aborta la creacion de proyecto
                    case 3:
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, se aborta la creacion de proyecto");
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun estudiante que seleccionar en la base de datos
            System.out.println("No hay ningun estudiante en la base de datos. Pudes crear estudiantes nuevos y asignarle uno al proyecto o puedes abortar la creacion del proyecto");
            System.out.println("¿Deseas crear alumnos nuevos? S/N");
            String response = scanString();
            if (response.equals("S")) {//El usuario ha decidido crear un alumno por lo que se mete en el modo de creacion de alumnos y luego vuelve a elegir
                System.out.println("Entraras en el modo de creacion de Alumno, puedes crear tantos alumnos como desees. Cuando acabes volveras a esta parte para poder seleccionar uno que asignar a tu proyecto");
                //Inserta los alumnos que desee insertar
                insertStudentController();
                //Una vez ha acbado de insertar alumnos vuelve aqui, pero como la base de datos ya tiene entradas no llegará a esta parte. A no ser que no haya introducido ninguno en cuyo caso volvera de nuevo a esta parte del if. 
                insertEnrollmentStudentSelecction();
            } else {//Has rechazado la creacion de alumno y vuelve al menu principal
                System.out.println("Saliendo de la creacion de proyecto, puedes volver cuando quieras.");
                mainMenuController();
            }
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return student;
    }

    /////////////////////////////////////////////////////////SELECTS////////////////////////////////////////////////////
    public static void selectsController() {
        if(!isDatabaseEmpty()){
        Menus.selectsMenu();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                if(returnGroupsItemCount()!=0){
                selectsGroupController();
                }else{
                    System.out.println("[!] En la base de datos no hay ningun grupo que pueda ser consultado");
                    System.out.println("Volveras al menu principal");
                    waitingKey();
                    mainMenuController();
                }
                break;
            case 2:
                if(returnStudentsItemCount()!=0){
                selectsStudentController();
                }else{
                    System.out.println("[!] En la base de datos no hay ningun Alumno que pueda ser consultado");
                    System.out.println("Volveras al menu principal");
                    waitingKey();
                    mainMenuController();
                }
                break;
            case 3:
                if(returnModulesItemCount()!=0){
                selectsModuleController();
                }else{
                    System.out.println("[!] En la base de datos no hay ningun Modulo que pueda ser consultado");
                    System.out.println("Volveras al menu principal");
                    waitingKey();
                    mainMenuController();
                }
                break;
               
            case 4:
               if(returnEnrollmentsItemCount()!=0){
                selectsEnrollmentController();
                }else{
                    System.out.println("[!] En la base de datos no hay ninguna matricula que pueda ser consultada");
                    System.out.println("Volveras al menu principal");
                    waitingKey();
                    mainMenuController();
                }
                break;
               
            case 5:
                if(returnProjectsItemCount()!=0){
                selectsProjectController();
                }else{
                    System.out.println("[!] En la base de datos no hay ningun projecto que pueda ser consultado");
                    System.out.println("Volveras al menu principal");
                    waitingKey();
                    mainMenuController();
                }
                break;
            case 6:
                waitingKey();
                mainMenuController();
                break;
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
        }else{
            System.out.println("[!] La base de datos esta vacia, por lo que no se puede imprimir ningun dato");
            System.out.println("Volveras al menu principal");
            waitingKey();
            mainMenuController();
        }
    }

    public static void selectsGroupController() {
        Menus.selectsMenuGroup();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                Menus.printGroupData(Selects.selectAllGroups());
                System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como alumnos? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedGroupInformation(Selects.selectAllGroups());
                    }
                waitingKey();
                mainMenuController();
                break;
            case 2://Busqueda por ID
                selectGroupByIdController();
                waitingKey();
                mainMenuController();
                break;

            case 3:
                selectGroupByClassroomController();
                waitingKey();
                mainMenuController();
                break;
            case 4:
                selectGroupByDescriptionController();
                waitingKey();
                mainMenuController();
                break;
            case 5:
                waitingKey();
                selectsController();
                break;
            case 6:
                waitingKey();
                mainMenuController();
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }
    public static void selectGroupByIdController(){
        System.out.println("Introduce el ID a buscar");
                int id = scanInt();
                List<Group> groupFound = new ArrayList<Group>();
                groupFound.add(Selects.findGroupById(id));
                System.out.println(groupFound.size());
                if(groupFound.get(0)!=null){
                    Menus.printGroupData(groupFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedGroupInformation(groupFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado el id en la base de datos");
                }
    }
    
    public static void selectGroupByClassroomController(){
         System.out.println("Introduce la clase del grupo que deseas buscar: ");
                String classroom = scanString();
                List<Group> groupsFoundClassroom = Selects.findGroupsByClassroom(classroom);
                if(!groupsFoundClassroom.isEmpty()){
                    Menus.printGroupData(groupsFoundClassroom);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedGroupInformation(groupsFoundClassroom);
                    }
                    
                }else{
                    System.out.println("[!] No se ha encontrado ningun grupo con esa clase asignada");
                }
    }
    
    public static void selectGroupByDescriptionController(){
        System.out.println("Introduce la clase del grupo que deseas buscar: ");
                String description = scanString();
                List<Group> groupsFoundDescription = Selects.findGroupsByDescription(description);
                if(!groupsFoundDescription.isEmpty()){
                    Menus.printGroupData(groupsFoundDescription);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir los alumnos que tiene asignados el grupo? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedGroupInformation(groupsFoundDescription);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ningun grupo con esa descripcion asignada");
                }
    }
    
    public static void selectExtendedGroupInformation(List<Group> groups){
        System.out.println("///////////////////////////////////////////");
        for (Group group: groups) {
           if(Selects.findStudentsByGroup(group)!=null){
               System.out.println("Estos son los alumnos relacionados con el grupo " + group.getClassroom() + " con el ID " +group.getGroupId());
               Menus.printStudentData(Selects.findStudentsByGroup(group));
           }else{
               System.out.println("El grupo " + group.getClassroom() + " con el ID " + group.getGroupId() + " no tiene ningun alumo asignado");
           }
           waitingKey();
        }
    }
    
    public static void selectsStudentController() {
        Menus.selectsMenuStudent();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                //Momentaneo ya que hay que actualizar a la version pro completa
                Menus.printStudentData(Selects.selectAllStudents());
                System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como proyectos y matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedStudentInformation(Selects.selectAllStudents());
                    }
                waitingKey();
                mainMenuController();
                break;
            case 2:
                selectStudentByNiaController();
                waitingKey();
                mainMenuController();
                break;
            case 3:
                selectStudentByNameController();
                waitingKey();
                mainMenuController();
                break;
            case 4:
                selectStudentByLastnameController();
                waitingKey();
                mainMenuController();
                break;
            case 5:
                selectStudentByGroupController();
                waitingKey();
                mainMenuController();
                break;
            case 6:
                waitingKey();
                selectsController();
                break;
            case 7:
                waitingKey();
                mainMenuController();
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }
    
    public static void selectStudentByNiaController(){
        System.out.println("Introduce el NIA a buscar");
                String nia = scanString();
                List<Student> studentFound = new ArrayList<Student>();
                studentFound.add(Selects.findStudentByNia(nia));
                if(studentFound.get(0)!=null){
                    Menus.printStudentData(studentFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas o proyecto? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedStudentInformation(studentFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado el NIA en la base de datos");
                }
    }
    
    public static void selectStudentByNameController(){
        System.out.println("Introduce el nombre del alumno que deseas buscar: ");
                String name = scanString();
                List<Student> studentsFound = Selects.findStudentsByName(name);
                if(studentsFound.isEmpty()){
                    Menus.printStudentData(studentsFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas o proyecto? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                         selectExtendedStudentInformation(studentsFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ningun alumno con ese nombre asignado");
                }
    }
    public static void selectStudentByLastnameController(){
        System.out.println("Introduce el apellido del alumno que deseas buscar: ");
                String lastname = scanString();
                List<Student> studentsFound = Selects.findStudentsByLastname(lastname);
                if(studentsFound.isEmpty()){
                    Menus.printStudentData(studentsFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas o proyecto? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                         selectExtendedStudentInformation(studentsFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ningun Alumno con ese apellido asignado");
                }
    }
    public static void selectStudentByGroupController(){
                Group groupSelected = selectStudentGroupSelector();
                List<Student> studentsFound = Selects.findStudentsByGroup(groupSelected);
                if(!studentsFound.isEmpty()){
                    Menus.printStudentData(studentsFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas o proyecto? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                         selectExtendedStudentInformation(studentsFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ningun Alumno con ese grupo asignado");
                }
    }
   public static Group selectStudentGroupSelector(){
        System.out.println("Se imprimiran los Grupos de la base de datos para que puedas buscar los estudiantes que tiene asignado");
         Group group = new Group();
        if (returnGroupsItemCount() != 0) {
            System.out.println("Elige uno de los siguientes Grupos ya creados o crea uno nuevo metiendo un indice que aun no exista diferente de cero: ");
            Menus.printGroupData(Selects.selectAllGroups());
            System.out.println("Introduce el numero de identificacion que quiera utilizar: ");
            int selecction = scanInt();
            if (Selects.findGroupById(selecction) != null) {//Si es nulo lo puede crear porque significaque el ID que intentas introducir no lo esta gastando ningun grupo en la base de datos
                //La consulta se vuelve a hacer, un poco redundante quizas pero no queria tener una variable mas en este metodo.
                Group groupSelected = Selects.findGroupById(selecction);
                return groupSelected;
            } else {//Si no es nulo significa que ya hay un objeto con ese ID en la base de datos y por tanto no puede utilizarlo
                System.out.println("Ha seleccionado un id que no esta en la base de datos");
                System.out.println("Por favor seleccione una de las siguientes opciones: ");
                System.out.println("1. Volver a elegir un grupo ya existente");
                System.out.println("2. Volver al menu de consultas");
                System.out.println("3. Volver al menu principal");
                int optionSelected = scanInt();
                switch (optionSelected) {
                    //Volver a elegir un Grupo
                    case 1:
                        waitingKey();
                        group = insertStudentGroupSelecction();
                        return group;
                    //Volver al menu de consulta
                    case 2:
                        System.out.println("Volveras a el menu de consulta ");
                        waitingKey();
                        selectsController();
                    case 3:
                        waitingKey();
                        mainMenuController();
                        break;
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, se aborta la consulta de alumno");
                        mainMenuController();
                }
            }

        } else {
            System.out.println("[!]No hay ningun grupo en la base de datos. Esto debe ser un error ya que no deberia haber ningun estudiante sin grupo");
        }
        return group;
    }
    public static void selectExtendedStudentInformation(List<Student> students){
        System.out.println("///////////////////////////////////////////");
        for (Student student: students) {
             waitingKey();
           if(Selects.findEnrollmentsByStudent(student)!=null){
               System.out.println("Estos son las matriculas relacionados con el alumno " + student.getName()+ " con el NIA " +student.getNia());
               Menus.printEnrollmentData(Selects.findEnrollmentsByStudent(student));
           }else{
               System.out.println("El Alumno " + student.getName()+ " con el NIA " + student.getNia() + " no tiene ninguna Matricula asignada");
           }
            System.out.println("///////////////////////////////////////////");
            if(Selects.findProjectsByStudent(student)!=null){
                System.out.println("Este es el proyecto que tiene asignado el alumno " + student.getName() + " con el nia " + student.getNia());
                Menus.printProjectData(Selects.findProjectsByStudent(student));
            }else{
                System.out.println("El Alumno " + student.getName()+ " con el NIA " + student.getNia() + " no tiene ningun proyecto asignado");
            }
        }
    }
    public static void selectsModuleController() {
        Menus.selectsMenuModule();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                //Momentaneo ya que hay que actualizar a la version pro completa
                Menus.printModuleData(Selects.selectAllModules());
                System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedModuleInformation(Selects.selectAllModules());
                    }
                waitingKey();
                mainMenuController();
                break;
            case 2:
                selectsModuleController(); 
                waitingKey();
                mainMenuController();
                break;
            case 3:
                selectModuleByDescriptionController();
                waitingKey();
                mainMenuController();
                break;
            case 4:
                selectModuleByNumHoursController();
                waitingKey();
                mainMenuController();
                break;
            case 5:
                waitingKey();
                selectsController();
                break;
            case 6:
                waitingKey();
                mainMenuController();
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }
    
    public static void selectModuleByIdController(){
        System.out.println("Introduce el ID a buscar");
                int id = scanInt();
                List<entity.Module> moduleFound = new ArrayList<entity.Module>();
                moduleFound.add(Selects.findModuleById(id));
                System.out.println(moduleFound.size());
                if(moduleFound.get(0)!=null){
                    Menus.printModuleData(moduleFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir las matriculas que tiene el modulo? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedModuleInformation(moduleFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado el id en la base de datos");
                }
    }
    
    public static void selectModuleByDescriptionController(){
        System.out.println("Introduce la descripcion del modulo que deseas buscar [!]Aviso: Tiene que ser exacta ");
                String description = scanString();
                List<entity.Module> modulesFound = Selects.findModulesByDescription(description);
                if(modulesFound.isEmpty()){
                    Menus.printModuleData(modulesFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                         selectExtendedModuleInformation(modulesFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ningun modulo con esa descripcion asignada");
                }
    }
    
    public static void selectModuleByNumHoursController(){
        System.out.println("Introduce la cantidad de horas del modulo que deseas buscar [!]Aviso: Tiene que ser exacta ");
                int numHours = scanInt();
                List<entity.Module> modulesFound = Selects.findModulesByNumHours(numHours);
                if(modulesFound.isEmpty()){
                    Menus.printModuleData(modulesFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir las matriculas que tienen los modulos asignadas? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                         selectExtendedModuleInformation(modulesFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ningun modulo con esa cantidad de horas asignada asignada");
                }
    }
    
    public static void selectExtendedModuleInformation(List<entity.Module> modules){
         System.out.println("//////////////////////////////////////");
         for (entity.Module module: modules) {
             waitingKey();
           if(Selects.findEnrollmentsByModule(module)!=null){
               System.out.println("Estos son las matriculas relacionados con el modulo con ID " + module.getModuleId());
               Menus.printEnrollmentData(Selects.findEnrollmentsByModule(module));
           }else{
               System.out.println("El modulo con ID " + module.getModuleId() + " no tiene ninguna matricula asignada" );
           }
            System.out.println("///////////////////////////////////////////");
        }
    }
    
    public static void selectsEnrollmentController() {
        Menus.selectsMenuEnrollment();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                Menus.printEnrollmentData(Selects.selectAllEnrollments());
                System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como alumnos y modulos? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedEnrollmentInformation(Selects.selectAllEnrollments());
                    }
                waitingKey();
                mainMenuController();
                break;
            case 2:
                selectEnrollmentbyIdController();
                mainMenuController();
                break;
            case 3:
                selectEnrollmentByDescription();
                waitingKey();
                mainMenuController();
                break;
            case 4:
                selectEnrollmentByStudent();
                waitingKey();
                mainMenuController();
                break;
            case 5:
                selectEnrollmentByModule();
                waitingKey();
                mainMenuController();
                break;
            case 6:
                waitingKey();
                selectsController();
                break;
            case 7:
                waitingKey();
                mainMenuController();
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }
    
    public static void selectEnrollmentbyIdController(){
         System.out.println("Introduce el ID a buscar");
                int id = scanInt();
                List<Enrollment> enrollmentFound = new ArrayList<Enrollment>();
                enrollmentFound.add(Selects.findEnrollmentById(id));
                System.out.println(enrollmentFound.size());
                if(enrollmentFound.get(0)!=null){
                    Menus.printEnrollmentData(enrollmentFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas los alumnos y modulos relacionados? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedEnrollmentInformation(enrollmentFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado el id en la base de datos");
                }
    }
    
    public static void selectEnrollmentByDescription(){
        System.out.println("Introduce la descripcion de la matricula que deseas buscar [!]Aviso: Tiene que ser exacta ");
                String description = scanString();
                List<Enrollment> enrollmentFound = Selects.findEnrollmentsByDescription(description);
                if(enrollmentFound.isEmpty()){
                    Menus.printEnrollmentData(enrollmentFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    System.out.println("¿Deseas los alumnos y modulos relacionados? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedEnrollmentInformation(enrollmentFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ninguna matricula con esa descripcion asignada");
                }
    }
    
    public static void selectEnrollmentByStudent(){
        Student studentSelected = selectsStudentSelector();
                List<Enrollment> enrollmentFound = Selects.findEnrollmentsByStudent(studentSelected);
                if(enrollmentFound.isEmpty()){
                    Menus.printEnrollmentData(enrollmentFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    System.out.println("¿Deseas los alumnos y modulos relacionados? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedEnrollmentInformation(enrollmentFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ninguna matricula con esa descripcion asignada");
                }
    }
    
    public static Student selectsStudentSelector(){
         Student student = new Student();
        System.out.println("Selecciona un estudiante para buscarlo en la base de datos");
        if (Selects.selectAllStudents() != null) {
            System.out.println("Hemos encontrado los siguientes alumnos en la base de datos");
            Menus.printStudentData(Selects.selectAllStudents());
            System.out.println("Escribe el NIA del alumno que quieras seleccionar");
            String nia = scanString();
            if (Selects.findStudentByNia(nia) != null) {
                //El select se hace dos veces pero eso me parecia mejor que crear una variable mas para esta clase que solo se iba a utilizar una vez. Si necesitaramos optimizar las entradas a la base de datos podriamos cambiarlo. 
                student = Selects.findStudentByNia(nia);
                return student;
            } else {//Si no encuentra el alumno en la base de datos significa que el nia seleccionado no existe. Si fuera necesario optimizarlo mas podriamos ahorrarnos esta consulta simplemente guardandonos la lista de estudiantes en la request original, sin embargo considere que asi era mejor porque podia aislar la representacion de los estudiantes en la clase de menus
                System.out.println("////////////////////////////////////////////");
                System.out.println("[!]El nia que has seleccionado no existe en la base de datos. ");
                System.out.println("");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un alumno de la lista");
                System.out.println("2. Volver al menu de mostrar datos");
                System.out.println("3. Volver al menu principal");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        waitingKey();
                        student = insertEnrollmentStudentSelecction();
                        return student;
                    //Redirige al menu de mostrar datos
                    case 2:
                        waitingKey();
                        selectsController();
                        break;
                    //Vuelve al menu principal 
                    case 3:
                        waitingKey();
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, vuelve al menu principal");
                        waitingKey();
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun estudiante que seleccionar en la base de datos
            System.out.println("No hay ningun estudiante en la base de datos. Esto debe tratarse de un error, ya que no deberia haber matriculas si no hay alumnos");
           
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return student;
    }
    
   
    public static void selectEnrollmentByModule(){
        entity.Module moduleSelected = selectEnrollmentModuleSelector();
                List<Enrollment> enrollmentFound = Selects.findEnrollmentsByModule(moduleSelected);
                if(enrollmentFound.isEmpty()){
                    Menus.printEnrollmentData(enrollmentFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    System.out.println("¿Deseas los alumnos y modulos relacionados? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedEnrollmentInformation(enrollmentFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ninguna matricula con esa descripcion asignada");
                }
    }
    
    public static entity.Module selectEnrollmentModuleSelector(){
        entity.Module module = new entity.Module();
        System.out.println("Ahora podras seleccionar el modulo que desees buscar en la base de datos");
        if (Selects.selectAllModules() != null) {//comprubea si hay algun modulo en la base de datos
            System.out.println("Hemos encontrado los siguientes modulos en la base de datos");
            Menus.printModuleData(Selects.selectAllModules());
            System.out.println("Escribe el id del modulo que quieras seleccionar");
            int idModule = scanInt();
            if (Selects.findModuleById(idModule) != null) {//Comprueba si el id seleccionado existe en la base de datos
                module = Selects.findModuleById(idModule);
                return module; 
            } else {//Si no existe da las siguientes opciones
                System.out.println("////////////////////////////////////////");
                System.out.println("[!]El id de modulo  que has seleccionado no existe en la base de datos. ");
                System.out.println("");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un modulo de la lista");
                System.out.println("2. Volver al selector de visualizacion de datos");
                System.out.println("3. Volver al menu principal");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        module = insertEnrollmentModuleSelecction();
                        return module;
                    //Redirige a al menu de Selects
                    case 2:
                        System.out.println("Volveras al menu de visualizacion de datos");
                        waitingKey();
                        selectsController();
                    //Vuelve al menu principal por lo que aborta la creacion de Matricula
                    case 3:
                        waitingKey();
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, volveras al menu principal");
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun modulo que seleccionar en la base de datos
            System.out.println("No hay ningun Modulo en la base de datos. Esto debe tratarse de un error puesto que so hay matriculas debe haber modulos tambien. ");
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return module;

    }

    public static void selectExtendedEnrollmentInformation(List<Enrollment> enrollments){
         System.out.println("//////////////////////////////////////");
         for (Enrollment enrollment: enrollments) {
               waitingKey();
               System.out.println("Este es el alumno relacionado con la matricula con ID " + enrollment.getIdEnrollment());
              //Una conversion rapida ya que printStudentData requiere que le des la informacion en forma de lista aunque esta solo tenga un elemento
               List <Student> studentAsList = new ArrayList <Student>(); 
               studentAsList.add(enrollment.getStudent()); 
               Menus.printStudentData(studentAsList);
            System.out.println("///////////////////////////////////////////");
             System.out.println("Este es el modulo al que esta matriculado el alumno relacionado con la matricula con ID " + enrollment.getIdEnrollment());
             //Una conversion rapida ya que printStudentData requiere que le des la informacion en forma de lista aunque esta solo tenga un elemento
               List <entity.Module> moduleAsList = new ArrayList <entity.Module>(); 
               moduleAsList.add(enrollment.getModule()); 
               Menus.printModuleData(moduleAsList);
           
        }
    }
    
    
    public static void selectsProjectController() {
        Menus.selectsMenuProject();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                Menus.printProjectData(Selects.selectAllProjects());
                System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir la informacion del alumno al que está asignado? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedProjectInformation(Selects.selectAllProjects());
                    }
                waitingKey();
                mainMenuController();
                break;
            case 2:
                selectProjectById();
                waitingKey();
                mainMenuController();
                break;
            case 3:
                selectProjectByTitle();
                waitingKey();
                mainMenuController();
                break;
            case 4:
               selectProjectByStudent();
               waitingKey();
                mainMenuController();
                break;
            case 5:
                waitingKey();
                selectsController();
                break;
            case 6:
                waitingKey();
                mainMenuController();
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }
    
    public static void selectProjectById(){
         System.out.println("Introduce el ID a buscar");
                String id = scanString();
                List<Project> projectFound = new ArrayList<Project>();
                projectFound.add(Selects.findProjectById(id));
                System.out.println(projectFound.size());
                if(projectFound.get(0)!=null){
                    Menus.printProjectData(projectFound);
                    System.out.println("");
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir la informacion del alumno al que está asignado? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedProjectInformation(projectFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado el id en la base de datos");
                }
    }
    
    public static void selectProjectByTitle(){
        System.out.println("Introduce el titulo del proyecto que deseas buscar");
                String title = scanString();
                List<Project> projectFound = Selects.findProjectsByTitle(title);
                if(projectFound.isEmpty()){
                    Menus.printProjectData(projectFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    System.out.println("¿Deseas imprimir la informacion del alumno al que está asignado? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedProjectInformation(projectFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ninguna matricula con esa descripcion asignada");
                }
    }
    
    public static void selectProjectByStudent(){
        Student studentSelected = selectsStudentSelector(); 
        List<Project> projectFound = Selects.findProjectsByStudent(studentSelected);
                if(projectFound.isEmpty()){
                    Menus.printProjectData(projectFound);
                    System.out.println("///////////////////////////////////////////////////////////");
                    System.out.println("¿Deseas imprimir otra informacion relevante como sus matriculas? [Introduce + para mostrar o pulsa enter para continuar]");
                    System.out.println("¿Deseas los alumnos y modulos relacionados? [Introduce + para mostrar o pulsa enter para continuar]");
                    String response = scanString();
                    if(response.equals("+")){
                        selectExtendedProjectInformation(projectFound);
                    }
                }else{
                    System.out.println("[!] No se ha encontrado ninguna matricula con esa descripcion asignada");
                }
    }
    
    
    public static void selectExtendedProjectInformation(List<Project> projects){
        System.out.println("//////////////////////////////////////");
         for (Project project: projects) {
               waitingKey();
               System.out.println("Este es el alumno asignado al proyecto con ID " + project.getProjectId());
               //Una conversion rapida ya que printStudentData requiere que le des la informacion en forma de lista aunque esta solo tenga un elemento
               List <Student> studentAsList = new ArrayList <Student>(); 
               studentAsList.add(project.getStudent()); 
               Menus.printStudentData(studentAsList);
        }
    }

    ///////////////////////////////////////////////////// Otras Funcionalidades ///////////////////////////////////////////////////
    public static void waitingKey() {
        System.out.println("Pulsa enter para continuar...");
        String waiting = scanString();
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
    
    public static boolean isDatabaseEmpty(){
        int totalItemCount = returnGroupsItemCount() + returnStudentsItemCount() + returnEnrollmentsItemCount() + returnModulesItemCount() + returnProjectsItemCount();
        if(totalItemCount == 0){
            return true;
        }else{
            return false;
        }
    }
   //////////////////////////////////////////////////////////// DELETES //////////////////////////////////////////////
    
    public static void deletesController(){
       Menus.deletesMenu();
        int selecction = scanInt();
        switch (selecction) {
            case 1:
                deleteGroupController();
                waitingKey();
                mainMenuController();
                break;
            case 2:
                deleteStudentController();
                waitingKey();
                mainMenuController();
                break;
            case 3:
                deleteModuleController();
                mainMenuController();
                break;
            case 4:
               deleteEnrollmentController();
               waitingKey();
                mainMenuController();
                break;
            case 5:
                deleteProjectController();
                waitingKey();
                mainMenuController();
                break;
            case 6:
                waitingKey();
                mainMenuController();
            default:
                System.out.println("La opcion que has seleccionado no existe, volveras al menu principal");
                waitingKey();
                mainMenuController();
                break;
        }
    }
    
    public static void deleteGroupController(){
        System.out.println("Selecciona el grupo que deseas borrar");
        Group group = deleteGroupSelector();
        if(Selects.findStudentsByGroup(group)== null){//Si la lista vuelve vacia significa que no hay ningun estudiante y por tanto la entrada puede ser borrada
            Deletes.deleteGroup(group);
        }else{
            System.out.println("[!]No se puede eliminar el grupo ya que hay alumnos que lo tienen asignado");
            System.out.println("Volveras al menu de borrar, borra primero los alumnos si deseas borrar este grupo");
            waitingKey();
            deletesController();
        }
    }
    
    public static Group deleteGroupSelector(){
         System.out.println("Se imprimiran los Grupos de la base de datos para que puedas elegir cual quieres borrar");
         Group group = new Group();
        if (returnGroupsItemCount() != 0) {
            System.out.println("Elige uno de los siguientes Grupos ya creados o crea uno nuevo metiendo un indice que aun no exista diferente de cero: ");
            Menus.printGroupData(Selects.selectAllGroups());
            System.out.println("Introduce el numero de identificacion que quiera utilizar: ");
            int selecction = scanInt();
            if (Selects.findGroupById(selecction) != null) {//Si es nulo lo puede crear porque significaque el ID que intentas introducir no lo esta gastando ningun grupo en la base de datos
                //La consulta se vuelve a hacer, un poco redundante quizas pero no queria tener una variable mas en este metodo.
                Group groupSelected = Selects.findGroupById(selecction);
                return groupSelected;
            } else {//Si no es nulo significa que ya hay un objeto con ese ID en la base de datos y por tanto no puede utilizarlo
                System.out.println("Ha seleccionado un id que no esta en la base de datos");
                System.out.println("Por favor seleccione una de las siguientes opciones: ");
                System.out.println("1. Volver a elegir un grupo ya existente");
                System.out.println("2. Volver al menu principal");
                int optionSelected = scanInt();
                switch (optionSelected) {
                    //Volver a elegir un Grupo
                    case 1:
                        waitingKey();
                        group = insertStudentGroupSelecction();
                        return group;
                    //Volver al menu de consulta
                    case 2:
                        waitingKey();
                        mainMenuController();
                        break;
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, se aborta la consulta de alumno");
                        mainMenuController();
                }
            }

        } else {
            System.out.println("[!]No hay ningun grupo en la base de datos. Esto debe ser un error ya que no deberia dejarte borrar el grupo si no hay ninguno en la base de datos");
        }
        return group;
        
    }
    
    public static void deleteStudentController(){
        System.out.println("Selecciona el Alumno que quieras borrar");
        Student student = deleteStudentSelector();
        if(Selects.findProjectsByStudent(student)==null){
            Deletes.deleteStudent(student);   
            System.out.println("Borrado realizado con exito");
        }else{
            if(student.getGroup().equals("FCT")){
                System.out.println("El estudiante tiene un proyecto asignado aunque es del grupo FCT. Tanto el proyecto como el alumno seran borrados de la base de datos");
                Deletes.deleteProject(Selects.findProjectsByStudent(student).get(0));
                Deletes.deleteStudent(student);
                System.out.println("Borrado realizado con exito");
                waitingKey();
            }else{
                System.out.println("[!] No puedes eliminar el alumno ya que tiene un proyecto asignado");
                System.out.println("Elimina antes el proyecto si deseas eliminar el alumno");
                System.out.println("Volveras al menu de borrado");
                waitingKey();
                deletesController();
            }
        }
    }
    
    public static Student deleteStudentSelector(){
        Student student = new Student();
        System.out.println("Selecciona un estudiante para borrarlo de la base de datos");
        if (Selects.selectAllStudents() != null) {
            System.out.println("Hemos encontrado los siguientes alumnos en la base de datos");
            Menus.printStudentData(Selects.selectAllStudents());
            System.out.println("Escribe el NIA del alumno que quieras seleccionar");
            String nia = scanString();
            if (Selects.findStudentByNia(nia) != null) {
                //El select se hace dos veces pero eso me parecia mejor que crear una variable mas para esta clase que solo se iba a utilizar una vez. Si necesitaramos optimizar las entradas a la base de datos podriamos cambiarlo. 
                student = Selects.findStudentByNia(nia);
                return student;
            } else {//Si no encuentra el alumno en la base de datos significa que el nia seleccionado no existe. Si fuera necesario optimizarlo mas podriamos ahorrarnos esta consulta simplemente guardandonos la lista de estudiantes en la request original, sin embargo considere que asi era mejor porque podia aislar la representacion de los estudiantes en la clase de menus
                System.out.println("////////////////////////////////////////////");
                System.out.println("[!]El nia que has seleccionado no existe en la base de datos. ");
                System.out.println("");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un alumno de la lista");
                System.out.println("2. Volver al menu principal");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        waitingKey();
                        student = insertEnrollmentStudentSelecction();
                        return student;
                  
                    //Vuelve al menu principal 
                    case 2:
                        waitingKey();
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, vuelve al menu principal");
                        waitingKey();
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun estudiante que seleccionar en la base de datos
            System.out.println("No hay ningun estudiante en la base de datos. Esto debe tratarse de un error, ya que no deberia haber matriculas si no hay alumnos");
           
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return student;
    }
    
    public static void deleteModuleController(){
        System.out.println("Elige el modulo que deseas borrar");
        entity.Module module = deleteModuleSelector();
        if(Selects.findEnrollmentsByModule(module)!= null){
            System.out.println("Hay alumnos matriculados en el modulo que intentas borrar, por lo que seran eliminados ");
        }
    }
    
    public static entity.Module deleteModuleSelector(){
        entity.Module module = new entity.Module();
        System.out.println("Ahora podras seleccionar el modulo que desees borrar de la base de datos");
        if (Selects.selectAllModules() != null) {//comprubea si hay algun modulo en la base de datos
            System.out.println("Hemos encontrado los siguientes modulos en la base de datos");
            Menus.printModuleData(Selects.selectAllModules());
            System.out.println("Escribe el id del modulo que quieras seleccionar");
            int idModule = scanInt();
            if (Selects.findModuleById(idModule) != null) {//Comprueba si el id seleccionado existe en la base de datos
                module = Selects.findModuleById(idModule);
                return module; 
            } else {//Si no existe da las siguientes opciones
                System.out.println("////////////////////////////////////////");
                System.out.println("[!]El id de modulo  que has seleccionado no existe en la base de datos. ");
                System.out.println("");
                System.out.println("Puedes elegir entre las siguientes opciones: ");
                System.out.println("1. Volver a elegir un modulo de la lista");
                System.out.println("2. Volver al menu principal");
                int response = scanInt();
                switch (response) {
                    //Volver a elegir otro alumno de la lista
                    case 1:
                        module = insertEnrollmentModuleSelecction();
                        return module;
                    //Redirige a al menu de Selects
                   
                    //Vuelve al menu principal por lo que aborta la creacion de Matricula
                    case 2:
                        waitingKey();
                        mainMenuController();
                        break;
                    //Igual que la opcion 3 pero con una pequeña explicacion
                    default:
                        System.out.println("La opcion que has seleccionado no es valida, por tanto, volveras al menu principal");
                        mainMenuController();
                }

            }
        } else {//Este else se ejecuta cuando no hay ningun modulo que seleccionar en la base de datos
            System.out.println("No hay ningun Modulo en la base de datos. Esto debe tratarse de un error puesto que so hay matriculas debe haber modulos tambien. ");
        }
        //Este return es solo para que permita compilar. Si no hay ningun error no deberia ejecutarse nunca.
        return module;
    }
    // Vaciar la base de datos
    public static void deleteAllDatabaseSelected() {
        
        if (!isDatabaseEmpty()) {
            Menus.databaseDeleteWarning();
            String response = scanString();
            if (!response.equals("X")) {
                deleteAllDatabaseOperation();
            }
        } else {
            System.out.println("[!] No se ha continuado con la operacion porque la base de datos ya esta vacia");
        }
    }

    public static void deleteAllDatabaseOperation() {
        Deletes.deleteAllProjects();
        Deletes.deleteAllEnrollments();
        Deletes.deleteAllStudents();
        Deletes.deleteAllModules();
        Deletes.deleteAllGroups();

        System.out.println("Base de datos eliminada con exito");
    }

}
