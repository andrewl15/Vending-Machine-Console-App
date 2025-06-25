package com.techelevator;

import com.techelevator.errors.InvalidData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInterfaceTest {

    private UserInterface userInterface;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp(){
        userInterface = new UserInterface();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testMainMenu_generate_items() throws InvalidData {
        List<Item> items = userInterface.dataFunctions.generateItems();
        assertFalse(items.isEmpty());
    }
    @Test
    void testMainMenu_generate_nothing_with_empty_list() throws InvalidData {
        List<Item> items = new ArrayList<>();
        assertTrue(items.isEmpty());
    }

    @Test
    void testDisplayMainMenu_UserChoosesExit() throws Exception {
        // Simulate user input (e.g., user enters "3")
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Create instance and run method
        UserInterface testObj = new UserInterface(); // Replace with your actual class
        testObj.displayMainMenu();

        // Restore original System.out and System.in
        System.setOut(originalOut);
        System.setIn(System.in);

        // Assert expected output
        String output = outContent.toString();
        assertTrue(output.contains("Thanks for using the machine!"));
    }

}
