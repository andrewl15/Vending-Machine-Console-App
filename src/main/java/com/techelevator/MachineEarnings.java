package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MachineEarnings {

private double revenue;

    public double getRevenue() {
        return revenue;
    }
    public MachineEarnings(){
        revenue = 0;
    }
    public void addRevenue(double price){
        BigDecimal roundedDown = new BigDecimal(revenue).add (new BigDecimal(price));
        revenue = roundedDown.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
