package com.studenttodogenerator.fileIO;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import com.studenttodogenerator.model.Assignment;
import java.io.FileReader;
import java.io.IOException;
/**
 * Class handles the reading of an excel file
 */
public class SheetReader {
    public static SheetReader SheetReader;

    public static SheetReader getInstance() {
        if (SheetReader == null) {
            SheetReader = new SheetReader();
        }
        return SheetReader;
    }

    private SheetReader() {

    }

    public List<Assignment> readAssignments(String name) {

        List<String> stringList = new ArrayList<>();
        BufferedReader bufferReader = null;
        try {
            System.out.println("Testing");
            bufferReader = new BufferedReader(new FileReader(name + ".txt"));
            String currentLine = "";
            while((currentLine = bufferReader.readLine()) != null) {
                currentLine = currentLine.trim();
                if (currentLine.equals("")) {
                    continue;
                }
                stringList.add(currentLine.trim());
            }
            bufferReader.close();
        } catch (IOException e) {
            System.out.println("SOMETHING WENT WRONG!");
            return null;
        }
        


        System.out.println("CURRENT LIST IS " + stringList);
        return turnIntoAssignments(stringList);
    }

    private List<Assignment> turnIntoAssignments(List<String> stringList) {
        List<Assignment> currentAssignments = new ArrayList<>();
        
        int counter = 0;
        String currentAssignmentString = "";
        for (String s : stringList) {
            currentAssignmentString = currentAssignmentString + "," + s;
            counter++;

            if (counter == 3) {
                String[] splittedAssignment = currentAssignmentString.split(",");

                Assignment newAssignment = new Assignment();
                newAssignment.setDueDate(splittedAssignment[0]);
                newAssignment.setClassName(splittedAssignment[1]);
                newAssignment.setAssignmentDescription(splittedAssignment[2]);
                
                counter = 0;
                currentAssignmentString = "";
            }
        }
    }
}
