package vehicles;

/**
 * Car.java - Child class of Vehicle
 * 
 * OOPS Concept: INHERITANCE
 * Car extends Vehicle — inherits all fields and methods from Vehicle.
 * 
 * OOPS Concept: METHOD OVERRIDING
 * Car overrides calculateRent() to apply car-specific pricing.
 * Car rate: Rs.1500/day
 */
public class Car extends Vehicle {

    private int numberOfSeats;

    // Constructor: uses super() to call the parent class constructor
    public Car(String vehicleId, String brand, String model, String registrationNumber, int numberOfSeats) {
        super(vehicleId, brand, model, registrationNumber);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public String getType() {
        return "Car";
    }

    /**
     * METHOD OVERRIDING: Car's own version of calculateRent().
     * Car charges Rs.1500 per day.
     */
    @Override
    public double calculateRent(int days) {
        return 1500 * days;
    }

    @Override
    public void displayVehicleDetails() {
        System.out.println("  Type         : Car");
        super.displayVehicleDetails();
        System.out.println("  Seats        : " + numberOfSeats);
        System.out.println("  Rent Rate    : Rs.1500/day");
    }
}
