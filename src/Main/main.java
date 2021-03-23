package Main;


import java.io.*;
import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        File listper = new File("Listado personas.txt");


        Metodos obx = new Metodos();
        do {
            obx.menu();
        }
        while (obx.getOpcionmenu() != 7);


    }

}