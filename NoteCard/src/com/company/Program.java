package com.company;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.File;

/**
 * @author Parker Amundsen
 * @version 12/20/2018
 */
public class Program {
    public void welcome() throws Exception {
        System.out.println("Notecards is an application that makes studying with notecards much easier!");
        resumeOnEnter();
    }

    /**
     * @throws Exception
     */
    public void howToMakeNoteCards() throws Exception {
        System.out.println("With the Notecards app you can add Notecards two ways.\n1. Drag and Drop. \n2. Create in Notecards.");
        resumeOnEnter();
        System.out.println("Drag and drop:");
        System.out.println("- Drag and Drop formatted .txt files into the NoteCard Folder.");
        System.out.print("   - The .txt files should be formatted as:");
        resumeOnEnter();
        System.out.println("           Question\n" +
                "           Answer\n" +
                "           Question\n" +
                "           Answer\n" +
                "   - Starting with the first Question and alternating between Question and Answer on new lines.");
        //TODO add dialogue for creating notecards within the program.
    }

    public static void resumeOnEnter() throws Exception {
        System.in.read();
    }

    /**
     * @param fis
     * @param fis2
     * @return
     */
    public NoteCard[] makeNoteCards(FileInputStream fis, FileInputStream fis2) {
        NoteCard[] noteCardSet;
        Scanner reader = new Scanner(fis);
        Scanner reader2 = new Scanner(fis2);
        int lineCounter = 0;
        while (reader.hasNextLine()) {
            lineCounter++;
            reader.nextLine();
        }
        noteCardSet = new NoteCard[lineCounter / 2];
        for (int i = 0; i < lineCounter / 2; i++) {
            String question = reader2.nextLine();
            String answer = reader2.nextLine();
            noteCardSet[i] = new NoteCard(question, answer);
        }
        return noteCardSet;
    }

    public void run(NoteCard[] noteCards, NoteCard[] original) throws Exception {
        NoteCard[] originalNotecard = original;
        Scanner keyboard = new Scanner(System.in);
        int incorrectCounter = 0;
        for (int i = 0; i < noteCards.length; i++) {
            System.out.println(noteCards[i].getQuestion() + "\nPress enter to reveal the answer.");
            resumeOnEnter();
            System.out.println(noteCards[i].getAnswer());
            System.out.println("Did you get the answer correct?");
            String response = keyboard.nextLine();
            if (!Manage.isTrue(response)) {
                noteCards[i].setCorrect(false);
                incorrectCounter++;
            } else {
                noteCards[i].setCorrect(true);
            }
        }
        int nextMove;

        do {
            System.out.println("Would you like to:\n 1. review the notecards you missed \n2.Review all the notecards you just covered. \n3.Review all the notecards in this file. \n4.Exit");
            nextMove = keyboard.nextInt();
            if (nextMove > 4 || nextMove < 1) {
                System.out.println("Please enter a number corresponding to the next action listed above. (between 1 and 4)\n Press enter to try again.");
                resumeOnEnter();
            }
        } while (nextMove > 4 || nextMove < 1);

        switch (nextMove) {
            case 1:
                NoteCard[] incorrect = incorrect(noteCards, incorrectCounter);
                if (incorrect.length == 0) {
                    System.out.println("You got them all correct!");
                    break;
                } run(incorrect, original);
                break;

            case 2:
                run(noteCards, original);
                break;

            case 3:
                run(original, original);
                break;

            case 4:
                return;
        }
    }

    public NoteCard[] incorrect(NoteCard[] noteCards, int numIncorrect) {
        NoteCard[] incorrectArray = new NoteCard[numIncorrect];
        int incorrectArrayIndex = 0;
        for (NoteCard x : noteCards) {
            if (!x.isCorrect()) {
                incorrectArray[incorrectArrayIndex++] = x;
            }
        }
        return incorrectArray;

    }
}

