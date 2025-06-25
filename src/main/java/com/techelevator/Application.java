package com.techelevator;

import com.techelevator.errors.InvalidData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {



	public static void main(String[] args) throws InvalidData {

		UserInterface ui = new UserInterface();
		ui.displayMainMenu();
	}
}
