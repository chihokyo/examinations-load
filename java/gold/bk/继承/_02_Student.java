package com.gold.继承;

public class _02_Student extends _02_Person {

    String subject;
    int score = 1;

    public _02_Student(String subject) {
        this.subject = subject;
    }

    public void study() {
        System.out.println("study " + this.subject);
    }

    public void showScore() {
        System.out.println("score is " + score); // score is 1
        System.out.println("this score is " + this.score); // this score is 1
        System.out.println("super score is " + super.score); // super score is 99
    }
}
