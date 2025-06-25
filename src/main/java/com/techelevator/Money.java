package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private double currentMoney;

    public Money(){
        currentMoney = 0.00;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void depositMoney(double money){
        BigDecimal roundedDown = new BigDecimal(currentMoney).add (new BigDecimal(money));
        currentMoney = roundedDown.setScale(2, RoundingMode.HALF_UP).doubleValue();

    }
    public void subtractMoney(double money){
        BigDecimal roundedDown = new BigDecimal(currentMoney).subtract (new BigDecimal(money));
        currentMoney = roundedDown.setScale(2, RoundingMode.HALF_UP).doubleValue();

    }
    public void resetMoney(){
        currentMoney = 0;
    }
}
