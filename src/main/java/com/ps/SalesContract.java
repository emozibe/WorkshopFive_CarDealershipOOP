package com.ps;

public class SalesContract extends Contract {

    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private double processingFee;
    private boolean isFinanced;
    private double interestRate;
    private int loanTerm;

    public SalesContract(String contractDate,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicle,
                         boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicle);
        this.isFinanced = isFinanced;

        if (vehicle.getPrice() < 10000) {
            this.processingFee = 295.0;
        } else {
            this.processingFee = 495.0;
        }

        if (isFinanced) {
            if (vehicle.getPrice() >= 10000) {
                this.interestRate = 0.0425;
                this.loanTerm = 48;
            } else {
                this.interestRate = 0.0525;
                this.loanTerm = 24;
            }
        } else {
            this.interestRate = 0.0;
            this.loanTerm = 0;
        }
    }

    @Override
    public double getTotalPrice() {
        double salesTax = vehicle.getPrice() * SALES_TAX_RATE;
        return vehicle.getPrice() + salesTax + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0.0;
        }

        double monthlyInterestRate = interestRate / 12;
        return (vehicle.getPrice() * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, (loanTerm * -1)));
    }
}