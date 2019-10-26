package com.example.planningpoker.model;

import java.util.ArrayList;
import java.util.Random;

public class Session {
    private long key;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Question> questions = new ArrayList<>();

    public Session() {

    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
