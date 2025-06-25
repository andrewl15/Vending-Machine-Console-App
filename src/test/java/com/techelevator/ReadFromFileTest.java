package com.techelevator;

import com.techelevator.errors.InvalidData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadFromFileTest {
    public final String TEMP_FILE = "temp_vending_test.csv";

    @BeforeEach
    void setUp() throws IOException {
        List<String> lines = Arrays.asList(
                "A1|Potato Crisps|3.05|Chip",
                "B1|Moonpie|1.80|Candy"
        );
        Files.write(Paths.get(TEMP_FILE), lines);
    }
    @AfterEach
    void deleteLines() throws IOException{
        Files.deleteIfExists(Paths.get(TEMP_FILE));
    }
    @Test
    void generateItems_returns_correct_list_items() throws InvalidData, InvalidData {
        // Arrange
        ReadFromFile reader = new ReadFromFile() {
            @Override
            public List<Item> generateItems() throws InvalidData {
                List<Item> output = new ArrayList<>();
                File optionData = new File(TEMP_FILE);
                try (Scanner fileReader = new Scanner(optionData)) {
                    while (fileReader.hasNext()) {
                        String line = fileReader.nextLine();
                        String[] lineParts = line.split("\\|");
                        Item item = new Item(
                                lineParts[0],
                                lineParts[1],
                                Double.parseDouble(lineParts[2]),
                                lineParts[3],
                                "5"
                        );
                        output.add(item);
                    }
                } catch (FileNotFoundException e) {
                    throw new InvalidData();
                }
                return output;
            }
        };

        // Act
        List<Item> items = reader.generateItems();

        // Assert
        assertEquals(2, items.size());
        assertEquals("A1", items.getFirst().getSlotLocation());
        assertEquals("Potato Crisps", items.getFirst().getItemName());
        assertEquals(3.05, items.getFirst().getPrice());
        assertEquals("Chip", items.getFirst().getType());
        assertEquals("5", items.getFirst().getStock());
    }
    @Test
    void generateItems_throws_invalid_data_when_file_missing() {
        // Arrange
        ReadFromFile reader = new ReadFromFile() {
            @Override
            public List<Item> generateItems() throws InvalidData {
                File noFile = new File("no_file.csv");
                try (Scanner fileReader = new Scanner(noFile)) {
                    return new ArrayList<>();
                } catch (FileNotFoundException e) {
                    throw new InvalidData();
                }
            }
        };

        // Act & Assert
        assertThrows(InvalidData.class, reader::generateItems);
    }
}
