package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class DealershipFileManager {

    public static Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
            String line;

            if ((line = br.readLine()) != null) {
                String[] dealershipData = line.split("\\|");

                String name = dealershipData[0];
                String address = dealershipData[1];
                String phone = dealershipData[2];

                dealership = new Dealership(name, address, phone);
            }

            while ((line = br.readLine()) != null) {
                String[] vehicleData = line.split("\\|");

                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String vehicleType = vehicleData[4];
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                dealership.addVehicle(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv"))) {

            String firstLine = String.format("%s|%s|%s",
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone());
            bw.write(firstLine);
            bw.newLine();

            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                String vehicleLine = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice()
                );
                bw.write(vehicleLine);

                if (i < vehicles.size() - 1) {
                    bw.newLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle any IOExceptions
        }
    }
}