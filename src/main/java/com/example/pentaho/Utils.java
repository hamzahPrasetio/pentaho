package com.example.pentaho;

import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

@NoArgsConstructor
public class Utils {

    public void listFilesInFolder(File folder, Month month, int year) {
        for (File fileInFolder : folder.listFiles()) {
            if (fileInFolder.isDirectory()) {
                boolean correctMonth = isFolderOfMonth(fileInFolder.getName(), month.getValue());
                boolean correctYear = isFolderOfYear(fileInFolder.getName(), year);
                if (correctMonth && correctYear) listFilesInFolder(fileInFolder, month, year);
            } else {
                System.out.println(fileInFolder.getName());
            }
        }
    }

    public void listFilesInFolder(File folder, Month month) {
        for (File fileInFolder : folder.listFiles()) {
            if (fileInFolder.isDirectory()) {
                boolean correctMonth = isFolderOfMonth(fileInFolder.getName(), month.getValue());
                if (correctMonth) listFilesInFolder(fileInFolder, month);
            } else {
                System.out.println(fileInFolder.getName());
            }
        }
    }

    public void listFilesInFolder(File folder, int year) {
        for (File fileInFolder : folder.listFiles()) {
            if (fileInFolder.isDirectory()) {
                boolean correctYear = isFolderOfYear(fileInFolder.getName(), year);
                if (correctYear) listFilesInFolder(fileInFolder, year);
            } else {
                System.out.println(fileInFolder.getName());
            }
        }
    }

    public void listFilesInFolder(File folder) {
        for (File fileInFolder : folder.listFiles()) {
            if (fileInFolder.isDirectory()) {
                listFilesInFolder(fileInFolder);
            } else {
                System.out.println(fileInFolder.getName());
            }
        }
    }

    private boolean isFolderOfMonth(String foldername, int month) {
        if (foldername.length() != 8) return false;
        if (foldernameContainMonth(foldername, month)) return true;
        return false;
    }

    private boolean isFolderOfYear(String foldername, int year) {
        if (foldername.length() != 8) return false;
        if (foldernameContainYear(foldername, year)) return true;
        return false;
    }

    private boolean foldernameContainMonth(String foldername, int searchedValue) {
        if (foldername.substring(4,6).equalsIgnoreCase(String.format("%02d",searchedValue))) return true;
        return false;
    }

    private boolean foldernameContainYear(String foldername, int searchedValue) {
        if (foldername.substring(0,4).equalsIgnoreCase(String.format("%04d",searchedValue))) return true;
        return false;
    }

    // append folder path to filename
    public String constructCompletePath(String filename) {
        LocalDate currdate = LocalDate.now();
        return "testing/" + String.format("%04d%02d%02d", currdate.getYear(), currdate.getMonthValue(), currdate.getDayOfMonth()) + "/" + filename + ".txt";
    }
}
