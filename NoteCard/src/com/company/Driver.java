package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        String pathName = new File("programFiles").getAbsolutePath();
        File programFilesFolder = new File(pathName);

        //String file = Manage.chooseFile(Manage.listTxtFiles(pathName), reader);
        String file1 = Manage.chooseFile(Manage.listSections(pathName), reader);

        System.out.println(file1);
        File directoryTest = new File("programFiles");
        Manage.addFile(directoryTest, file1, reader);
        reader.close();


    }
}
