package com.studenttodogenerator.managers;

import com.studenttodogenerator.fileIO.SheetReader;
import com.studenttodogenerator.fileIO.SheetWriter;
import com.studenttodogenerator.model.Assignment;
import com.studenttodogenerator.repositories.AssignmentRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

/**
 * This is the class the user will interact with the most.
 * Delegates all incoming requests to other managers.
 */
public class EventManager {
    private SheetReader sheetReader;
    private SheetWriter sheetWriter;
    private AssignmentRepository assignmentRepository;

    public static EventManager eventManager;
    public static EventManager getInstance() {
        if (eventManager == null) {
            eventManager = new EventManager();
            eventManager.sheetReader = SheetReader.getInstance();
            eventManager.sheetWriter = SheetWriter.getInstance();
            eventManager.assignmentRepository = AssignmentRepository.getInstance();
        }

        return eventManager;
    }

    private EventManager() {

    }

    public void displayOptions() {

    }

    public void handleCreateTodoSheet() {
        List<Assignment> listOfAssignments = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {

            Assignment assignment = new Assignment();

            System.out.println("Enter in the due date in mm-dd-yyyy format: ");
            String dueDateInput = sc.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("MM-DD-yyyy HH:mm:ss");
            try {
                Date dueDate = formatter.parse(dueDateInput + " 00:00:00");
                assignment.setDueDate(dueDate);
            } catch (ParseException e) {
                return;
            }

            System.out.println("Enter in the assignment description: ");
            String description = sc.nextLine();
            assignment.setAssignmentDescription(description);

            System.out.println("Enter in the course number (ex: ITSC3112): ");
            String course = sc.nextLine().toUpperCase();
            assignment.setClassName(course);

            listOfAssignments.add(assignment);

            System.out.println("All done? Type N to add another assignment or Y to finish. ");
            String answer = sc.nextLine().toUpperCase();

            if (answer.equals("Y")) {
                break;
            }
        }

        
        System.out.println("Enter in the name of the file you want to create (DO NOT INCLUDE THE .txt EXTENSION): ");
        String fileName = sc.nextLine();

        Collections.sort(listOfAssignments);
        SheetWriter.writeSheet(listOfAssignments, fileName);

    }

    /**
     *
     */
    public void handleReadTodoSheet() {
        boolean userIsViewing = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Type in the name of the sheet you want to read: ");
        String name = sc.nextLine();
        name = name.trim();
        // Read the files into objects
        List<Assignment> currentAssignments = sheetReader.readAssignments(name);
        // Set assignmentRepository's array list and sort by due date
        assignmentRepository.setCurrentAssignments(currentAssignments);
        // Display all the current todo assignments
        assignmentRepository.displayAssignments();
        
    }

}
