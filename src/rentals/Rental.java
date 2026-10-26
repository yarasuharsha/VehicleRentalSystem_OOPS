package rentals;

import customers.Customer;
import vehicles.Vehicle;

/**
 * Rental.java - Represents a single rental transaction.
 * 
 * A Rental links a Customer to a Vehicle for a certain number of days.
 * The total rental amount is calculated using vehicle.calculateRent(days).
 * Since each child class (Car, Bike, Van, Scooter) overrides calculateRent(),
 * the correct rate is applied automatically — this is method overriding in action.
 */
public class Rental {

    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int numberOfDays;
    private double totalAmount;

    // Constructor
    public Rental(String rentalId, Customer customer, Vehicle vehicle, int numberOfDays) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.numberOfDays = numberOfDays;

        // vehicle.calculateRent(days) calls the overridden method
        // of the actual object (Car, Bike, or Van)
        this.totalAmount = vehicle.calculateRent(numberOfDays);
    }

    // ----- Getters -----

    public String getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * displayRentalDetails() - prints rental transaction info to console.
     */
    public void displayRentalDetails() {
        System.out.println("  Rental ID  : " + rentalId);
        System.out.println("  Customer   : " + customer.getName());
        System.out.println("  Vehicle    : " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("  Vehicle ID : " + vehicle.getVehicleId());
        System.out.println("  Days       : " + numberOfDays);
        System.out.println("  Total Amt  : Rs." + totalAmount);
    }
}
