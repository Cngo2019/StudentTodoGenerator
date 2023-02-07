package com.studenttodogenerator.model;

import java.util.Date;

/**
 * Model for an assignment or todo list
 */
public class Assignment {
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

    public void setDueDate(String dueDate) {
        //implement here
        return;
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

}
