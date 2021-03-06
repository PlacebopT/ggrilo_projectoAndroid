package ggrilo.millionaireapp;

public class Question {
    public int id;
    public String questions;
    public Answer[] answers;


    int counter = 0;
    public void addAnswer(Answer answer){
        answers[counter] = answer;
        counter++;
    }

    public Question(int id, String questions)
    {
        this.id = id;
        this.questions = questions;
        this.answers = new Answer[4];
    }

    public static class Answer
    {
        String id;
        String question;
        boolean correct;

        public Answer(String id, String question, boolean correct){
            this.id = id;
            this.question = question;
            this.correct = correct;
        }
    }

    @Override
    public String toString() {
        String temp = "ID = " + id + " Question = " + questions + " Length = " + answers.length;

        return temp;
    }
}
