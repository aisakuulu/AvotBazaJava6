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
            throw new ChangeDriverException("The driver on the road. You cannot change this driver");
        } else if (truck.getState().equals(State.REPAIR)){
            throw new ChangeDriverException("The truck in repair. You cannot change the driver");
        }
    }

    public void startDriving(Truck truck, Driver driver) {

        if (truck.getState().equals(State.BASE) ) {
            if (!truck.getDriver().equals("")) {
                truck.setState(State.ROUTE);
                System.out.println("The Truck has successfully get started");
            } else {
                System.out.println("There is no any driver");
            }
        }
        else if (truck.getState().equals(State.ROUTE)) {
            throw new TruckStateException("The truck is on the road!");
        }
        else if (truck.getState().equals(State.REPAIR)) {
//            Random random = new Random();
////            int a = random.nextInt(1,3);
//
//            if (a == 1){
//                truck.setState(State.ROUTE);
//            } else {
//                truck.setState(State.BASE);
//            }
            System.out.println("The trucks state is: " + truck.getState());
        }
    }

    public void startRepairing(Truck truck, Driver driver) {
        if (truck.getState().equals(State.BASE) || truck.getState().equals(State.ROUTE)) {
            truck.setState(State.REPAIR);
            System.out.println("The Truck is in repair");
        } else if (truck.getState().equals(State.REPAIR)){
            throw new TruckStateException("This truc is already in repair!");
        }
    }
}
