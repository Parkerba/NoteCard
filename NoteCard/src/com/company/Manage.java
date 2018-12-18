package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Parker Amundsen
 * @version 12/15/2018
 * @descrip This class will be used to work with the .txt files that will be used to store the questions and answers
 * that the notecard objects will be composed of.
 */
public class Manage {

    /**
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
     * @param arr
     * @descrip simply prints a formatted two dimensional array parameter.
     */
    public static void printArray(String[] arr) {
        int i = 0;
        for (String file : arr) {
            System.out.println(i++ + ". " + file);
        }
    }

    /**
     * @param arr
     * @return String filename that is chosen through console input
     * @descrip Asks the user to choose from the available .txt files, returns the users selection.
     */
    public static String chooseFile(String[] arr, Scanner reader) {
        printArray(arr);
        System.out.println("Choose the file you would like to select by entering the number adjacent to the file listed above.");
        while (true) {
            int choice = reader.nextInt();
            if (choice < arr.length && choice >= 0) {
                return arr[choice];
            } else {
                System.out.println("Invalid input. Try again values must be between 0 and " + (arr.length - 1) + ".");
            }
        }

    }

    /**
     * @param input
     * @return boolean
     * @descrip returns true/false based on the string param, handles various options for true/false.
     */
    public static boolean isTrue(String input) {
        if (input.equalsIgnoreCase("True") || input.equalsIgnoreCase("yes") ||
                input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yeah")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @descrip moves The File associated with the fileName to the given directory.
     * @param directory
     * @param fileName
     * @param reader
     */
    public static void addFile(File directory, String fileName, Scanner reader) {
        if (!directory.exists()) {
            System.out.println("Your destination folder does not exist, would you like to create a new one? (Enter\"YES/NO\" )");
            String response = reader.next();
            if (isTrue(response)) {
                makeDir(directory);
            } else {
                return;
            }
            File fileToAdd = new File(fileName);
            String pathName = new File("").getAbsolutePath();
            fileToAdd.renameTo(new File(pathName + "/" + directory + "/" + fileName));
            System.out.println((new File(pathName + "/" + directory + "/" + fileName)));
            }


        }



    /**
     * #descrip makeDir() is used to make a directory
     */
    public static void makeDir() {
        System.out.println("What would you like to name the section?");
        Scanner reader = new Scanner(System.in);
        String sectionName = reader.next();
        File section = new File(sectionName);
        if (section.exists() && section.isDirectory()) {
            System.out.println("This section already exists. Would you like to override the Folder erasing all of its contents?");
            String override = reader.next();
            if (isTrue(override)){
                section.delete();
                if (section.mkdir()) {
                    System.out.println("Section created!");
                } else {
                    System.out.println("Something went wrong! Your section was not created please try again.");
                }

            }
        } else if (section.mkdir()) {
            System.out.println("Section created!");
        } else {
            System.out.println("Something went wrong! Your section was not created please try again.");
        }
    }

    /**
     * @escrip overloaded File param makeDir() is used to make a directory
     */
    public static void makeDir(File section) {
        if (section.mkdir()) {
            System.out.println("Section created!");
        } else {
            System.out.println("Something went wrong! Your section was not created please try again.");
        }
    }
}
