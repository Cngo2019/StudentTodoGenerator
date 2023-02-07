package com.studenttodogenerator.fileIO;

import com.studenttodogenerator.model.Assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class handles the writing of an excel file
 */
public class SheetWriter {
    public static SheetWriter SheetWriter;

    public static SheetWriter getInstance() {
        if (SheetWriter == null) {
            SheetWriter = new SheetWriter();
        }
        return SheetWriter;
    }

    private SheetWriter() {

    }

    public static void writeSheet(List<Assignment> assignmentList, String scheduleName) {
        try {
            System.out.println("Writing...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(scheduleName + ".txt"));
            for (Assignment assignment : assignmentList) {
                writer.write(assignment.getDueDate() + "\n" +
                             assignment.getClassName()  + "\n" +
                             assignment.getAssignmentDescription() + "\n\n");
            }
            writer.close();
        } catch(IOException e) {

        }

    }
}