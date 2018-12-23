package com.company;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Parker Amundsen
 * @version 12/18/2018
 * @descrip This class will be used to work with the .txt files that will be used to store the questions and answers
 * that the notecard objects will be composed of.
 */
public class Manage {

    /**
     * @descrip This is the String pathname to the programFiles directory where all of the user determined data is stored.
     */
    private String pathName = new File("programFiles").getAbsolutePath();
    /**
     * @descrip This is the File object that encapsulates the programFiles directory where all of the user determined data is stored.
     */
    private File programFilesFolder = new File(pathName);
    /**
     * @descrip This is the String pathname to the working directory of the program where userSubmitted .txt files may be inputted to the program for later use.
     */
    private String workingDirectoryPathName = new File("").getAbsolutePath();


    /**
     * @descrip checks to see if the programFiles directory is created, if it is not, the directory is created.
     */
    public void checkForProgramFilesFolder() {
        if (this.programFilesFolder.exists()) {
            System.out.println("Loading Program Files");
        } else {
            this.programFilesFolder.mkdir();
            System.out.println("Welcome to NoteCards!");
        }
    }

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
     * @param pathname
     * @return String array of directories.
     */
    public static String[] listSections(String pathname) {
        File dir = new File(pathname);
        String[] filenames = dir.list();
        int dirCounter = 0;
        for (int i = 0; i < filenames.length; i++) {
            if (new File(pathname + "/" + filenames[i]).isDirectory()) {
                dirCounter++;
            }
        }
        String[] directories = new String[dirCounter];
        int index = 0;
        for (int i = 0; i < filenames.length; i++) {
            if (new File(pathname + "/" + filenames[i]).isDirectory()) {
                directories[index++] = filenames[i];
            }

        }
        return directories;
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
        if (arr.length > 0) {
            System.out.println("To select enter one of the numbers above.");
            while (true) {
                try {
                    int choice = reader.nextInt();
                    if (choice < arr.length && choice >= 0) {
                        return arr[choice];
                    } else {
                        System.out.println("Invalid input. Try again values must be between 0 and " + (arr.length - 1) + ".");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You must enter ONLY whole numbers please try again.");
                    reader.nextLine();
                }

            }
        }
        System.out.println("No files found.");
        return null;
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
     * @param directory
     * @param fileName
     * @param reader
     * @descrip moves The File associated with the fileName to the given directory.
     */
    public void addFile(File directory, String fileName, Scanner reader) {
        File fileToAdd = new File(this.workingDirectoryPathName + "/" + fileName);
        if (!directory.exists()) {
            System.out.println("Your destination folder does not exist, would you like to create a new one? (Enter\"YES/NO\" )");
            String response = reader.next();
            if (isTrue(response)) {
                makeDir(directory);
            } else {
                System.out.println("New directory was NOT made.");
                return;
            }
            fileToAdd.renameTo(new File(directory + "/" + fileName));
            System.out.println((new File(directory + "/" + fileName)));
        } else {
            fileToAdd.renameTo(new File(directory + "/" + fileName));
            System.out.println((new File(directory + "/" + fileName)));
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
            if (isTrue(override)) {
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
     * @param section
     * @descrip overloaded File param makeDir() is used to make a directory
     */
    public static void makeDir(File section) {
        if (section.mkdir()) {
            System.out.println("Section created!");
        } else {
            System.out.println("Something went wrong! Your section was not created please try again.");
        }
    }

    /**
     * @param arr
     * @param pathName
     * @throws FileNotFoundException
     */
    public static void writeToFile(String[] arr, String pathName) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(pathName, true);
        PrintWriter writer = new PrintWriter(fos);
        for (String x : arr) {
            writer.println(x);
        }
        writer.close();
    }

    /**
     * @param questions
     * @param answers
     * @return mergedAndTrimmedArray[]
     * @descrip Merges the question and answer arrays and trims empty elements from the end of the arrays.
     */
    public static String[] mergeAndTrimArrays(String[] questions, String[] answers) {
        int realElementCounter = 0;
        for (String x : questions) {
            if (x == null) {
                break;
            } else if (x.length() > 0 && x != null) {
                realElementCounter++;
            }
        }

        String[] mergedArray = new String[realElementCounter * 2];
        int mergedArrayIndex = 0;
        for (int i = 0; i < realElementCounter; i++) {
            mergedArray[mergedArrayIndex++] = questions[i];
            mergedArray[mergedArrayIndex++] = answers[i];
        }
        return mergedArray;
    }

    /**
     * @param reader
     */
    public void makeFile(Scanner reader) {
        //Establish arrays for collecting user provided questions and answers.
        String[] questions = new String[50];
        String[] answers = new String[50];

        //Collects the questions and answers from user, stores in questions[] and answers[].
        String response;
        int index = 0;
        do {
            System.out.println("What should the question (front) of the notecard say?");
            String q = reader.nextLine();
            System.out.println("What should the answer (back) of the notecard say?");
            String a = reader.nextLine();
            questions[index] = q;
            answers[index++] = a;
            System.out.println("Do you have more notecards to make?");
            response = reader.next();
            reader.nextLine();
        } while (isTrue(response));

        //Merges questions[] and answers[] to one array and trims the empty elements.
        String[] mergedArray = mergeAndTrimArrays(questions, answers);
        System.out.println("Would you like to add these notecards to an existing file?");
        response = reader.next();
        if (isTrue(response)) {
            System.out.println("Choose the section which has the file you would like to add to.");
            String chosenDir = chooseFile(listSections(pathName), reader);
            System.out.println("Now choose the file you would like to add to.");
            String chosenFilePathname = pathName + "/" + chosenDir + "/" + chooseFile(listTxtFiles(pathName + "/" + chosenDir), reader);
            try {
                writeToFile(mergedArray, chosenFilePathname);
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong.");
            }
        } else {
            System.out.println("What would you like to name the file?");
            reader.nextLine();
            response = reader.nextLine();
            String fileName = (response.endsWith(".txt")) ? response : response + ".txt";
            System.out.println("Would you like to add " + response + "to a section?");
            response = reader.next();
            if (!isTrue(response)) {
                try {
                    writeToFile(mergedArray, pathName + "/" + fileName);
                    System.out.println("File created in main directory, to use add it to a section");
                } catch (FileNotFoundException e) {
                    System.out.println("File unable to be created.");
                }
            }
            try {
                writeToFile(mergedArray, pathName + "/" + chooseFile(listSections(pathName), reader) + "/" + fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File unable to be created");
            }
        }
    }


}

