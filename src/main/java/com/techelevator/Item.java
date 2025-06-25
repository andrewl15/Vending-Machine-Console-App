package com.techelevator;

public class Item{

    private String slotLocation;
    private double price;
    private String itemName;
    private String stock;
    private String type;

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getType() {
        return type;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }
    public Item(String slotLocation,String itemName ,double price, String type, String stock){
        this.price = price;
        this.itemName = itemName;
        this.stock = stock;
        this.type = type;
        this.slotLocation = slotLocation;
    }

    public String getTypeMessage(){
        return switch (type) {
            case "Chip" -> "Crunch Crunch, Yum!";
            case "Candy" -> "Munch Munch, Yum!";
            case "Drink" -> "Glug Glug, Yum!";
            default -> "Chew Chew, Yum!";
        };

    }
}
