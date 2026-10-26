package vehicles;

/**
 * Van.java - Child class of Vehicle
 * 
 * OOPS Concept: INHERITANCE
 * Van extends Vehicle — inherits all fields and methods from Vehicle.
 * 
 * OOPS Concept: METHOD OVERRIDING
 * Van overrides calculateRent() to apply van-specific pricing.
 * Van rate: Rs.2000/day
 */
public class Van extends Vehicle {

    private double cargoCapacity; // in tons

    // Constructor: uses super() to call the parent class constructor
    public Van(String vehicleId, String brand, String model, String registrationNumber, double cargoCapacity) {
        super(vehicleId, brand, model, registrationNumber);
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getType() {
        return "Van";
    }

    /**
     * METHOD OVERRIDING: Van's own version of calculateRent().
     * Van charges Rs.2000 per day.
     */
    @Override
    public double calculateRent(int days) {
        return 2000 * days;
    }

    @Override
    public void displayVehicleDetails() {
        System.out.println("  Type         : Van");
        super.displayVehicleDetails();
        System.out.println("  Cargo Cap.   : " + cargoCapacity + " tons");
        System.out.println("  Rent Rate    : Rs.2000/day");
    }
}
