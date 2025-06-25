package com.techelevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MachineEarningsTest {

    private MachineEarnings machineEarnings;

    @BeforeEach
    public void setUp(){
        this.machineEarnings = new MachineEarnings();
    }

    @Test
    public void add_revenue_add_money(){
        MachineEarnings machineEarnings = new MachineEarnings();
        machineEarnings.addRevenue(2.00);
        assertEquals(2.00, machineEarnings.getRevenue());
    }

}
