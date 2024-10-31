package com.ps;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static Dealership dealership;
    static Scanner cmdscnr = new Scanner(System.in);
    static Scanner inptscnr = new Scanner(System.in);

    private static void init() {
        dealership = DealershipFileManager.getDealership();
    }

    public static void display() {
        init();
        int choice;

        System.out.println("Welcome to your car dealership! \uD83D\uDE97");

        do {
            System.out.println("\nPlease select one of the following options -");
            System.out.println(" 1) Find vehicles within a price range");
            System.out.println(" 2) Find vehicles by make/model");
            System.out.println(" 3) Find vehicles by year range");
            System.out.println(" 4) Find vehicles by color");
            System.out.println(" 5) Find vehicles by mileage range");
            System.out.println(" 6) Find vehicles by type (car, truck, SUV, van)");
            System.out.println(" 7) List all vehicles");
            System.out.println(" 8) Add a vehicle");
            System.out.println(" 9) Remove a vehicle");
            System.out.println(" 0) Exit\n");
            System.out.print("Please enter the number that corresponds to your selection: ");

            try {
                choice = cmdscnr.nextInt();
                cmdscnr.nextLine();

                switch (choice) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleTypeRequest();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 0:
                        System.out.println("\nExiting...\nSee you soon :)");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.\n");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid choice. Please try again.\n");
                cmdscnr.next();
            }
        } while (true);
    }

    private static void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            String vehicleInfo = String.format(
                    "VIN: %d, Year: %d, Make: %s, Model: %s, Type: %s, Color: %s, Odometer: %d, Price: $%.2f",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice()
            );
            System.out.println(vehicleInfo);
        }
    }

    private static void processGetByPriceRequest() {
        System.out.println("\nPlease specify your price range -\n");

        double minPrice;
        while (true) {
            System.out.print("Enter min price: $");
            String minPriceInput = inptscnr.nextLine().trim();
            try {
                minPrice = Double.parseDouble(minPriceInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid amount. Please enter a valid number.\n");
            }
        }

        double maxPrice;
        while (true) {
            System.out.print("Enter max price: $");
            String maxPriceInput = inptscnr.nextLine().trim();
            try {
                maxPrice = Double.parseDouble(maxPriceInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid amount. Please enter a valid number.\n");
            }
        }

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);

        if (filteredVehicles.isEmpty()) {
            System.out.println("\nThere are no vehicles that match your criteria.");
        } else {
            System.out.println("\nDisplaying all vehicles that match your criteria:\n");
            displayVehicles(filteredVehicles);
        }
    }

    private static void processGetByMakeModelRequest() {
        System.out.println("\nPlease specify your make and model -\n");

        System.out.print("Enter vehicle make: ");
        String make = inptscnr.nextLine().trim();
        System.out.print("Enter vehicle model: ");
        String model = inptscnr.nextLine().trim();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);

        if (filteredVehicles.isEmpty()) {
            System.out.println("\nThere are no vehicles that match your criteria.");
        } else {
            System.out.println("\nDisplaying all vehicles that match your criteria:\n");
            displayVehicles(filteredVehicles);
        }
    }

    private static void processGetByYearRequest() {
        System.out.println("\nPlease specify your year range -\n");

        int minYear;
        while (true) {
            System.out.print("Enter min year: ");
            String minYearInput = inptscnr.nextLine().trim();
            try {
                minYear = Integer.parseInt(minYearInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid year. Please enter a valid number.\n");
            }
        }

        int maxYear;
        while (true) {
            System.out.print("Enter max year: ");
            String maxYearInput = inptscnr.nextLine().trim();
            try {
                maxYear = Integer.parseInt(maxYearInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid year. Please enter a valid number.\n");
            }
        }

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByYear(minYear, maxYear);

        if (filteredVehicles.isEmpty()) {
            System.out.println("\nThere are no vehicles that match your criteria.");
        } else {
            System.out.println("\nDisplaying all vehicles that match your criteria:\n");
            displayVehicles(filteredVehicles);
        }
    }

    private static void processGetByColorRequest() {
        System.out.println("\nPlease specify your color -\n");

        System.out.print("Enter vehicle color: ");
        String color = inptscnr.nextLine().trim();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByColor(color);

        if (filteredVehicles.isEmpty()) {
            System.out.println("\nThere are no vehicles that match your criteria.");
        } else {
            System.out.println("\nDisplaying all vehicles that match your criteria:\n");
            displayVehicles(filteredVehicles);
        }
    }

    private static void processGetByMileageRequest() {
        System.out.println("\nPlease specify your mileage range -\n");

        int minMileage;
        while (true) {
            System.out.print("Enter min mileage: ");
            String minMileageInput = inptscnr.nextLine().trim();
            try {
                minMileage = Integer.parseInt(minMileageInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid mileage. Please enter a valid number.\n");
            }
        }

        int maxMileage;
        while (true) {
            System.out.print("Enter max mileage: ");
            String maxMileageInput = inptscnr.nextLine().trim();
            try {
                maxMileage = Integer.parseInt(maxMileageInput);
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid mileage. Please enter a valid number.\n");
            }
        }

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);

        if (filteredVehicles.isEmpty()) {
            System.out.println("\nThere are no vehicles that match your criteria.");
        } else {
            System.out.println("\nDisplaying all vehicles that match your criteria:\n");
            displayVehicles(filteredVehicles);
        }
    }

    private static void processGetByVehicleTypeRequest() {
        System.out.println("\nPlease specify your vehicle type -\n");

        System.out.print("Enter vehicle type: ");
        String vehicleType = inptscnr.nextLine().trim();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByType(vehicleType);

        if (filteredVehicles.isEmpty()) {
            System.out.println("\nThere are no vehicles that match your criteria.");
        } else {
            System.out.println("\nDisplaying all vehicles that match your criteria:\n");
            displayVehicles(filteredVehicles);
        }
    }

    private static void processGetAllVehiclesRequest() {
        System.out.println("\nDisplaying all vehicles:\n");
        displayVehicles(dealership.getAllVehicles());
    }

    private static void processAddVehicleRequest() {
        System.out.println("\nPlease enter the details of the new vehicle -\n");

        System.out.print("Enter VIN: ");
        int vin = Integer.parseInt(inptscnr.nextLine().trim());

        System.out.print("Enter year: ");
        int year = Integer.parseInt(inptscnr.nextLine().trim());

        System.out.print("Enter make: ");
        String make = inptscnr.nextLine().trim();

        System.out.print("Enter model: ");
        String model = inptscnr.nextLine().trim();

        System.out.print("Enter vehicle type (e.g., car, truck, SUV, van): ");
        String type = inptscnr.nextLine().trim();

        System.out.print("Enter color: ");
        String color = inptscnr.nextLine().trim();

        System.out.print("Enter odometer reading: ");
        int odometer = Integer.parseInt(inptscnr.nextLine().trim());

        System.out.print("Enter price: $");
        double price = Double.parseDouble(inptscnr.nextLine().trim());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(newVehicle);

        System.out.println("\nVehicle added successfully and inventory updated.");
    }

    private static void processRemoveVehicleRequest() {
        System.out.print("\nEnter the VIN of the vehicle you want to remove: ");
        int vin = Integer.parseInt(inptscnr.nextLine().trim());

        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            System.out.println("\nVehicle removed successfully and inventory updated.");
        } else {
            System.out.println("\nNo vehicle found with the specified VIN.");
        }
    }
}