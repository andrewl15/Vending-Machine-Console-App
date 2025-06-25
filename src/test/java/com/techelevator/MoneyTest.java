package com.techelevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {

    private Money money;

    @BeforeEach
    public void setUp(){
        this.money = new Money();
    }

    @Test
    public void deposit_zero_money(){
        Money money = new Money();
        assertEquals(0.00,money.getCurrentMoney());
    }
    @Test
    public void deposit_money_add_money(){
        Money money = new Money();
        money.depositMoney(2.00);
        assertEquals(2.00, money.getCurrentMoney());
    }
    @Test
    public void deposit_money_add_money_with_decimal(){
        Money money = new Money();
        money.depositMoney(2.50);
        assertEquals(2.50, money.getCurrentMoney());
    }
    @Test
    public void deposit_money_multiple_times(){
        Money money = new Money();
        money.depositMoney(2.00);
        money.depositMoney(4.00);
        assertEquals(6.00, money.getCurrentMoney());
    }
    @Test
    public void subtract_zero_money(){
        Money money = new Money();
        assertEquals(0.00,money.getCurrentMoney());
    }
    @Test
    public void subtract_money_subtract_money(){
        Money money = new Money();
        money.depositMoney(10.00);
        money.subtractMoney(2.00);
        assertEquals(8.00, money.getCurrentMoney());
    }
    @Test
    public void subtract_money_multiple_times(){
        Money money = new Money();
        money.depositMoney(10.00);
        money.subtractMoney(2.00);
        money.subtractMoney(3.00);;
        assertEquals(5.00, money.getCurrentMoney());
    }
    @Test
    public void subtract_money_subtract_money_with_decimal(){
        Money money = new Money();
        money.depositMoney(10.00);
        money.subtractMoney(2.50);
        assertEquals(7.50, money.getCurrentMoney());
    }
    @Test
    public void reset_money_to_zero(){
        Money money = new Money();
        money.depositMoney(10.00);
        money.resetMoney();
        assertEquals(0.00,money.getCurrentMoney());
    }
}
