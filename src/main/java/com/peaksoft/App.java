package com.peaksoft;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peaksoft.createJsonFiler.JsonFileService;
import com.peaksoft.enums.State;
import com.peaksoft.model.Driver;
import com.peaksoft.model.Truck;
import com.peaksoft.service.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {

    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path TRUCKS = Paths.get("./trucks.json");
    public static final Path DRIVERS = Paths.get("./drivers.json");


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(1, "Renault Magnum", "", State.BASE));
        trucks.add(new Truck(2, "Volvo", "", State.BASE));
        trucks.add(new Truck(3, "DAF XT", "", State.BASE));

        String jsonTrucks = GSON.toJson(trucks);
        JsonFileService.writeFile(jsonTrucks, TRUCKS);
        System.out.println(JsonFileService.readFile(TRUCKS));

        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver(1, "Petr", ""));
        drivers.add(new Driver(2, "Askar", ""));
        drivers.add(new Driver(3, "Uson", ""));

        String jsonDriver = GSON.toJson(drivers);
        JsonFileService.writeFile(jsonDriver, DRIVERS);
        System.out.println(JsonFileService.readFile(DRIVERS));

        printTruck(trucks);
        System.out.println();


        Service service = new Service();
        while (true) {

            System.out.println("Choose Truck by ID:");
            int input = scanner.nextInt();

            if (input > 3 || input < 1) {
                System.err.println("You have entered incorrect information!!!");
            } else {
                Driver.showInfo(trucks.get(input - 1));
                getInstruction();

                int action = scanner.nextInt();
                if (action == 1) {
                    service.changeDriver(trucks.get(input - 1), drivers.get(input - 1));
                    printTruck(trucks);
                    printDriver(drivers);
                } else if (action == 2) {
                    service.startDriving(trucks.get(input - 1), drivers.get(input - 1));
                    System.out.println(trucks.get(input - 1));
                    printTruck(trucks);
                    printDriver(drivers);
                } else if (action == 3) {
                    service.startRepairing(trucks.get(input - 1), drivers.get(input - 1));
                    printTruck(trucks);
                    printDriver(drivers);
                } else if (action == 0) {
                    System.out.println("Game is over. See You!");
                    break;
                } else {
                    System.err.println("You have entered incorrect information!!!");
                }
            }
        }
    }


    public static void printTruck(List<Truck> trucks) {
        System.out.println();
        System.out.println("-----------------------------  TRUCKS  ---------------------------");
        System.out.println("   #   |  Bus                |  Driver            |  State         ");
        System.out.println("-------+---------------------+--------------------+----------------");
        int counter = 1, tabsBus = 20, tabsDriver = 18, tabsState = 14;
        for (Truck truck : trucks) {
            System.out.print("   " + counter + "   | ");
            System.out.print(truck.getName());
            for (int j = 0; j < tabsBus - truck.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + truck.getDriver());
            for (int j = 0; j < tabsDriver - truck.getDriver().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  ");
            System.out.print(truck.getState());
            for (int j = 0; j < tabsState - truck.getState().toString().length(); j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    public static void printDriver(List<Driver> drivers) {

        System.out.println();
        System.out.println("-------------------  DRIVERS  --------------------");
        System.out.println("   #   |  Driver             |  Bus               ");
        System.out.println("-------+---------------------+--------------------");
        int counter = 1, tabsDriver = 20, tabsBus = 18;
        for (Driver i : drivers) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getName());
            for (int j = 0; j < tabsDriver - i.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getTruck());
            for (int j = 0; j < tabsBus; j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    public static void getInstruction() {
        System.out.println("""
                Press - 1 - to change Driver
                Press - 2 - to send to the Route
                Press - 3 - to send to the Repairing
                Press - 0 - to Quit Game
                """);
    }

}
