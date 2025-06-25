package com.techelevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.awt.PageAttributes.MediaType.A4;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private Item item;

    @Test
    public void test_item_constructor(){
        Item item = new Item("A4","cloud popcorn", 3.65, "Chip", "5");
        assertEquals("A4",item.getSlotLocation());
        assertEquals("cloud popcorn",item.getItemName());
        assertEquals(3.65,item.getPrice());
        assertEquals("Chip",item.getType());
        assertEquals("5",item.getStock());
    }
    @Test
    public void set_stock_is_equal_to_new_stock(){
        Item item = new Item("A4","cloud popcorn", 3.65, "Chip", "5");
        item.setStock("4");
        assertEquals("4", item.getStock());
    }
    @Test
    public void set_stock_is_equal_to_null_stock(){
        Item item = new Item("A4","cloud popcorn", 3.65, "Chip", "5");
        item.setStock("0");
        assertEquals("0", item.getStock());
    }
    @Test
    public void get_type_method_returns_correct_string(){
        Item item  = new Item("A4","Cloud popcorn", 3.65, "Chip", "5");
        assertEquals("Crunch Crunch, Yum!",item.getTypeMessage());
    }
    @Test
    public void get_type_method_returns_correct_string_for_chip(){
        Item item  = new Item("A4","Cloud popcorn", 3.65, "Chip", "5");
        assertEquals("Crunch Crunch, Yum!",item.getTypeMessage());
    }
    @Test
    public void get_type_method_returns_correct_string_for_candy(){
        Item item  = new Item("B1","Moonpie", 1.80, "Candy", "5");
        assertEquals("Munch Munch, Yum!",item.getTypeMessage());
    }
    @Test
    public void get_type_method_returns_correct_string_for_drink(){
        Item drink  = new Item("C1","Cola", 1.25, "Drink", "5");
        assertEquals("Glug Glug, Yum!",drink.getTypeMessage());
    }
    @Test
    public void get_type_method_returns_correct_string_for_gum(){
        Item item  = new Item("D4","Triplemint", 0.75, "Gum", "5");
        assertEquals("Chew Chew, Yum!",item.getTypeMessage());
    }

}
