package com.company;

import java.io.File;
import java.util.Scanner;

/**
 * @author Parker Amundsen
 * @version 12/15/2018
 * @descrip This class will be used to work with the .txt files that will be used to store the questions and answers
 * that the notecard objects will be composed of.
 */
public class Manage {

    /**
     *
     * @param pathname
     * @return returns an array of the .txt files in parameter specified directory.
     */
    public static String[] listTxtFiles(String pathname) {
        File dir = new File(pathname);
        String[] filenames = dir.list();
        int txtCounter = 0;
        for (int i = 0; i < filenames.length; i++) {
            if (filenames[i].endsWith(".txt"))
                txtCounter++;
        }
        String[] txtFiles = new String[txtCounter];
        int index = 0;
        for (int i = 0; i < filenames.length; i++) {
            if (filenames[i].endsWith(".txt")) {
                txtFiles[index++] = filenames[i];
            }

        }
        return txtFiles;
    }

    /**
     * @descrip simply prints a two dimensional array parameter.
     * @param arr
     */
    public static void printArray(String[] arr) {
        int i = 0;
        for (String file : arr) {
            System.out.println(i++ + ". " + file);
        }
    }

    /**
     * @descrip Asks the user to choose from the available .txt files, returns the users selection.
     * @param arr
     * @return String filename that is chosen through console input
     */
    public static String chooseFile(String[] arr) {
        printArray(arr);
            System.out.println("Choose the file you would like to select by entering the number adjacent to the file listed above.");
        while (true) {
            Scanner reader = new Scanner(System.in);
            int choice = reader.nextInt();
            if (choice < arr.length && choice >= 0) {
                reader.close();
                return arr[choice];
            } else {
                System.out.println("Invalid input. Try again values must be between 0 and " + (arr.length - 1) + ".");
            }
        }

    }
}
