package com.studenttodogenerator;

import com.studenttodogenerator.managers.EventManager;

public class Application {
    public static void main(String[] args) {
        EventManager eventManager = EventManager.getInstance();
        eventManager.handleCreateTodoSheet();

        System.out.println(System.getProperty("user.dir"));
    }
}
