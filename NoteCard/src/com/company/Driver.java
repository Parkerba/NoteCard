package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        NoteCard melon = new NoteCard("How old is Melon turning?", "20");

        System.out.println(melon.getQuestion());
        System.out.println(melon.getAnswer());

        String file = Manage.chooseFile(Manage.listTxtFiles("/Users/parkeramundsen/Desktop/NoteCard/NoteCard"), reader);
        File directoryTest = new File("parker");
        Manage.addFile(directoryTest,file,reader);
        reader.close();


    }
}
