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

    public static void writeExcelSheet(List<Assignment> assignmentList, String scheduleName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(scheduleName + ".txt"));
            for (Assignment assignment : assignmentList) {
                writer.write("Due Date: " + assignment.getDueDate() + "\n" +
                             "Class: " + assignment.getClassName()  + "\n" +
                             "Description: " + assignment.getAssignmentDescription());
            }
            writer.close();
        } catch(IOException e) {

        }

    }
}
