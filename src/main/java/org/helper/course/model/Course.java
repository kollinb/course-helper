package org.helper.course.model;

import java.util.ArrayList;

public class Course {

    private String identifier, fullName, description;
    private boolean interested, fall, spring;
    private ArrayList<Goal> goals = new ArrayList<Goal>();

    public Course() {

    }

    public Course(String identifier, String fullName, String description, boolean interested, boolean fall, boolean spring, ArrayList<Goal> goals) {
        this.identifier = identifier;
        this.fullName = fullName;
        this.description = description;
        this.interested = interested;
        this.fall = fall;
        this.spring = spring;
        this.goals = goals;
    }

    public Course(String identifier, boolean interested, boolean fall, boolean spring) {
        this.identifier = identifier;
        this.interested = interested;
        this.fall = fall;
        this.spring = spring;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInterested(boolean interested) {
        this.interested = interested;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }

    public void setSpring(boolean spring) {
        this.spring = spring;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public boolean getInterested() {
        return interested;
    }

    public boolean getFall() {
        return fall;
    }

    public boolean getSpring() {
        return spring;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public boolean meetsGoal(Goal goal) {
        return goals.contains(goal);
    }

    @Override
    public String toString() {
        return identifier + "(" + fullName + ") " + (fall ? "Fall " : "") + (spring ? "Spring " : "") + (interested ? "true" : "false");
    }
}
