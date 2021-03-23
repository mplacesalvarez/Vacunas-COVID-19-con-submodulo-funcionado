package Main;

import metodos.metodos_print;
import Clases.Persona;
import Clases.Vacuna;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Esta clase contiene los métodos que constituyen el menú de la aplicación.
 *
 * @author Manuel Places Alvarez
 */


public class Metodos {

    Scanner sc;

    File listper = new File("Listado personas.txt");

    File listvac = new File("Listado vacunas.txt");

    metodos_print metobx = new metodos_print();
    ArrayList<Persona> listapersonas = new ArrayList<Persona>();
    ArrayList<Vacuna> listavacunas = new ArrayList<Vacuna>();
    private int edad, num_vac_inyect = 0, vac_quedan;
    private String riesgo;
    private String nombre, apellidos, dni;
    private int opcionmenu;

    /**
     * Método que crea el menu de la aplicacón
     */
    public void menu() {

        try {
            opcionmenu = Integer.parseInt(JOptionPane.showInputDialog(metobx.mensaje()));

            switch (opcionmenu) {
                case 1:
                    añadirpersona();
                    break;
                case 2:
                    añadirvacuna();
                    break;
                case 3:
                    listado();
                    break;
                case 4:
                    buscar();
                    break;
                case 5:
                    informacion();
                    break;
                case 6:
                    editar();
                    break;

            }


        } catch (NumberFormatException ex) {
        }
    }//Cierre del método

    /**
     * Método que añade personas a la lista
     */
    public void añadirpersona() {
        int cont = 0;


        setNombre(JOptionPane.showInputDialog("Nombre: "));
        setApellidos(JOptionPane.showInputDialog("Apellidos: "));
        setDni(JOptionPane.showInputDialog("DNI: "));
        String[] palabra;
        try {


            sc = new Scanner(new File("Listado personas.txt"));
            try {
                while (sc.hasNextLine()) {
                    String s = sc.nextLine();
                    palabra = s.split(" ");
                    for (int i = 0; i < palabra.length; i++) {
                        if (palabra[i].equalsIgnoreCase(dni)) {
                            cont++;
                        }
                    }

                }

            } catch (ArrayIndexOutOfBoundsException ex) {
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (cont != 0) {
            System.out.println("Ya hay una persona en la lista con ese DNI.");
            return;
        }

        setEdad(Integer.parseInt(JOptionPane.showInputDialog("Edad: ")));
        setRiesgo(JOptionPane.showInputDialog("¿Es persona de riesgo (Si/No)?: "));


        String nombre_vac = " ";


        if (listvac.length() == 0) {
            nombre_vac = "No hay vacunas";
        } else {

            try {

                String[] palabras;
                sc = new Scanner(new File("Listado vacunas.txt"));
                ArrayList<String> nombre_vacunas = new ArrayList<String>();

                while (sc.hasNextLine()) {
                    String s = sc.nextLine();
                    palabras = s.split(" ");

                    try {

                        nombre_vacunas.add(palabras[1]);

                    } catch (IndexOutOfBoundsException ex) {
                    }


                }
                int numero = (int) (Math.random() * nombre_vacunas.size());

                try {
                    nombre_vac = nombre_vacunas.get(numero);
                } catch (IndexOutOfBoundsException ex) {
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


        Persona obx = new Persona(nombre, apellidos, dni, edad, riesgo, nombre_vac);

        listapersonas.add(obx);
        FileWriter wrtp = null;

        {
            try {
                wrtp = new FileWriter(listper, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            wrtp.write("\n" + obx.toString());
            wrtp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }//Cierre del método

    /**
     * Método que añade vacunas a la lista
     */
    public void añadirvacuna() {

        Vacuna obx = new Vacuna();
        obx.setNombre(JOptionPane.showInputDialog("Nombre:"));
        obx.setDisponibles(Integer.parseInt(JOptionPane.showInputDialog("Número de dosis disponibles:")));
        obx.setNum_dosis(Integer.parseInt(JOptionPane.showInputDialog("Número de dosis necesarias: ")));

        listavacunas.add(obx);
        FileWriter wrtv = null;

        {
            try {
                wrtv = new FileWriter(listvac, true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            wrtv.write(obx.toString());
            wrtv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n");
    }//Cierre del método

    /**
     * Método que visualiza la lista de personas
     */
    public void listado() {

        System.out.println("**** PERSONAS EN LA LISTA ****");

        try {
            sc = new Scanner(new File("Listado personas.txt"));

            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                System.out.println(s);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (listper.length() == 0) {
            System.out.println("No hay personas en la lista.");
        }

        System.out.println("\n");
    }

    public void buscar() {
        if (listper.length() == 0) {
            metobx.mensaje3();
            metobx.mensaje2();
        } else {
            int option = Integer.parseInt(JOptionPane.showInputDialog("Elija un criterio de busqueda: \n1) Nombre.\n2) Apellidos.\n3) DNI."));
            switch (option) {
                case 1:
                    String nombre = JOptionPane.showInputDialog("Nombre: ");
                    metobx.mensaje3();


                    try {
                        sc = new Scanner(new File("Listado personas.txt"));
                        String[] palabras;

                        int cont = 0;
                        while (sc.hasNextLine()) {
                            String s = sc.nextLine();

                            palabras = s.split(" ");

                            for (int i = 0; i < palabras.length; i++) {
                                if (nombre.equalsIgnoreCase(palabras[i])) {
                                    for (i = 0; i < palabras.length; i++) {
                                        System.out.print(palabras[i] + " ");
                                        cont++;
                                    }
                                    System.out.println("\n");
                                }

                            }

                        }
                        if (cont == 0) {
                            metobx.mensaje4();
                        }

                        sc.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    break;

                case 2:
                    String apellidos = JOptionPane.showInputDialog("Apellidos: ");

                    metobx.mensaje3();

                    try {
                        sc = new Scanner(new File("Listado personas.txt"));
                        String[] palabras;
                        int cont = 0;
                        while (sc.hasNextLine()) {
                            String s = sc.nextLine();

                            palabras = s.split(" ");

                            for (int i = 0; i < palabras.length; i++) {
                                if (apellidos.equalsIgnoreCase(palabras[i])) {
                                    for (i = 0; i < palabras.length; i++) {
                                        System.out.print(palabras[i] + " ");
                                        cont++;
                                    }
                                    System.out.println("\n");
                                }


                            }


                        }
                        if (cont == 0) {
                            metobx.mensaje5();
                        }


                        sc.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    break;


                case 3:
                    String dni = JOptionPane.showInputDialog("DNI: ");

                    metobx.mensaje3();


                    try {
                        sc = new Scanner(new File("Listado personas.txt"));
                        String[] palabras;
                        int cont = 0;
                        while (sc.hasNextLine()) {
                            String s = sc.nextLine();

                            palabras = s.split(" ");

                            for (int i = 0; i < palabras.length; i++) {
                                if (dni.equalsIgnoreCase(palabras[i])) {
                                    for (i = 0; i < palabras.length; i++) {
                                        System.out.print(palabras[i] + " ");
                                        cont++;
                                    }
                                    System.out.println("\n");
                                }


                            }

                        }
                        if (cont == 0) {
                            metobx.mensaje6();
                        }


                        sc.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    break;

            }
        }
    }//Cierre del método


    /**
     * Método que visualiza la lista de vacunas
     */

    public void informacion() {

        System.out.println("**** VACUNAS DISPONIBLES EN ESPAÑA ****");
        /**for (int i = 0; i < listavacunas.size(); i++) {
         System.out.println(listavacunas.get(i).toString());
         }*/


        try {
            sc = new Scanner(new File("Listado vacunas.txt"));

            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                System.out.println(s);


            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (listvac.length() == 0) {
            System.out.println("No hay vacunas en la lista.");
        }

        System.out.println("\n");
    }//Cierre del método

    /**
     * Método para cambiar los datos acerca de una vacuna o persona.
     */

    public void editar() {
        int option = Integer.parseInt(JOptionPane.showInputDialog("1) Editar personas\n2) Editar vacunas"));
        switch (option) {
            case 1:
                String option1 = JOptionPane.showInputDialog("Escriba el DNI de la persona que quiere editar:");
                int cont = 0;
                try {
                    String[] palabras;
                    sc = new Scanner(new File("Listado personas.txt"));


                    while (sc.hasNextLine()) {
                        int linea = 0;

                        String s = sc.nextLine();
                        linea++;
                        palabras = s.split(" ");
                        for (int i = 0; i < palabras.length; i++) {
                            if (option1.equalsIgnoreCase(palabras[i])) {
                                cont++;
                                int option2 = Integer.parseInt(JOptionPane.showInputDialog("1) Cambiar nombre.\n2) Cambiar apellidos.\n3) Cambiar DNI.\n4) Cambiar edad.\n5) Cambiar riesgo.\n6) Cambiar vacuna.\n7) Cambiar número de vacunas inyectadas."));
                                switch (option2) {

                                    case 1:
                                        String nuevo_nombre = (JOptionPane.showInputDialog("Nuevo nombre:"));
                                        palabras[1] = nuevo_nombre;
                                        FileWriter wrtp = null;
                                    {
                                        try {
                                            wrtp = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp.write(palabras[i] + " ");
                                            }
                                            wrtp.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;


                                    case 2:
                                        String nuevo_apellidos = (JOptionPane.showInputDialog("Nuevos apellidos:"));
                                        palabras[3] = nuevo_apellidos;
                                        FileWriter wrtp1 = null;
                                    {
                                        try {
                                            wrtp1 = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp1.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp1.write(palabras[i] + " ");
                                            }
                                            wrtp1.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;

                                    case 3:
                                        String nuevo_dni = (JOptionPane.showInputDialog("Nuevo DNI:"));
                                        palabras[5] = nuevo_dni;
                                        FileWriter wrtp2 = null;
                                    {
                                        try {
                                            wrtp2 = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp2.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp2.write(palabras[i] + " ");
                                            }
                                            wrtp2.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;
                                    case 4:
                                        String nuevo_edad = (JOptionPane.showInputDialog("Nueva edad:"));
                                        palabras[7] = nuevo_edad;
                                        FileWriter wrtp3 = null;
                                    {
                                        try {
                                            wrtp3 = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp3.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp3.write(palabras[i] + " ");
                                            }
                                            wrtp3.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;
                                    case 5:
                                        String nuevo_riesgo = (JOptionPane.showInputDialog("Nuevo riesgo:"));
                                        palabras[9] = nuevo_riesgo;
                                        FileWriter wrtp4 = null;
                                    {
                                        try {
                                            wrtp4 = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp4.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp4.write(palabras[i] + " ");
                                            }
                                            wrtp4.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;
                                    case 6:
                                        String nuevo_vac = (JOptionPane.showInputDialog("Nueva vacuna:"));
                                        palabras[11] = nuevo_vac;
                                        FileWriter wrtp5 = null;
                                    {
                                        try {
                                            wrtp5 = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp5.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp5.write(palabras[i] + " ");
                                            }
                                            wrtp5.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;
                                    case 7:
                                        String nuevo_num_vac_inyect = (JOptionPane.showInputDialog("Nuevo número de vacunas inyectadas:"));
                                        palabras[16] = nuevo_num_vac_inyect;
                                        FileWriter wrtp6 = null;
                                    {
                                        try {
                                            wrtp6 = new FileWriter(listper, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp6.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp6.write(palabras[i] + " ");
                                            }
                                            wrtp6.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;

                                }
                            }
                        }
                    }
                    sc.close();

                    if (cont == 0) {
                        metobx.mensaje6();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                break;


            case 2:
                String option3 = JOptionPane.showInputDialog("Escriba el nombre de la vacuna que quiere editar:");
                int cont1 = 0;
                try {
                    String[] palabras;

                    sc = new Scanner(new File("Listado vacunas.txt"));
                    while (sc.hasNextLine()) {
                        String s = sc.nextLine();
                        palabras = s.split(" ");

                        for (int i = 0; i < palabras.length; i++) {
                            if (option3.equalsIgnoreCase(palabras[i])) {
                                cont1++;
                                int option2 = Integer.parseInt(JOptionPane.showInputDialog("1) Cambiar nombre.\n2) Cambiar número de dosis disponibles.\n3) Cambiar número de dosis necesarias."));
                                switch (option2) {

                                    case 1:
                                        String nuevo_nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                                        palabras[1] = nuevo_nombre;
                                        FileWriter wrtp = null;
                                    {
                                        try {
                                            wrtp = new FileWriter(listvac, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp.write(palabras[i] + " ");
                                            }
                                            wrtp.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;

                                    case 2:
                                        String nuevo_dosis_disp = ((JOptionPane.showInputDialog("Nuevo número de dosis disponibles:")));
                                        palabras[6] = nuevo_dosis_disp;
                                        FileWriter wrtp1 = null;
                                    {
                                        try {
                                            wrtp1 = new FileWriter(listvac, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp1.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp1.write(palabras[i] + " ");
                                            }
                                            wrtp1.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }
                                    break;
                                    case 3:
                                        String nuevo_dosis_nec = (JOptionPane.showInputDialog("Nuevo número de dosis necesarias:"));
                                        palabras[11] = nuevo_dosis_nec;
                                        FileWriter wrtp2 = null;
                                    {
                                        try {
                                            wrtp2 = new FileWriter(listvac, true);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            String editado;
                                            wrtp2.write("\n");
                                            for (i = 0; i < palabras.length; i++) {
                                                wrtp2.write(palabras[i] + " ");
                                            }
                                            wrtp2.close();
                                            metobx.mensaje1();
                                            for (i = 0; i < palabras.length; i++) {

                                                System.out.print(palabras[i] + " ");
                                            }
                                            System.out.println("\n");
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (IndexOutOfBoundsException e) {
                                        }

                                    }

                                }

                            }
                        }
                    }
                    if (cont1 == 0) {
                        System.out.println("No hay vacunas en la lista con ese nombre.");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }

    }//Cierre del método


    public int getOpcionmenu() {
        return opcionmenu;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNum_vac_inyect(int num_vac_inyect) {
        this.num_vac_inyect = num_vac_inyect;
    }

    public void setVac_quedan(int vac_quedan) {
        this.vac_quedan = vac_quedan;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
//Cierre de la clase