package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalesReport {

    public static void WriteLog(List<Item> sale, double revenue) {
        String directoryName = "salesReport";
        String fileName = "SalesReport.txt";
        File logDirectory = new File(directoryName);
        if(!logDirectory.exists()){
            logDirectory.mkdir();
        }
        String temp = directoryName + "\\" + fileName;
        try(FileOutputStream outputStream = new FileOutputStream(temp, true);
            PrintWriter pw = new PrintWriter(outputStream)){
            for(Item item : sale){
                pw.println(item.getItemName() + " | " + (item.getStock().equals("SOLD OUT")? 0 : item.getStock()));
            }
            pw.println("**TOTAL SALES** $" + revenue);
        } catch(Exception e){

        }
    }

}
