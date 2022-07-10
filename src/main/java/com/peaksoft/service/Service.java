package com.peaksoft.service;


import com.peaksoft.enums.State;
import com.peaksoft.exeptions.ChangeDriverException;
import com.peaksoft.exeptions.TruckStateException;
import com.peaksoft.model.Driver;
import com.peaksoft.model.Truck;

import java.util.Random;

public class Service {

    public void changeDriver(Truck truck, Driver driver) {
        if(truck.getState().equals(State.BASE)){
            truck.setDriver(driver.getName());
            driver.setTruck(truck.getName());
            System.out.println("The truck " + truck.getName() + "'s driver is: " + driver.getName());
        } else if (truck.getState().equals(State.ROUTE)) {
            System.err.println("The driver is on the road. You can not change this driver!");
        } else if (truck.getState().equals(State.REPAIR)) {
            System.err.println("The Truck is on repair, You can not change driver!");
        }
    }

    public void startDriving(Truck truck, Driver driver) {

        if (truck.getState().equals(State.BASE) ) {
            if (!truck.getDriver().equals("")) {
                truck.setState(State.ROUTE);
                System.out.println("The Truck has successfully get started");
            } else {
                System.err.println("There is no any driver");
            }
        }
        else if (truck.getState().equals(State.ROUTE)) {
            System.err.println("The truck is already on the road!");
        }
        else if (truck.getState().equals(State.REPAIR)) {
            System.err.println("The truck is on repair, you can not drive the truck!");
        }
    }

    public void startRepairing(Truck truck, Driver driver) {
        if (truck.getState().equals(State.BASE) || truck.getState().equals(State.ROUTE)) {
            truck.setState(State.REPAIR);
            System.out.println("The Truck has successfully send to repair!");
        } else if (truck.getState().equals(State.REPAIR)){
            System.err.println("This truck is already in repair!");
        }
    }
}
