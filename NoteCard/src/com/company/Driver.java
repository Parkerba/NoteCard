package com.company;

public class Driver {

    public static void main(String[] args) {
        NoteCard melon = new NoteCard("How old is Melon turning?", "20");

        System.out.println(melon.getQuestion());
        System.out.println(melon.getAnswer());


    }
}
