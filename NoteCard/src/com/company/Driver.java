package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
        Manage program = new Manage();
        program.checkForProgramFilesFolder();
        Scanner reader = new Scanner(System.in);
        String programFilesPathName = new File("programFiles").getAbsolutePath();
        String workingDirectoryPathName = new File("").getAbsolutePath();

        File programFilesFolder = new File(programFilesPathName);

        String file = Manage.chooseFile(Manage.listTxtFiles(workingDirectoryPathName), reader);
        //String file1 = Manage.chooseFile(Manage.listSections(pathName), reader);
        System.out.println("What directory would you like to add the file to?");
        reader.nextLine();
        String sectionName = reader.nextLine();
        File directoryTest = new File(programFilesPathName + "/" + sectionName);
        program.addFile(directoryTest, file, reader);
        reader.close();


    }
}
