package com.company;

public class NoteCard {
    /**
     * @descrip String data members question and answer encapsulate the two sides of a notecard.
     */
    private String question;
    private String answer;

    /**
     * @descrip NoteCard obj constructor, takes two String parameters.
     * @param question
     * @param answer
     */
    public NoteCard(String question, String answer){
        this.answer = answer;
        this.question = question;
    }

    /**
     * @descrip getter for String data member question.
     * @return question
     */
    public String getQuestion(){
        return this.question;
    }
    /**
     * @descrip getter for String data member answer.
     * @return answer
     */
    public String getAnswer(){
        return this.answer;
    }


    /**
     * @descrip toString() override.
     * @return returns formatted NoteCard obj as String.
     */
    @Override
    public String toString(){
       return "Q: " +  this.question + "\n" + "A: " + this.answer + "\n";
    }
}
