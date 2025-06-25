package com.techelevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesReportTest {

    @Test
    public void creates_file_and_writes_to_file() throws Exception{
        //Arrange
        String directoryName = "salesReport";
        String filePath = directoryName + "\\SalesReport.txt";
        File SalesReportDirectory = new File(directoryName);

        Item item1 = new Item("A1", "Chips", 1.50, "Chip", "5");
        Item item2 = new Item("B1", "Cola", 1.25, "Drink", "SOLD OUT");
        List<Item> items = Arrays.asList(item1, item2);
        double totalRevenue = 10.75;

        if(!SalesReportDirectory.exists()){
            SalesReportDirectory.mkdir();
        }

        // Act
        SalesReport.WriteLog(items, totalRevenue);

        //Assert
        assertTrue(Files.exists(Paths.get(filePath)), "Sales report file should exist");

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        assertTrue(lines.contains("Chips | 5"), "Report should include Chips with stock 5");
        assertTrue(lines.contains("Cola | 0"), "Report should include Cola with stock 0 (SOLD OUT)");
        assertTrue(lines.stream().anyMatch(line -> line.contains("**TOTAL SALES** $10.75")),
                "Report should include total sales with correct amount");
    }

}
