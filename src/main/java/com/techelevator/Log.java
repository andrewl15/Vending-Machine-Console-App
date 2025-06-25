package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    public static void WriteLog(String reason) {
        String directoryName = "log";
        String fileName = "log.txt";
        File logDirectory = new File(directoryName);
        if(!logDirectory.exists()){
            logDirectory.mkdir();
        }
        String temp = directoryName + "\\" + fileName;
        try(FileOutputStream outputStream = new FileOutputStream(temp, true);
         PrintWriter pw = new PrintWriter(outputStream)){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            pw.println(now.format(formatter) + " " + reason);
        } catch(Exception e){

        }
    }
}
