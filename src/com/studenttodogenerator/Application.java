package com.studenttodogenerator;

import com.studenttodogenerator.managers.EventManager;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EventManager eventManager = EventManager.getInstance();


        eventManager.displayOptions();


        boolean userIsUsing = true;
        while (userIsUsing) {
            System.out.println("Select which option you want by typing the number in the terminal and pressing enter: ");
            int userOption = sc.nextInt();
            sc.nextLine();
            switch (userOption) {
                case 1:
                    eventManager.handleCreateTodoSheet();
                    break;
                case 2:
                    // Read from the sheet
                    eventManager.handleReadTodoSheet();
                    break;
                case 3:
                    // Exit the program
                    userIsUsing = false;
                    break;
            }
        }

        System.out.println(System.getProperty("user.dir"));
    }
}
