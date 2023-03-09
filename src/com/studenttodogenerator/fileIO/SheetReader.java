package com.studenttodogenerator.fileIO;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import com.studenttodogenerator.model.Assignment;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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

    public List<Assignment> readAssignments(String name) throws ParseException {

        List<String> stringList = new ArrayList<>();
        BufferedReader bufferReader = null;
        try {
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
            return null;
        }
        


        return turnIntoAssignments(stringList);
    }

    private List<Assignment> turnIntoAssignments(List<String> stringList) throws ParseException {
        List<Assignment> currentAssignments = new ArrayList<>();
        
        int counter = 0;
        String currentAssignmentString = "";
        for (String s : stringList) {
            currentAssignmentString = currentAssignmentString + s + ",";
            counter++;

            // Every 3rd iteration go ahead and create the new assignment
            if (counter == 3) {
                String[] splittedAssignment = currentAssignmentString.split(",");

                Assignment newAssignment = new Assignment();
                newAssignment.setDueDate(splittedAssignment[0]);
                newAssignment.setClassName(splittedAssignment[1]);
                newAssignment.setAssignmentDescription(splittedAssignment[2]);
                
                counter = 0;
                currentAssignmentString = "";
                currentAssignments.add(newAssignment);
            }
        }

        return currentAssignments;
    }
}
