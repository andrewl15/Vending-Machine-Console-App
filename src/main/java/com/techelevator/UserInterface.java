package com.techelevator;

import com.techelevator.errors.InvalidData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner keyboard = new Scanner(System.in);
    private List<Item> optionsList = new ArrayList<>();
    DataFunctions dataFunctions = new ReadFromFile();
    Money balance = new Money();
    MachineEarnings earnings = new MachineEarnings();



    public void displayMainMenu() throws InvalidData {
        if(optionsList == null || optionsList.isEmpty()) {
            optionsList = dataFunctions.generateItems();
        }
        Scanner keyboard;
        String userChoice = "7";

        try {
            // get the answer from the user
            do {
                System.out.println("(1) Display Vending Machine Items");
                System.out.println("(2) Purchase");
                System.out.println("(3) Exit");
                System.out.print("Please choose: ");
                keyboard = new Scanner(System.in);
                userChoice = keyboard.nextLine();
                System.out.println();
            } while (Integer.parseInt(userChoice) < 1 || Integer.parseInt(userChoice) > 4);
        } catch (Exception e) {
            System.out.println("That is not a valid answer.");
            System.out.println();
            displayMainMenu();
            // TODO log the error
        }

        if(userChoice.equals("1")){
            displayVendingItems();
        }else if(userChoice.equals("2")){
            displayPurchaseScreen();
        } else if(userChoice.equals("4")){
            SalesReport.WriteLog(optionsList, earnings.getRevenue());
            displayMainMenu();
        } else if (userChoice.equals("3")) {
            System.out.println("Thanks for using the machine!");
            //End Program
        }
        //TODO Hidden list
    }
    public void displayVendingItems() throws InvalidData {
        for (Item item : optionsList) {
            System.out.println(item.getSlotLocation() + ") " + "Item Name: " + item.getItemName() + " Price: " + item.getPrice() + " Stock: " + item.getStock());
        }
        System.out.println("\n");
        displayMainMenu();
    }
    public void displayPurchaseScreen() throws InvalidData {
        Scanner keyboard;
        String userChoice = "7";
        // get the answer from the user

        try{
            do{
                System.out.println("Current money provided: " + balance.getCurrentMoney());
                System.out.println();
                System.out.println("(1) Feed Money");
                System.out.println("(2) Select Product");
                System.out.println("(3) Finish Transaction");
                System.out.print("Please choose: ");
                keyboard = new Scanner(System.in);
                userChoice = keyboard.nextLine();
                System.out.println();
            }while(Integer.parseInt(userChoice) < 0 || Integer.parseInt(userChoice) > 3);
        } catch (Exception e){
            System.out.println("That is not a valid answer.");
            System.out.println();
            // TODO log the error
            displayPurchaseScreen();
        }
        if(userChoice.equals("1")){
           feedMoneyScreen();
        }else if(userChoice.equals("2")){
            selectProduct();
        } else if(userChoice.equals("3")){
            finishTransaction();
        }
    }
    public void feedMoneyScreen() throws InvalidData {
        Scanner keyboard;
        String userChoice = "0";
        try{
            do{
                // get the answer from the user
                System.out.print("Please enter the amount of money to deposit: ");
                keyboard = new Scanner(System.in);
                userChoice = keyboard.nextLine();
                System.out.println();
            }while(Integer.parseInt(userChoice) <= 0);
        }catch (Exception e){
            System.out.println("Please enter a whole number");
            System.out.println();
            feedMoneyScreen();
        }
        balance.depositMoney(Double.parseDouble(userChoice));
        Log.WriteLog("FEED MONEY: $" + userChoice + ".00 $" + balance.getCurrentMoney() + "0");
        displayPurchaseScreen();
    }
    public void exitApplication(){
        System.out.println("Have a nice day!");
    }

    public void selectProduct() throws InvalidData {
        Scanner keyboard;
        String userChoice = "";
        int tempIndex = 0;
        boolean isValidSlot = false;
        do{
            try{
                for (Item item : optionsList) {
                    System.out.println(item.getSlotLocation() + ") " + "Item Name: " + item.getItemName() + " Price: " + item.getPrice() + " Stock: " + item.getStock());
                }
                System.out.println();
                System.out.print("Please enter the slot you would like the purchase: ");
                keyboard = new Scanner(System.in);
                userChoice = keyboard.nextLine();
                System.out.println();
                for(int i = 0; i < optionsList.size(); i++){
                    if(optionsList.get(i).getSlotLocation().equalsIgnoreCase(userChoice)){
                        isValidSlot = true;
                        tempIndex = i;
                        break;
                    }
                }
            } catch (Exception e){
                System.out.println("That is not a valid answer.");
                // TODO log the error
            }
        }while(!isValidSlot);
        earnings.addRevenue(optionsList.get(tempIndex).getPrice());
        try{
            if(balance.getCurrentMoney() - optionsList.get(tempIndex).getPrice() >= 0){
                balance.subtractMoney(optionsList.get(tempIndex).getPrice());
                optionsList.get(tempIndex).setStock(String.valueOf(Integer.parseInt(optionsList.get(tempIndex).getStock()) - 1));
                if (Integer.parseInt(optionsList.get(tempIndex).getStock()) == 0) {
                    optionsList.get(tempIndex).setStock("SOLD OUT");
                }
                System.out.println(optionsList.get(tempIndex).getTypeMessage());
                Log.WriteLog(optionsList.get(tempIndex).getItemName() + " " + optionsList.get(tempIndex).getSlotLocation()
                + " $" + optionsList.get(tempIndex).getPrice() + " $" + balance.getCurrentMoney());
                displayPurchaseScreen();
            }else{
                System.out.println("You do not have enough money to buy that! ");
                displayPurchaseScreen();
            }


        }catch(Exception e){
            System.out.println("That item is sold out");
            selectProduct();
            //TODO Log info
        }

    }
    public void finishTransaction() throws InvalidData {
        changeRemaining();
        displayMainMenu();
    }
    public void changeRemaining(){
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        int cents = (int)(balance.getCurrentMoney()*100);
        if((balance.getCurrentMoney()*100) > 5) {
            quarter = cents / 25;
            cents %= 25;
            dime = cents / 10;
            cents %= 10;
            nickle = cents / 5;
            cents %= 5;
            Log.WriteLog("GIVE CHANGE: $" + balance.getCurrentMoney() + " $0.00");
            balance.resetMoney();
            System.out.println("Here is your change: ");
            if (quarter > 0) {
                System.out.println("Quarters: " + quarter);
            }
            if (dime > 0) {
                System.out.println("Dimes: " + dime);
            }
            if (nickle > 0) {
                System.out.println("Nickles: " + nickle);
            }
        } else {
            System.out.println("You have no change");
        }

    }

}
