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
        System.out.println("1. Create a todo sheet");
        System.out.println("2. Read an existing todo sheet");
        System.out.println("3. Exit program");

    }

    public void handleCreateTodoSheet() {
        List<Assignment> listOfAssignments = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {

            Assignment assignment = new Assignment();
            
            try {
                assignment.setDueDate(generateDateFromString());
            } catch(ParseException e) {
                System.out.println("You entered the date in the incorrect format. Please start over");
                continue;
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

    public void handleReadTodoSheet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type in the name of the sheet you want to read: ");
        String name = sc.nextLine();
        name = name.trim();
        // Read the files into objects
        List<Assignment> currentAssignments = new ArrayList<>();
        try {
            currentAssignments = sheetReader.readAssignments(name);
        } catch(ParseException e) {
            System.out.println("Something went wrong when reading the sheet");
            return;
        }

        if (currentAssignments == null) {
            System.out.println("The list does not exist or nothing was found");
            return;
        }
        // Set assignmentRepository's array list and sort by due date
        assignmentRepository.setCurrentAssignments(currentAssignments);
        
        System.out.println("\n\n\n Here is a list of all your assignments for this specific sheet: ");
        // Display all the current todo assignments
        assignmentRepository.displayAllAssignments();

        int userChoice = -1;
        boolean userIsViewing = true;
        while (userIsViewing) {
            System.out.println(" 1. Show me all assignments \n 2. Filter by specific due date \n 3. Filter by class name \n 4. Exit Program (You can enter any OTHER number that 1, 2, and 3 \nSelect the number corresponding to the option: ");
            userChoice = sc.nextInt();
            sc.nextLine();
            switch(userChoice) {
                case 1:
                    assignmentRepository.displayAllAssignments();
                    break;
                case 2:
                    Date userDate = null;
                    try {
                        userDate = generateDateFromString();
                    } catch(ParseException e) {
                        System.out.println("Something went wrong during parsing");
                        userIsViewing = false;
                        continue;
                    }
                        
                    assignmentRepository.displayByDueDate(userDate);
                    break;

                case 3:
                    System.out.println("Enter the class name (Example like ITCS3112): ");
                    String userEntry = sc.nextLine();
                    assignmentRepository.displayByClassName(userEntry);
                    break;

                default:
                    userIsViewing = false;
                    break;
            }
        
        }
            
    }

    private Date generateDateFromString() throws ParseException {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter in the due date in mm-dd-yyyy format: ");
            String dueDateInput = sc.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            Date dueDate = formatter.parse(dueDateInput + " 00:00:00");
            return dueDate; 
    }
}
