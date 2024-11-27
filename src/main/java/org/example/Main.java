package org.example;

import manager.StudentManager;
import shellcontroller.Shell;

public class Main {

    public static Shell shell;
    public static boolean finito = false;

    public static void main(String[] args) {

        // prendere percorso relativo progetto e appendere nome file.
//        String currentDir = System.getProperty("user.dir");
//        StringBuilder sb = new StringBuilder();
//        sb.append(currentDir).append("/file_Studente.txt");

        do {

            shell = new Shell();
            shell.StartPoint();

        } while (!finito);


    }
}