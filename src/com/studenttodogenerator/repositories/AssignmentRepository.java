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
        sortByDueDate();
        this.currentAssignments = currentAssignments;
    }

    public List<Assignment> getCurrentAssignments(List<Assignment> currentAssignments) {
        return this.currentAssignments;
    }

    public List<Assignment> filterByClassName(String className) {
        return null;
    }

    public List<Assignment> filterByDueDate(Date date) {
        return null;
    }

    public List<Assignment> filterByPriority(Date date) {
        return null;
    }

    public void printCurrentAssignments() {
        if (currentAssignments == null) {
            return;
        }

        for (Assignment assignment : currentAssignments) {
            System.out.println(assignment.toString() + "\n\n");
        }
    }


    public void displayAssignments() {
        System.out.println("================================= YOUR CURRENT ASSIGNMENTS ARE =================================");
        for (Assignment assignment : currentAssignments) {
            System.out.println(assignment.getDueDate() + " (" + assignment.getClassName() + ")" + "\n" + assignment.getAssignmentDescription() + "\n\n");
        }
    }

    

    /**
     * Method should get called when we set currentAssignments.
     */
    private void sortByDueDate() {
        Collections.sort(currentAssignments);
    }
}
