package com.company;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.File;


/**
 * @author Parker Amundsen
 * @version 12/20/2018
 * @descrip Class contains methods to draw data from the files, and allows the data to be used for practical purposes.
 */
public class Program extends Manage {
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

    public void execute() throws Exception {
        Scanner keyboard = new Scanner(System.in);
        String makeSelection = "Enter the number adjacent to the Actions listed above.";
        String[] home = {"Review NoteCards", "NoteCard Manager", "How to/About", "Exit NoteCards"};
        String[] noteCardManager = {"Create a Section", "Create NoteCards", "Move a NoteCard file to a Section", "Back to Home"};
        Boolean keepRunning = true;
        while (keepRunning) {
            String homeChoice = chooseFile(home, keyboard);
            switch (homeChoice) {

                case "Review NoteCards":
                    String chosenSectionPathname = this.pathName + "/" +chooseFile(listSections(this.pathName),keyboard);
                    String chosenFilePathname = chosenSectionPathname + "/" + chooseFile(listTxtFiles(chosenSectionPathname),keyboard);
                    FileInputStream lineCounter = new FileInputStream(chosenFilePathname);
                    FileInputStream noteCardReader = new FileInputStream(chosenFilePathname);
                    NoteCard[] noteCards = makeNoteCards(lineCounter,noteCardReader);
                    run(noteCards,noteCards);

                    break;

                case "NoteCard Manager":
                    String managerChoice = chooseFile(noteCardManager,keyboard);
                    switch (managerChoice) {
                        case "Create a Section":
                            System.out.println("What would you like to name the section?");
                            keyboard.nextLine();
                            String sectionName = keyboard.nextLine();
                            String sectionPathName = this.pathName + "/" + sectionName;
                            makeDir(new File(sectionPathName));
                            break;

                        case "Create NoteCards":
                            keyboard.nextLine();
                            makeFile(keyboard);
                            break;

                        case "Move a NoteCard file to a Section":
                            String chosenFile = chooseFile(listTxtFiles(workingDirectoryPathName),keyboard);
                            String chosenDirPathname = pathName + "/" + chooseFile(listSections(pathName),keyboard);
                            addFile(new File(chosenDirPathname), chosenFile, keyboard);
                            break;

                        case "Back to Home":
                            break;
                    }
                    break;

                case "How to/About":
                    howToMakeNoteCards();
                    break;

                case "Exit NoteCards":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

            }

        }
    }

    /**
     * @throws Exception
     * @descrip Welcome() prints a welcome message to the user on startup.
     */
    public void welcome() throws Exception {
        //TODO: CREATE WELCOME DIALOGUE
        System.out.println("CREATE WELCOME DIALOGUE");
        resumeOnEnter();
    }

    /**
     * @throws Exception
     * @descrip Prints an brief explanation of how to add notecards to the program.
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
        //TODO: add dialogue for creating notecards within the program.
    }

    /**
     * @throws Exception
     * @descrip Pauses the program until the user pressed the "enter" key.
     */
    public static void resumeOnEnter() throws Exception {
        System.in.read();
    }

    public static boolean isValid(int input, int max) {
        if (input >= 0 && input < max) {
            return true;
        } return false;
    }

    /**
     * @param fis  (used for counting number of lines in the file)
     * @param fis2 (used for reading the file and creating NoteCard[])
     * @return noteCardSet[] array
     * @descrip
     */
    public NoteCard[] makeNoteCards(FileInputStream fis, FileInputStream fis2) {
        NoteCard[] noteCardSet;
        //Scanner object reader is used to count the number of lines in the file.
        Scanner reader = new Scanner(fis);
        //Scanner object is used to reader the questions and answers for the return NoteCard[] from the file.
        Scanner reader2 = new Scanner(fis2);
        //counts lines in the file
        int lineCounter = 0;
        while (reader.hasNextLine()) {
            lineCounter++;
            reader.nextLine();
        }
        //Establishing the size noteCardSet[] using from the number of question and answer sets.
        noteCardSet = new NoteCard[lineCounter / 2];
        //Creating the NoteCard objects and putting them in a NoteCard array.
        for (int i = 0; i < lineCounter / 2; i++) {
            String question = reader2.nextLine();
            String answer = reader2.nextLine();
            noteCardSet[i] = new NoteCard(question, answer);
        }
        return noteCardSet;
    }

    /**
     * @param noteCards
     * @param original  This is the original NoteCard[] to be carried through recursion as a passed param.
     * @throws Exception
     */
    public void run(NoteCard[] noteCards, NoteCard[] original) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        int incorrectCounter = 0; //To be used when creating new NoteCard[] filled with NoteCards that were answered incorrectly.

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
                }
                run(incorrect, original);
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

    /**
     * @param noteCards
     * @param numIncorrect
     * @return Filters NoteCard[] and returns array containing NoteCards with boolean datamember correct == false.
     */
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

