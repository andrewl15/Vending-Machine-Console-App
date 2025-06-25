package com.techelevator;

import com.techelevator.errors.InvalidData;
import com.techelevator.errors.InvalidInputData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile implements DataFunctions{
    private final String REGEX_DIVIDER = "\\|";
    private final String INPUT_FILE = "vendingmachine.csv";
    private final String DEFAULT_STOCK = "5";

    @Override
    public List<Item> generateItems() throws InvalidData {
        List<Item> output = new ArrayList<>();
        File optionData = new File(INPUT_FILE);
        try(Scanner fileReader = new Scanner(optionData)){
            while(fileReader.hasNext()){
                String line = fileReader.nextLine();
                String[] lineParts = line.split(REGEX_DIVIDER);
                Item item = new Item(lineParts[0],lineParts[1], Double.parseDouble(lineParts[2]), lineParts[3], DEFAULT_STOCK);
                output.add(item);
            }

        }catch(FileNotFoundException e){
            //TODO log error
            throw new InvalidData();
        }
        return output;
    }


}
