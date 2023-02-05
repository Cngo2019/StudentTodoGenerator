package com.studenttodogenerator.fileIO;

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
}
