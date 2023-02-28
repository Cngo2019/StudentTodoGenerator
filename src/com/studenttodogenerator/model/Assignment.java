package com.studenttodogenerator.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Model for an assignment or todo list
 */
public class Assignment implements Comparable<Assignment> {
    private String assignmentDescription;
    private Date dueDate;
    private String className;

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDueDate(String dueDateString) throws ParseException{
        //implement here
        Date newDate = null;
        newDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(dueDateString);
        this.dueDate = newDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String toString() {
        return dueDate + " - " + className + " \n" + assignmentDescription;
    }

    @Override
    public int compareTo(Assignment assignment) {
        return this.getDueDate().compareTo(assignment.getDueDate());
    }

}
