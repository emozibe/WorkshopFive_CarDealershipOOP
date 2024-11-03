package com.ps;

import java.time.LocalDate;

public class LeaseContract extends Contract {

    private static final double INTEREST_RATE = 0.04;
    private static final int LEASE_TERM = 36;
    private double residualValue;
    private double leaseFee;
    private boolean isValid;

    public LeaseContract(String contractDate,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicle) {
        super(contractDate, customerName, customerEmail, vehicle);

        int vehicleAge = LocalDate.now().getYear() - vehicle.getYear();

        if (vehicleAge > 3) {
            System.out.println("Error: Cannot lease a vehicle over 3 years old.");
            isValid = false;
        } else {
            this.residualValue = vehicle.getPrice() * 0.5;
            this.leaseFee = vehicle.getPrice() * 0.07;
            isValid = true;
        }
    }

    @Override
    public double getTotalPrice() {
        if (!isValid) {
            return 0.0;
        }

        return (getMonthlyPayment() * LEASE_TERM) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isValid) {
            return 0.0;
        }

        double depreciation = (vehicle.getPrice() - residualValue) / LEASE_TERM;
        double monthlyInterest = ((vehicle.getPrice() + residualValue) / 2) * (INTEREST_RATE / 12);
        return depreciation + monthlyInterest;
    }
}