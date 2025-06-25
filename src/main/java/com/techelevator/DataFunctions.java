package com.techelevator;

import com.techelevator.errors.InvalidData;

import java.util.List;

public interface DataFunctions {
    List<Item> generateItems() throws InvalidData;
}
