package com.company;

import java.io.File;
import java.util.Scanner;

public class Manage {
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

    public static void printArray(String[] arr) {
        int i = 0;
        for (String file : arr) {
            System.out.println(i++ + ". " + file);
        }
    }

    public static String chooseFile(String[] arr) {
        printArray(arr);
        while (true) {
            System.out.println("Choose the file you would like to select by entering the number adjacent to the file listed above.");
            Scanner reader = new Scanner(System.in);
            int choice = reader.nextInt();
            if (choice < arr.length && choice >= 0) {
                reader.close();
                return arr[choice];
            } else {
                System.out.println("Invalid input try again values but be between 0 and " + (arr.length - 1) + ".");
            }
        }

    }
}
