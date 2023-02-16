package com.studenttodogenerator.repositories;

import com.studenttodogenerator.model.Assignment;

import java.util.Date;
import java.util.List;
import java.util.Collections;

/**
 * Place to store the assignments. Functionlity for the
 * filtering and display.
 */
public class AssignmentRepository {

    public static AssignmentRepository assignmentRepository;
    public static AssignmentRepository getInstance() {
        if (assignmentRepository == null) {
            assignmentRepository = new AssignmentRepository();
        }

        return assignmentRepository;
    }

    List<Assignment> currentAssignments;
    private AssignmentRepository() {

    }

    public void setCurrentAssignments(List<Assignment> currentAssignments) {
        this.currentAssignments = currentAssignments;
        sortByDueDate();
    }

    public List<Assignment> getCurrentAssignments(List<Assignment> currentAssignments) {
        return this.currentAssignments;
    }

    public void displayByClassName(String className) {
        System.out.println("\n\n\n================================= ASSIGNMENTS DUE ON  " + className + "=================================");
        for (Assignment assignment : currentAssignments) {
            if (assignment.getClassName().equals(className)) {
                System.out.println("DUE DATE: " + assignment.getDueDate() + " (" + assignment.getClassName() + ")" + "\n" + assignment.getAssignmentDescription() + "\n\n");
            }
        }
    }

    public void displayByDueDate(Date date) {
        System.out.println("\n\n\n================================= ASSIGNMENTS DUE ON  " + date.toString() + "=================================");
        for (Assignment assignment : currentAssignments) {
            if (assignment.getDueDate().equals(date)) {
                System.out.println("DUE DATE: " + assignment.getDueDate() + " (" + assignment.getClassName() + ")" + "\n" + assignment.getAssignmentDescription() + "\n\n");
            }
        }
    }


    public void printCurrentAssignments() {
        if (currentAssignments == null) {
            return;
        }

        for (Assignment assignment : currentAssignments) {
            System.out.println(assignment.toString() + "\n\n");
        }
    }


    public void displayAllAssignments() {
        System.out.println("================================= YOUR CURRENT ASSIGNMENTS ARE =================================");
        for (Assignment assignment : currentAssignments) {
            System.out.println("DUE DATE: " + assignment.getDueDate() + " (" + assignment.getClassName() + ")" + "\n" + assignment.getAssignmentDescription() + "\n\n");
        }
    }

    

    /**
     * Method should get called when we set currentAssignments.
     */
    private void sortByDueDate() {
        Collections.sort(currentAssignments);
    }
}
